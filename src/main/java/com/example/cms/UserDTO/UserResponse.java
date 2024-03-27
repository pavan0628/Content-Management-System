package com.example.cms.UserDTO;



import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserResponse {
	private int userId;
	private String username;
	private String email;
	private boolean deleted;
	private  LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
}
