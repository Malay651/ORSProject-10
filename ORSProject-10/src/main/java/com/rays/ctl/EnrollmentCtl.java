package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.EnrollmentDTO;
import com.rays.form.EnrollmentForm;
import com.rays.service.EnrollmentServiceInt;
@RestController
@RequestMapping(value= "Enrollment")
public class EnrollmentCtl extends BaseCtl <EnrollmentDTO, EnrollmentForm ,EnrollmentServiceInt> {

}
