package com.rays.ctl;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.common.BaseServiceInt;
import com.rays.dto.ApprovalHistoryDTO;
import com.rays.form.ApprovalHistoryForm;
import com.rays.service.ApprovalHistoryServiceInt;

@RestController
@RequestMapping(value = "ApprovalHistory")
public class ApprovalHistoryCtl extends BaseCtl<ApprovalHistoryDTO, ApprovalHistoryForm, ApprovalHistoryServiceInt> {

}
