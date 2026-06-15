package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.AllowListDTO;
import com.rays.dto.LibrarySystemDTO;

public class LibrarySystemForm extends BaseForm  {

	@NotEmpty(message = "bookname is required")
	private String bookName;
	@NotEmpty(message = "authorname is required")
	private String authorname;
	@NotNull(message = "issuedate is required")
	private Date issueDate;
	@NotNull(message = "price is required")
	private Double price;
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public BaseDTO getDto() {
		LibrarySystemDTO dto = initDTO(new LibrarySystemDTO());
		dto.setBookName(bookName);
		dto.setAuthorName(authorname);
		dto.setIssueDate(issueDate);
		dto.setPrice(price);
		return dto;
	}
}
