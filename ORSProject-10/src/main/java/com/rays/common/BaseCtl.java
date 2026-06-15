package com.rays.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.UserDTO;

/**
 * Abstract base controller providing common CRUD and search operations for all
 * REST controllers in the application.
 * <p>
 * All concrete controllers should extend this class and supply the appropriate
 * DTO, Form, and Service generic type parameters. This class wires the service
 * layer automatically and exposes endpoints for save, get, deleteMany, and
 * search.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO}
 * @param <F> the Form type extending {@link BaseForm}
 * @param <S> the Service interface type extending {@link BaseServiceInt}
 *
 * @author malay dongre
 */
public class BaseCtl<T extends BaseDTO, F extends BaseForm, S extends BaseServiceInt<T>> {

	/**
	 * The service layer instance auto-wired by Spring for handling business logic.
	 */
	@Autowired
	protected S service;

	/**
	 * The number of records per page, injected from application properties
	 * ({@code page.size}).
	 */
	@Value("${page.size}")
	private int pageSize = 0;

	/**
	 * The current user's context, populated from the HTTP session before each
	 * request.
	 */
	protected UserContext userContext = null;

	/**
	 * Populates {@link #userContext} from the HTTP session before any request
	 * method is invoked.
	 * <p>
	 * If no {@code UserContext} is found in the session, a default context is
	 * created using a hardcoded {@link UserDTO} with login ID
	 * {@code ajay@gmail.com}.
	 * </p>
	 *
	 * @param session the current {@link HttpSession} from which the user context is
	 *                retrieved
	 */
	@ModelAttribute
	public void setUserContext(HttpSession session) {
		userContext = UserContextHolder.getContext();

		if (userContext == null) {
			UserDTO dto = new UserDTO();
			dto.setLoginId("ajaystl3@gmail.com");
			userContext = new UserContext(dto);
		}
	}

	/**
	 * Validates the binding result from a form submission and returns an
	 * {@link ORSResponse}.
	 * <p>
	 * If validation errors are present, the response will have
	 * {@code success=false} and the result map will contain a field-to-message
	 * error map under the {@code inputerror} key.
	 * </p>
	 *
	 * @param bindingResult the {@link BindingResult} produced by Spring's form
	 *                      validation
	 * @return an {@link ORSResponse} with {@code success=true} if no errors exist,
	 *         or {@code success=false} with a map of field errors if validation
	 *         fails
	 */
	public ORSResponse validate(BindingResult bindingResult) {

		ORSResponse res = new ORSResponse(true);

		if (bindingResult.hasErrors()) {
			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});

			res.addInputError(errors);
		}

		return res;
	}

	/**
	 * Saves (adds or updates) a record based on the submitted form data.
	 * <p>
	 * Validates the form first. If validation fails, returns the error response
	 * immediately. Checks for duplicate unique-key values before persisting.
	 * Returns a success message and the saved DTO on success, or an error message
	 * on failure.
	 * </p>
	 *
	 * @param form          the form object containing the data to save; must be
	 *                      valid per Bean Validation
	 * @param bindingResult the validation result for the submitted form
	 * @return an {@link ORSResponse} with {@code success=true} and the saved DTO on
	 *         success, or {@code success=false} with an error/validation message on
	 *         failure
	 */
	@PostMapping(value = "save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (!res.success) {
			return res;
		}

		T dto = (T) form.getDto();

		if (dto.getUniqueKey() != null && !dto.getUniqueKey().equals("")) {

			T existsDTO = service.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);

			// main condition check for already exist record
			if (existsDTO != null && (dto.getId() == null || !existsDTO.getId().equals(dto.getId()))) {
				res.addMessage(dto.getLabel() + " already exists");
				res.setSuccess(false);
				return res;
			}
		}

		Long exId = dto.getId();

		// System.out.println("User Context:" + userContext);

		long id = service.save(dto, userContext);

		if (id > 0 && exId == null) {
			res.addMessage("data added successfully");
			res.addData(dto);
		} else if (id == dto.getId()) {
			res.addMessage("data updated successfully");
			res.addData(dto);
		} else {
			res.addMessage("issue in adding");
			res.setSuccess(false);
		}

		return res;
	}

	/**
	 * Retrieves a single record by its primary key ID.
	 *
	 * @param id the primary key of the record to fetch
	 * @return an {@link ORSResponse} with {@code success=true} and the DTO in the
	 *         data field if found, or {@code success=false} with a "Record Not
	 *         Found" message if not found
	 */
	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse(true);

		T dto = service.findById(id, userContext);

		if (dto != null) {
			res.setSuccess(true);
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record Not Found");
		}

		return res;
	}

	/**
	 * Deletes multiple records by their IDs and returns the updated list for the
	 * current page.
	 * <p>
	 * After deletion, performs a search using the form's DTO and the given page
	 * number to return the remaining records. Also returns the size of the next
	 * page to assist with client-side pagination.
	 * </p>
	 *
	 * @param ids    an array of record ID strings extracted from the path variable
	 * @param pageNo the current page number (as a {@link String}) used to re-fetch
	 *               the list after deletion
	 * @param form   the search form whose DTO is used to query remaining records
	 * @return an {@link ORSResponse} with {@code success=true}, the updated list,
	 *         and the next page size on success; or {@code success=false} with an
	 *         error message on failure
	 */
	@PostMapping("deleteMany/{ids}")
	public ORSResponse deleteMany(@PathVariable String[] ids, @RequestParam("pageNo") String pageNo,
			@RequestBody F form) {

		ORSResponse res = new ORSResponse();

		try {
			for (String id : ids) {
				service.delete(Long.parseLong(id), userContext);
			}

			T dto = (T) form.getDto();

			List<T> list = service.search(dto, Integer.parseInt(pageNo), pageSize, userContext);

			List<T> nextList = service.search(dto, Integer.parseInt(pageNo + 1), pageSize, userContext);

			if (list.size() == 0) {
				res.setSuccess(false);
				res.addMessage("Record Not Found...!");
			} else {
				res.setSuccess(true);
				res.addMessage("Records deleted Successfully");
				res.addData(list);
				res.addResult("nextList", nextList.size());
			}

		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}

		return res;

	}

	/**
	 * Searches for records matching the filter criteria in the form, with
	 * pagination support.
	 * <p>
	 * Accepts both GET and POST HTTP methods. Normalizes negative page numbers to
	 * 0. Also fetches the next page to help the client determine if more records
	 * are available.
	 * </p>
	 *
	 * @param form   the search form whose DTO holds the filter criteria
	 * @param pageNo the zero-based page number for pagination; negative values are
	 *               treated as 0
	 * @return an {@link ORSResponse} with {@code success=true}, the matching list,
	 *         and the next page size on success; or {@code success=false} with a
	 *         "Record not found" message if no results are returned
	 */
	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse(true);

		pageNo = (pageNo < 0) ? 0 : pageNo;

		T dto = (T) form.getDto();

		List<T> list = service.search(dto, pageNo, pageSize, userContext);

		List<T> nextList = service.search(dto, pageNo + 1, pageSize, userContext);

		if (list.size() == 0) {
			res.setSuccess(false);
			res.addMessage("Record not found..!!");
		} else {
			res.setSuccess(true);
			res.addData(list);
			res.addResult("nextListSize", nextList.size());
		}

		return res;

	}

}