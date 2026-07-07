package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.CourseDTO;
import com.rays.dto.SubjectDTO;
import com.rays.dto.TimeTableDTO;
import com.rays.form.TimeTableForm;
import com.rays.service.CourseServiceInt;
import com.rays.service.SubjectServiceInt;
import com.rays.service.TimeTableServiceInt;

/**
 * REST controller for TimeTable CRUD operations.
 * Inherits save, get, search, and deleteMany from {@link BaseCtl}.
 * Mapped to {@code /TimeTable}.
 *
 * @author Ajay Pratap Kerketta
 */
@RestController
@RequestMapping(value = "TimeTable")
public class TimeTableCtl extends BaseCtl<TimeTableDTO, TimeTableForm, TimeTableServiceInt> {

	@Autowired
	private CourseServiceInt courseService;

	@Autowired
	private SubjectServiceInt subjectService;

	/**
	 * Returns dropdown data required to populate the TimeTable form.
	 * Loads all courses and subjects.
	 *
	 * @return {@link ORSResponse} with {@code courseList} and {@code subjectList}
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<CourseDTO> list = courseService.search(new CourseDTO(), userContext);
		List<SubjectDTO> list1 = subjectService.search(new SubjectDTO(), userContext);
		res.addResult("courseList", list);
		res.addResult("subjectList", list1);
		return res;
	}

}