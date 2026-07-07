package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.LimitDTO;
import com.rays.form.LimitForm;
import com.rays.service.LimitServiceInt;
@RestController
@RequestMapping(value = "Limit")
public class LimitCtl extends BaseCtl<LimitDTO, LimitForm, LimitServiceInt>{

}
