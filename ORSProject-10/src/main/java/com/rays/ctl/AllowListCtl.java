package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.AllowListDTO;
import com.rays.form.AllowListForm;
import com.rays.service.AllowListServiceInt;

@RestController
@RequestMapping(value = "AllowList")
public class AllowListCtl extends BaseCtl <AllowListDTO,AllowListForm,AllowListServiceInt> {

}
