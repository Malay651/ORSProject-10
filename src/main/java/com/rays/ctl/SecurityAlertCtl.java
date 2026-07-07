package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.SecurityAlertDTO;
import com.rays.form.SecurityAlertForm;
import com.rays.service.SecurityAlertServiceInt;

@RestController
@RequestMapping(value = "SecurityAlert")
public class SecurityAlertCtl extends BaseCtl<SecurityAlertDTO,SecurityAlertForm,SecurityAlertServiceInt> {

}
