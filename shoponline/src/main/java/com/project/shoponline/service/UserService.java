package com.project.shoponline.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.shoponline.dao.customer.UserRepository;
import com.project.shoponline.model.ApplicationStatus;
import com.project.shoponline.model.Consumer;
import com.project.shoponline.model.ConsumerData;
import com.project.shoponline.model.User;
import com.project.shoponline.utils.Password;


@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private RoleRepository roleRepository;

	public List<User> getUserByUserNameOrEmail(String user){
//		Optional<User> optionalUser = userRepository.findByUserNameOrEmail(user, user);
//		if (!optionalUser.isPresent()) {
//			return null;
//		}
//		return optionalUser.get();
		return userRepository.findByUserNameOrEmail(user, user);
	}
	//can also be userd for update
	public User createUserData(User user){
		System.out.println("USER___________"+user.toString());
		return userRepository.save(user);
	}
	
	public User updateUserData(User user) throws Exception {
		Password passwordUtil = new Password();
		Optional<User> optionalUser = userRepository.findByUserId(user.getUserId());
		if (optionalUser.isPresent()) {
			optionalUser.get().setEmail(user.getEmail());
			String encyptedPass =  passwordUtil.getSaltedHash(user.getPassword());
			optionalUser.get().setPassword(encyptedPass);
			optionalUser.get().setUserStatus(user.getUserStatus());
			return userRepository.save(optionalUser.get());
		}
		return null;
	}
	
	public User verifyUserLogin(User user) throws Exception{
//		@Autowired
		Password passwordUtil = new Password();
		List<User> users = userRepository.findByUserNameOrEmail(user.getUserName(), user.getEmail());
		if(!users.isEmpty()) {
				if(passwordUtil.check(user.getPassword(), users.get(0).getPassword())) {
					User verfiedUser = new User();
					Consumer consumer = new Consumer (); 
					consumer.setConsumerId(users.get(0).getConsumer().getConsumerId());
					consumer.setFirstName(users.get(0).getConsumer().getFirstName());
					verfiedUser.setUserId(users.get(0).getUserId());
					verfiedUser.setUserName(users.get(0).getUserName());
					verfiedUser.setUserStatus(users.get(0).getUserStatus());
					verfiedUser.setConsumer(consumer);
					return verfiedUser;
				}
				else  throw new EntityNotFoundException("The password is not correct.");
		}
		else  throw new EntityNotFoundException("There is No Account associated with the Usrname or Email.");
	}
	
	
}
