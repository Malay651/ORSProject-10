package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.HealthCheckDTO;
import com.rays.form.HealthCheckForm;
import com.rays.service.HealthCheckServiceInt;

@RestController
@RequestMapping(value= "HealthCheck")
 public class HealthCheckCtl extends BaseCtl <HealthCheckDTO, HealthCheckForm, HealthCheckServiceInt> {

}
