package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.UserDTO.UserRequestDTO;
import com.example.cms.UserDTO.UserResponse;
import com.example.cms.utility.ResponseStructure;

public interface UserService {
	
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequestDTO user);

	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);
//
//	public ResponseEntity<ResponseStructure<UserResponse>> findUniqueUser(int userId);
//	
}
