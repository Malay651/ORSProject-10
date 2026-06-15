package com.rays.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.BookDTO;
import com.rays.dto.CateringDTO;

public class CateringForm extends BaseForm {

	@NotEmpty(message="vendorname is required")
	private String vendorName;
	@NotEmpty(message="menutype is required")
	private String menuType;
	@NotNull(message="cost is required")
	private int cost;
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public BaseDTO getDto() {
		CateringDTO dto = initDTO(new CateringDTO());
		dto.setVendorName(vendorName);
		dto.setMenuType(menuType);
		dto.setCost(cost);
		return dto;
	}
}
