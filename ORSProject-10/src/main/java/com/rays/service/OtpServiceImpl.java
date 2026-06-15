package com.rays.service;




import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseDAOImpl;
import com.rays.common.BaseServiceImpl;
import com.rays.dao.OtpDAOInt;
import com.rays.dto.OtpDTO;

@Service
@Transactional
public class OtpServiceImpl extends BaseServiceImpl<OtpDTO,OtpDAOInt> implements OtpServiceInt {

	
}
