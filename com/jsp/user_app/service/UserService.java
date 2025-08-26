package com.jsp.user_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jsp.user_app.dao.UserDao;
import com.jsp.user_app.entity.User;
import com.jsp.user_app.exception.EmailNotFound;
import com.jsp.user_app.exception.IdNotFoundException;
import com.jsp.user_app.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseStructure<User> registerUser(User user) {
		User user2 = userDao.registerUser(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setData(user2);
		responseStructure.setTimeStamp(LocalDateTime.now());
		responseStructure.setStatusCode(201);
		responseStructure.setMessage("User Record successfully");

		return responseStructure;

	}

	public ResponseStructure<Optional<User>> getUserById(int userId) {
		Optional<User> optional = userDao.getUserById(userId);
		if (optional.isPresent()) {
			ResponseStructure<Optional<User>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(optional);
			responseStructure.setTimeStamp(LocalDateTime.now());
			responseStructure.setStatusCode(302);
			responseStructure.setMessage("User Record Found successfully");

			return responseStructure;
		} else {
			throw new IdNotFoundException("Id " + userId + " Not Found");
		}

	}

	public ResponseStructure<List<User>> getAllUser() {

		List<User> allUser = userDao.getAllUser();
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		responseStructure.setData(allUser);
		responseStructure.setTimeStamp(LocalDateTime.now());
		responseStructure.setStatusCode(302);
		responseStructure.setMessage("All User Record Found successfully");

		return responseStructure;

	}

	public ResponseStructure<User> updateUser(User user, int userId) {
		User user2 = userDao.updateUser(user, userId);
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setData(user2);
		responseStructure.setTimeStamp(LocalDateTime.now());
		responseStructure.setStatusCode(200);
		responseStructure.setMessage("All User Record Found successfully");

		return responseStructure;
	}

	public ResponseStructure<String> deleteUser(int userId) {
		String msg = userDao.deleteUser(userId);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setData(msg);
		responseStructure.setTimeStamp(LocalDateTime.now());
		responseStructure.setStatusCode(200);
		responseStructure.setMessage(" User Record Deleted successfully");

		return responseStructure;
	}

	public ResponseStructure<Page<User>> getUserByPage(int pageNo) {
		Page<User> page = userDao.getUserByPage(pageNo);
		ResponseStructure<Page<User>> responseStructure = new ResponseStructure<>();
		responseStructure.setData(page);
		responseStructure.setTimeStamp(LocalDateTime.now());
		responseStructure.setStatusCode(302);
		responseStructure.setMessage(" User Record Found ");

		return responseStructure;
	}

	public ResponseStructure<Optional<User>> loginUser(String email, String password) {
		Optional<User> optional = userDao.loginUser(email);
		if(optional.isPresent())
		{
		//	String userEmail = optional.get().getUserEmail(); //db email
			String userPassword = optional.get().getUserPassword(); // db pass
			if(password.equals(userPassword)) {
				ResponseStructure<Optional<User>> responseStructure = new ResponseStructure<>();
				responseStructure.setData(optional);
				responseStructure.setTimeStamp(LocalDateTime.now());
				responseStructure.setStatusCode(200);
				responseStructure.setMessage(" User login success ");

				return responseStructure;
				
			}
		}
		throw new EmailNotFound("Invalid Email");
	}

}
