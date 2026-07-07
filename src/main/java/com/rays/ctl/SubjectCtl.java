package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.CourseDTO;
import com.rays.dto.SubjectDTO;
import com.rays.form.SubjectForm;
import com.rays.service.CourseServiceInt;
import com.rays.service.SubjectServiceInt;

/**
 * REST controller for Subject CRUD operations.
 * Inherits save, get, search, and deleteMany from {@link BaseCtl}.
 * Mapped to {@code /Subject}.
 *
 * @author Ajay Pratap Kerketta
 */
@RestController
@RequestMapping(value = "Subject")
public class SubjectCtl extends BaseCtl<SubjectDTO, SubjectForm, SubjectServiceInt> {

	@Autowired
	private CourseServiceInt courseService;

	/**
	 * Returns dropdown data required to populate the Subject form.
	 * Loads all courses.
	 *
	 * @return {@link ORSResponse} with {@code courseList} containing all available courses
	 */
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<DropdownList> list = courseService.search(new CourseDTO(), userContext);
		res.addResult("courseList", list);
		return res;
	}

}