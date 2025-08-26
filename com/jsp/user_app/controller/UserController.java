package com.jsp.user_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.user_app.entity.User;
import com.jsp.user_app.service.UserService;
import com.jsp.user_app.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Validated @RequestBody User user) {
		ResponseStructure<User> responseStructure = userService.registerUser(user);
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	@GetMapping("/getUser")
	public ResponseEntity<?> getUserById(@RequestParam int userId) {
		 ResponseStructure<Optional<User>> responseStructure = userService.getUserById(userId);
		return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);

	}

	@GetMapping("/getAllUser")
	public ResponseEntity<?> getAllUser() {
		 ResponseStructure<List<User>> responseStructure = userService.getAllUser();
		 return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);

	}
	@PutMapping("/update")
	public  ResponseEntity<?> updateUser(@RequestBody User user,@RequestParam int userId) {
		  ResponseStructure<User> responseStructure = userService.updateUser(user,userId);
		  return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	@DeleteMapping("/delete") 
	public  ResponseEntity<?> deleteUser(@RequestParam int userId) {
		  ResponseStructure<String> responseStructure = userService.deleteUser(userId);
		  return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	@GetMapping("/getUserPage")
	public ResponseEntity<?> getUserByPage(@RequestParam int pageNo) {
		 ResponseStructure<Page<User>> responseStructure = userService.getUserByPage(pageNo);
		 return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
	}
	@GetMapping("/login") //localhost:8080/user/login?email=""&password=""
	public ResponseEntity<?> loginUser(@RequestParam String email,@RequestParam String password) {
		ResponseStructure<Optional<User>> responseStructure = userService.loginUser(email,password);
		return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
	}

}
/*  find http request codes
 1xx (Informational):
The request was received, and the server is continuing the process.
Example: 100 Continue (Indicates that the server has received the request headers and the client should proceed to send the request body).

2xx (Success):
The request was successfully received, understood, and accepted.
Example: 200 OK (The standard response for successful HTTP requests), 
		 201 Created (The request has been fulfilled and a new resource has been created).
		 
3xx (Redirection):
Further action needs +to be taken by the client to complete the request, often involving following a redirect to a different URL.
Example: 301 Moved Permanently (The requested resource has been permanently moved to a new URL),
 		 302 Found (The requested resource has temporarily moved to a different URL).
 		 
4xx (Client Error):
The request contains an error and cannot be fulfilled, often due to invalid syntax or a lack of necessary authentication/authorization.
Example: 400 Bad Request (The server could not understand the request due to malformed syntax),
 		 404 Not Found (The server cannot find the requested resource), 
 		 403 Forbidden (The server understood the request but refuses to authorize it).
 
5xx (Server Error):
The server failed to fulfill an apparently valid request due to an internal server issue.
Example: 500 Internal Server Error (A generic error message indicating an unexpected condition on the server), 
		 503 Service Unavailable (The server is currently unable to handle the request due to temporary overload or maintenance).
 * 
 */
