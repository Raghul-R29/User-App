package com.jsp.user_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.jsp.user_app.entity.User;
import com.jsp.user_app.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User registerUser(User user) {
		User user2=userRepository.save(user);
		return user2;
	}

	public Optional<User> getUserById(int userId) {
		Optional<User> optional = userRepository.findById(userId);
		return optional;
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User updateUser(User user, int userId) {
		Optional<User> optional= userRepository.findById(userId);
		if(optional.isPresent())
		{
			user.setUserId(userId);
			return userRepository.save(user);
		}
		else {
			throw new IllegalArgumentException("Invalid user id");
		}
	}

	public String deleteUser(int userId) {
		Optional<User> optional= userRepository.findById(userId);
		if(optional.isPresent())
		{
			User user = optional.get();
			userRepository.delete(user);
		// userRepository.deleteById(userId);
			return "user data deleted";
		}
		else {
			throw new IllegalArgumentException("Invalid user id");
		}
		
	}

	public Page<User> getUserByPage(int pageNo) {
		PageRequest pageRequest  = PageRequest.of(pageNo, 10);
		 Page<User> page= userRepository.findAll(pageRequest);
		return page;
	}

	public Optional<User> loginUser(String email) {
		 return userRepository.findByUserEmail(email);
		
	}

}
