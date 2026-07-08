package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.CollegeDTO;
import com.rays.dto.StudentDTO;
import com.rays.form.StudentForm;
import com.rays.service.CollegeServiceInt;
import com.rays.service.StudentServiceInt;

/**
 * REST controller for Student CRUD operations.
 * Inherits save, get, search, and deleteMany from {@link BaseCtl}.
 * Mapped to {@code /Student}.
 *
 * @author Ajay Pratap Kerketta
 */
@RestController
@RequestMapping(value = "Student")
public class StudentCtl extends BaseCtl<StudentDTO, StudentForm, StudentServiceInt> {

	@Autowired
	private CollegeServiceInt collegeService;

	/**
	 * Returns dropdown data required to populate the Student form.
	 * Loads all colleges.
	 *
	 * @return {@link ORSResponse} with {@code collegeList} containing all available colleges
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<CollegeDTO> list = collegeService.search(new CollegeDTO(), userContext);
		res.addResult("collegeList", list);
		return res;
	}

}