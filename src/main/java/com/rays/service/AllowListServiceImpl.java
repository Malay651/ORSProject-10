package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.AllowListDAOInt;
import com.rays.dto.AllowListDTO;


@Service
@Transactional
public class AllowListServiceImpl extends BaseServiceImpl<AllowListDTO, AllowListDAOInt> implements AllowListServiceInt {

}
