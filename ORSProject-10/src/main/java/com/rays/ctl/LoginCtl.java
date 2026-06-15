package com.rays.ctl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.common.UserContext;
import com.rays.config.JWTUtil;
import com.rays.dto.UserDTO;
import com.rays.form.ForgetPasswordForm;
import com.rays.form.LoginForm;
import com.rays.form.UserForm;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserServiceInt;

/**
 * REST controller handling authentication operations: login, sign-up, logout,
 * and forgot password. All endpoints are publicly accessible under {@code /Auth}.
 *
 * @author Ajay Pratap Kerketta
 */
@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<UserDTO, UserForm, UserServiceInt> {

	@Autowired
	private JWTUtil jwtUtil;

	/**
	 * Authenticates a user and returns a JWT token along with user details.
	 *
	 * @param form          the login form containing {@code loginId} and {@code password}
	 * @param bindingResult validation result for the submitted form
	 * @return {@link ORSResponse} with {@code success=true} and JWT token on success,
	 *         or {@code success=false} with an error message on invalid credentials
	 * @throws Exception if JWT token generation fails
	 */
	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult) throws Exception {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = service.authenticate(form.getLoginId(), form.getPassword());

		if (dto == null) {
			res.setSuccess(false);
			res.addMessage("Invalid Login ID & Password");
		} else {

			UserContext context = new UserContext(dto);

			String token = jwtUtil.generateToken(dto.getId(), dto.getLoginId(), dto.getRoleName());

			res.setSuccess(true);
			res.addData(dto);
			res.addResult("loginId", dto.getLoginId());
			res.addResult("role", dto.getRoleName());
			res.addResult("fname", dto.getFirstName());
			res.addResult("lname", dto.getLastName());
			res.addResult("token", token);
			return res;
		}
		return res;
	}

	/**
	 * Registers a new user with {@code Inactive} status and role ID {@code 2}.
	 *
	 * @param form          the registration form with user details
	 * @param bindingResult validation result for the submitted form
	 * @return {@link ORSResponse} with {@code success=true} on successful registration,
	 *         or {@code success=false} if the login ID already exists or validation fails
	 */
	@PostMapping("signUp")
	public ORSResponse signUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = service.findByLoginId(form.getLoginId(), userContext);

		if (dto != null) {
			res.setSuccess(false);
			res.addMessage("Login Id already exists");
			return res;
		}

		dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		dto.setDob(form.getDob());
		dto.setGender(form.getGender());
		dto.setPhone(form.getPhone());

		dto.setStatus("Inactive");
		dto.setRoleId(2L);

		service.register(dto, userContext);

		res.setSuccess(true);
		res.addMessage("User has been registered successfully..!!");
		return res;
	}

	/**
	 * Invalidates the current HTTP session, effectively logging the user out.
	 *
	 * @param session the current {@link HttpSession} to invalidate
	 * @return {@link ORSResponse} with a logout success message
	 * @throws Exception if session invalidation fails
	 */
	@GetMapping("logout")
	public ORSResponse logout(HttpSession session) throws Exception {

		ORSResponse res = new ORSResponse();

		session.invalidate();

		res.addMessage("Logout successfully..!!!");

		return res;
	}

	/**
	 * Sends the user's password to their registered email if the login ID is found.
	 *
	 * @param form          the form containing the {@code loginId} to look up
	 * @param bindingResult validation result for the submitted form
	 * @return {@link ORSResponse} with {@code success=true} and a confirmation message,
	 *         or {@code success=false} if the login ID is not found
	 */
	@PostMapping("forgetPassword")
	public ORSResponse forgetPassword(@RequestBody @Valid ForgetPasswordForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO fDto = service.forgotPassword(form.getLoginId());

		if (fDto == null) {
			res.setSuccess(false);
			res.addMessage("LoginId / Email not found.");
			return res;
		} else {
			res.setSuccess(true);
			res.addMessage("Hello " + fDto.getFirstName() + " " + fDto.getLastName()
					+ "..! Your password has been sent on your email.");
		}
		return res;
	}
}