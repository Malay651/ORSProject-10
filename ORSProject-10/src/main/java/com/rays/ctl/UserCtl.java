package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;
import com.rays.form.ChangePasswordForm;
import com.rays.form.MyProfileForm;
import com.rays.form.UserForm;
import com.rays.service.AttachmentServiceInt;
import com.rays.service.RoleServiceInt;
import com.rays.service.UserServiceInt;

/**
 * REST controller for User CRUD and profile picture operations. Inherits save,
 * get, search, and deleteMany from {@link BaseCtl}. Mapped to {@code /User}.
 *
 * @author Ajay Pratap Kerketta
 */
@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl<UserDTO, UserForm, UserServiceInt> {

	@Autowired
	RoleServiceInt roleService;

	@Autowired
	AttachmentServiceInt attachmentService;

	/**
	 * Returns all roles as a dropdown list for the User form.
	 *
	 * @return {@link ORSResponse} with {@code roleList} containing all available
	 *         roles
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);

		RoleDTO dto = new RoleDTO();

		List<DropdownList> roleList = roleService.search(dto, userContext);

		res.addResult("roleList", roleList);

		return res;
	}

	/**
	 * Uploads or replaces the profile picture for the given user. Saves the file as
	 * an {@link AttachmentDTO} and updates the user's {@code imageId} if new.
	 *
	 * @param userId the ID of the user whose profile picture is being uploaded
	 * @param file   the multipart image file to upload
	 * @param req    the incoming HTTP request
	 * @return {@link ORSResponse} with {@code success=true} and the saved
	 *         {@code imageId}
	 */
	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		AttachmentDTO attachmentDto = new AttachmentDTO(file);

		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(userId);

		UserDTO userDto = service.findById(userId, userContext);

		if (userDto.getImageId() != null && userDto.getImageId() > 0) {

			attachmentDto.setId(userDto.getImageId());

		}

		Long imageId = attachmentService.save(attachmentDto, userContext);

		if (userDto.getImageId() == null) {

			userDto.setImageId(imageId);

			service.update(userDto, userContext);
		}

		ORSResponse res = new ORSResponse();

		res.addResult("imageId", imageId);
		res.setSuccess(true);

		return res;
	}

	/**
	 * Streams the profile picture of the given user directly to the HTTP response.
	 * Writes an error message if no image is found.
	 *
	 * @param userId   the ID of the user whose profile picture is to be downloaded
	 * @param response the HTTP response; content type is set from the stored
	 *                 attachment type
	 */
	@GetMapping("/profilePic/{userId}")
	public void downloadPic(@PathVariable Long userId, HttpServletResponse response) {

		try {

			UserDTO userDto = service.findById(userId, userContext);

			AttachmentDTO attachmentDTO = null;

			if (userDto != null) {
				attachmentDTO = attachmentService.findById(userDto.getImageId(), userContext);
			}

			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Updates the profile information of the currently logged-in user.
	 * 
	 * @param form          the my profile form containing updated user details
	 * @param bindingResult validation result
	 * @return ORSResponse with success message if profile updated successfully
	 */
	@PostMapping("myProfile")
	public ORSResponse myProfile(@RequestBody @Valid MyProfileForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = service.findByLoginId(userContext.getLoginId(), userContext);
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setDob(form.getDob());
		dto.setPhone(form.getPhone());
		dto.setGender(form.getGender());

		service.update(dto, userContext);

		res.setSuccess(true);
		res.addMessage("Your Profile updated successfully..!!");

		return res;
	}
	
	/**
	 * Changes the password for the specified user.
	 * 
	 * @param form the change password form containing loginId, old password, and new password
	 * @param bindingResult validation result
	 * @return ORSResponse with success message if password changed, or error if old password is invalid
	 */
	@PostMapping("changePassword")
	public ORSResponse changePassword(@RequestBody @Valid ChangePasswordForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO changedDto = service.changePassword(form.getLoginId(), form.getOldPassword(), form.getNewPassword(),
				userContext);

		if (changedDto == null) {
			res.setSuccess(false);
			res.addMessage("Invalid old password");
			return res;
		}

		res.setSuccess(true);
		res.addMessage("Password has been changed");

		return res;
	}

}