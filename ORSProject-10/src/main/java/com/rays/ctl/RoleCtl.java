package com.rays.ctl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleServiceInt;

/**
 * REST controller for Role CRUD operations.
 * Inherits save, get, search, and deleteMany from {@link BaseCtl}.
 * Mapped to {@code /Role}.
 *
 * @author Ajay Pratap Kerketta
 */
@RestController
@RequestMapping(value = "Role")
public class RoleCtl extends BaseCtl<RoleDTO, RoleForm, RoleServiceInt> {

	/**
	 * Returns all roles as a dropdown list for form population.
	 *
	 * @return {@link ORSResponse} with {@code roleList} containing all available roles
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);

		RoleDTO dto = new RoleDTO();

		List<DropdownList> roleList = service.search(dto, userContext);

		res.addResult("roleList", roleList);

		return res;
	}
}