package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.HelpDeskDTO;
import com.rays.form.HelpDeskForm;
import com.rays.service.HelpDeskServiceInt;

@RestController
@RequestMapping(value="HelpDesk")
public class HelpDeskCtl extends BaseCtl <HelpDeskDTO,HelpDeskForm,HelpDeskServiceInt> {

	
}
