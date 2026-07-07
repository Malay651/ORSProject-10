package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.SecurityAlertDAOInt;
import com.rays.dto.SecurityAlertDTO;
@Service
@Transactional
public class SecurityAlertServiceImpl extends BaseServiceImpl<SecurityAlertDTO, SecurityAlertDAOInt> implements SecurityAlertServiceInt{

}
