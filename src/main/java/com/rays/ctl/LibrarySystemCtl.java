package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dao.LibrarySystemDAOInt;
import com.rays.dto.LibrarySystemDTO;
import com.rays.form.LibrarySystemForm;
import com.rays.service.LibrarySystemServiceInt;

@RestController
@RequestMapping(value= "librarysystem")
public class LibrarySystemCtl extends BaseCtl <LibrarySystemDTO,LibrarySystemForm,LibrarySystemServiceInt> {

	
}
