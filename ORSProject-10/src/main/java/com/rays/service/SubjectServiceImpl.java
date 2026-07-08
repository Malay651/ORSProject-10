package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.SubjectDAOInt;
import com.rays.dto.SubjectDTO;

/**
 * Service implementation for {@link SubjectDTO}. Inherits all standard CRUD
 * operations from {@link BaseServiceImpl}.
 *
 * @author Ajay Pratap Kerketta
 */
@Service
@Transactional
public class SubjectServiceImpl extends BaseServiceImpl<SubjectDTO, SubjectDAOInt> implements SubjectServiceInt {

}