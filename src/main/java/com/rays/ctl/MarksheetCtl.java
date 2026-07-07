package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;
import com.rays.form.MarksheetForm;
import com.rays.service.MarksheetServiceInt;
import com.rays.service.StudentServiceInt;

/**
 * REST controller for Marksheet operations.
 * Inherits save, get, search, and deleteMany from {@link BaseCtl}.
 * Mapped to {@code /Marksheet}.
 *
 * @author Ajay Pratap Kerketta
 */
@RestController
@RequestMapping(value = "Marksheet")
public class MarksheetCtl extends BaseCtl<MarksheetDTO, MarksheetForm, MarksheetServiceInt> {

	@Autowired
	private StudentServiceInt studentService;

	@Autowired
	private MarksheetServiceInt marksheetService;

	/**
	 * Returns dropdown data required to populate the Marksheet form.
	 * Loads all students and existing marksheets.
	 *
	 * @return {@link ORSResponse} with {@code studentList} and {@code marksheetList}
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);

		List<StudentDTO> list = studentService.search(new StudentDTO(), userContext);
		res.addResult("studentList", list);

		List<DropdownList> mlist = marksheetService.search(new MarksheetDTO(), userContext);
		res.addResult("marksheetList", mlist);

		return res;
	}

	/**
	 * Finds a marksheet by its roll number.
	 *
	 * @param rollNo the roll number to search for
	 * @return {@link ORSResponse} with the matching {@link MarksheetDTO} on success,
	 *         or {@code success=false} with a "Record not found" message if not found
	 */
	@GetMapping("rollno/{rollNo}")
	public ORSResponse rollNo(@PathVariable String rollNo) {
		ORSResponse res = new ORSResponse(true);
		MarksheetDTO dto = service.findByRollNo(rollNo, userContext);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	/**
	 * Returns the top 10 marksheet records ordered by merit (highest marks first).
	 *
	 * @return {@link ORSResponse} with {@code list} containing the merit list
	 */
	@GetMapping("meritlist")
	public ORSResponse getMeritList() {
		//System.out.println("getMeritList run on ctl");
		List<MarksheetDTO> list = service.getMeritList(userContext);
		ORSResponse res = new ORSResponse(true);
		res.addResult("list", list);

		return res;
	}

}