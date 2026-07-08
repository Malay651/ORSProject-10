package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.HelpDeskDAOInt;
import com.rays.dto.HelpDeskDTO;

@Service
@Transactional
public class HelpDeskServiceImpl extends BaseServiceImpl<HelpDeskDTO, HelpDeskDAOInt> implements HelpDeskServiceInt{

}
