package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.StudentDAOInt;
import com.rays.dto.StudentDTO;

/**
 * Service implementation for {@link StudentDTO}. Inherits all standard CRUD
 * operations from {@link BaseServiceImpl}.
 *
 * @author Ajay Pratap Kerketta
 */
@Service
@Transactional
public class StudentServiceImpl extends BaseServiceImpl<StudentDTO, StudentDAOInt> implements StudentServiceInt {

}