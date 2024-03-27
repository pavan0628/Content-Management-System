package com.example.cms.serviceIMPL;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.UserDTO.UserRequestDTO;
import com.example.cms.UserDTO.UserResponse;
import com.example.cms.exception.EmailAlreadyPresentException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.model.User;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceIMPL implements UserService{

	private UserRepository repository;
	private ResponseStructure<UserResponse> structure;
	private PasswordEncoder password;
	

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequestDTO userRequest) {
		if(repository.existsByEmail(userRequest.getEmail()))
			throw new EmailAlreadyPresentException("Failed to register");
		
		User user = repository.save(mapToUser(userRequest, new User()));
		
		return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
				             				.setMessage("User registered successfully")
				             				.setData(mapToUserResponse(user)));
	}
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.email(user.getEmail())
				.deleted(user.isDeleted())
				.createdAt(user.getCreatedAt())
				.lastModifiedAt(user.getLastModifiedAt())
				.build();
	}
	private User mapToUser(UserRequestDTO userRequest, User user) {
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setDeleted(false);
		user.setCreatedAt(LocalDateTime.now());
		user.setLastModifiedAt(LocalDateTime.now());
		user.setPassword(password.encode(userRequest.getPassword()));
		return user;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {
		 return repository.findById(userId).map(user -> {
			 user.setDeleted(true);
			 user = repository.save(user);
			 return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
					 .setMessage("Deleted successfully")
					 .setData(mapToUserResponse(user)));
		 }).orElseThrow(()->new UserNotFoundException ("User is not found"));
	}
//	
//	@Override
//	public ResponseEntity<ResponseStructure<UserResponse>> findUniqueUser(int userId) {
//		return repository.findById(userId).map(u->{
//			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
//					.setMessage("User Found")
//					.setData(mapToUserResponse(u)));})
//		
//				.orElseThrow(()-> new UserNotFoundException ("User Not Found by Id"));
//	}
}
