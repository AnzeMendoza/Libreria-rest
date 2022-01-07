package edu.sucho.libreriaweb.web;


import edu.sucho.libreriaweb.web.presentation.AuthorizationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Autowired
//	private UserService userService;
//
//	public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//		this.userService = userService;
//		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//	}
//
//	//@Secured("ROLE_ADMIN")
//	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
//	@GetMapping("/{id}")
//	public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
//		final User user = userService.getUser(id);
//
//		if (user == null) {
//			return ResponseEntity.notFound().build();
//		}
//
//		UserResponse userResponse = UserMapper.toResponse(user);
//		return new ResponseEntity<>(userResponse, HttpStatus.OK);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PostMapping
//	public ResponseEntity<User> saveUser(@RequestBody AuthorizationRequest userRequest) {
//		userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
//		final User userToSave = userService.save(UserMapper.toDomain(userRequest));
//
//		return new ResponseEntity<>(userToSave, HttpStatus.OK);
//	}
}
