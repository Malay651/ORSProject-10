package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseDAOInt;
import com.rays.common.BaseDTO;
import com.rays.common.BaseServiceImpl;
import com.rays.dao.EnrollmentDAOInt;
import com.rays.dto.EnrollmentDTO;
@Service
@Transactional
public class EnrollmentServiceImpl extends BaseServiceImpl<EnrollmentDTO, EnrollmentDAOInt> implements EnrollmentServiceInt {

}
