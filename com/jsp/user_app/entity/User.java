package com.jsp.user_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
//	@NotNull(message = "Name cannot be null")
//	@NotEmpty(message = "Name cannot be empty")
	@NotBlank(message = "Name cannot be blank")
	@Size(min=3, max=8, message = "Name must be between 3 and 8 characters")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
	private String userName;
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format")
	private String userEmail;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Password must be 8-20 characters long, contain at least one digit, one uppercase letter, one lowercase letter, one special character, and have no whitespace")
	private String userPassword;
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Contact number must be a valid 10-digit number starting with 6-9")
	private String contact;
	private String address;
}

/*Assignment -regular expression for email,password


/*
 * git init
 * git status --> master
 * git add .
 * git commit -m "first commit"
 * git push origin master
 * git remote add origin "url"
 * 
 * 
 * git config --global user.name "username"
 * git config--global user.email "
 * 
 * 
 * git config--global user.email "emailid"
 * 
 */
