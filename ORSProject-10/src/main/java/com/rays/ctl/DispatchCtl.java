package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.DispatchDTO;
import com.rays.form.DispatchForm;
import com.rays.service.DispatchServiceInt;

@RestController
@RequestMapping(value = "Dispatch")
public class DispatchCtl extends BaseCtl <DispatchDTO, DispatchForm, DispatchServiceInt> {

}
