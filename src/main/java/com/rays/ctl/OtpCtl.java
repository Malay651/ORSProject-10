package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.OtpDTO;
import com.rays.form.OtpForm;
import com.rays.service.OtpServiceInt;

@RestController
@RequestMapping(value="otp")
public class OtpCtl extends BaseCtl<OtpDTO,OtpForm,OtpServiceInt> {

}
