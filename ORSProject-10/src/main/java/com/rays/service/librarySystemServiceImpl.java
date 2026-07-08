package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.LibrarySystemDAOImpl;
import com.rays.dao.LibrarySystemDAOInt;
import com.rays.dto.LibrarySystemDTO;

@Service
@Transactional
public class librarySystemServiceImpl extends BaseServiceImpl<LibrarySystemDTO, LibrarySystemDAOInt> implements LibrarySystemServiceInt {

}
