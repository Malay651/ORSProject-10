package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.BookDTO;
import com.rays.form.BookForm;
import com.rays.service.BookServiceInt;

@RestController
@RequestMapping(value="Book")
public class BookCtl extends BaseCtl <BookDTO,BookForm,BookServiceInt> {

}
