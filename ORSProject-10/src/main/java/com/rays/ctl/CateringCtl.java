package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.CateringDTO;
import com.rays.form.CateringForm;
import com.rays.service.CateringServiceInt;

@RestController
@RequestMapping(value="catering")
public class CateringCtl extends BaseCtl <CateringDTO,CateringForm,CateringServiceInt> {

}
