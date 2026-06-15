package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.CourseDTO;
import com.rays.form.CourseForm;
import com.rays.service.CourseServiceInt;

/**
 * REST controller for Course CRUD operations.
 * Inherits save, get, search, and deleteMany from {@link BaseCtl}.
 * Mapped to {@code /Course}.
 *
 * @author Ajay Pratap Kerketta
 */
@RestController
@RequestMapping(value = "Course")
public class CourseCtl extends BaseCtl<CourseDTO, CourseForm, CourseServiceInt> {

}