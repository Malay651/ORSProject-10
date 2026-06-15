package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.HealthCheckDAOInt;
import com.rays.dto.HealthCheckDTO;

@Service
@Transactional
public class HealthCheckServiceImpl extends BaseServiceImpl<HealthCheckDTO, HealthCheckDAOInt> implements HealthCheckServiceInt {


	
}
