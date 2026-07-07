package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.BookDTO;
import com.rays.dto.UserDTO;

public class BookForm extends BaseForm {
    
	@NotEmpty(message="booktitle is required")
	private String bookTitle;
	
	@NotEmpty(message="bookname is required")
	private String bookName;
	
	@NotNull(message="bookdate is required")
	private Date bookDate;
	
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	
	@Override
	public BaseDTO getDto() {
		BookDTO dto = initDTO(new BookDTO());
		dto.setBookTitle(bookTitle);
		dto.setBookName(bookName);
		dto.setBookDate(bookDate);
		return dto;
	}
}