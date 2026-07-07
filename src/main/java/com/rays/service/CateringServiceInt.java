package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceInt;
import com.rays.dto.CateringDTO;

@Transactional
@Service
public interface CateringServiceInt extends BaseServiceInt<CateringDTO> {

}
