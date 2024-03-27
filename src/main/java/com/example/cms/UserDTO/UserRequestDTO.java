package com.example.cms.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

	@NotBlank(message = "User Name is required")
	@NotNull(message = "User Name is required")
	private String username;

	@NotBlank(message = "User Email is required")
	@NotNull(message = "User Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	@NotNull(message = "Password is required")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	private String password;
	
	//private boolean deleted;
}
