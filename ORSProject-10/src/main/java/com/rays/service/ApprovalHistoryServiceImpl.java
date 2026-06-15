package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.ApprovalHistoryDAOInt;
import com.rays.dto.ApprovalHistoryDTO;

@Service
@Transactional
public class ApprovalHistoryServiceImpl extends BaseServiceImpl<ApprovalHistoryDTO, ApprovalHistoryDAOInt> implements ApprovalHistoryServiceInt{

}
