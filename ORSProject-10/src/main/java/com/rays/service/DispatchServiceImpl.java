package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.DispatchDAOInt;
import com.rays.dto.DispatchDTO;


@Service
@Transactional
public class DispatchServiceImpl extends BaseServiceImpl<DispatchDTO, DispatchDAOInt> implements DispatchServiceInt {

	
}
