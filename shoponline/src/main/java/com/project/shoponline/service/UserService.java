package com.project.shoponline.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.shoponline.dao.customer.UserRepository;
import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module1.User;
import com.project.shoponline.model.module1.UserStatus;
import com.project.shoponline.utils.Password;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	// @Autowired
	// private RoleRepository roleRepository;

	public List<User> getUserByUserNameOrEmail(String user) {
		return userRepository.findByUserNameOrEmail(user, user);
	}
	public Optional<User> getUserByUserID(String userID) {
		return userRepository.findById(Long.parseLong(userID));
	}
	

	public User createUserData(User user) {
		return userRepository.save(user);
	}

	public User updateUserData(User user , String type) throws Exception {
		Password passwordUtil = new Password();
		//Optional<User> optionalUser = userRepository.findById(user.getUserId());
		List<User> userList = userRepository.findByUserNameOrEmail("", user.getEmail());
		System.err.println(userList);
		if(type.equalsIgnoreCase("PasswordReset")) {
			System.err.println("inside");
			if(!userList.isEmpty()) {
				Optional.ofNullable(user.getEmail()).ifPresent(usr->userList.get(0).setEmail(user.getEmail()));
				Optional.ofNullable(user.isPwdReset()).ifPresent(usr->userList.get(0).setPwdReset(false));				
				if(null!=user.getPassword()) {
					String encyptedPass = passwordUtil.getSaltedHash(user.getPassword());
					userList.get(0).setPassword(encyptedPass);
				}
				Optional.ofNullable(user.getUserStatus()).ifPresent(usr->userList.get(0).setUserStatus(user.getUserStatus()));
				System.err.println(userList.get(0));
				return userRepository.save(userList.get(0));			
			}	
		}

//		if (optionalUser.isPresent()) {
//			Optional.ofNullable(user.getEmail()).ifPresent(usr->optionalUser.get().setEmail(user.getEmail()));
//			Optional.ofNullable(user.getUserName()).ifPresent(usr->optionalUser.get().setUserName(user.getUserName()));
//			
//			if(null!=user.getPassword()) {
//				String encyptedPass = passwordUtil.getSaltedHash(user.getPassword());
//				optionalUser.get().setPassword(encyptedPass);
//			}
//			Optional.ofNullable(user.getUserStatus()).ifPresent(usr->optionalUser.get().setUserStatus(user.getUserStatus()));
//			
//			return userRepository.save(optionalUser.get());
//		}
		return null;
	}

	public User verifyUserLogin(User user) throws Exception {
		Password passwordUtil = new Password();
		List<User> users = userRepository.findByUserNameOrEmail(user.getUserName(), user.getEmail());
		if (!users.isEmpty()) {
			if (passwordUtil.check(user.getPassword(), users.get(0).getPassword())) {
				// check of the account is verified
				checkForVerificationStatu(users.get(0));
				User verfiedUser = new User();
				Consumer consumer = new Consumer();
				consumer.setConsumerId(users.get(0).getConsumer().getConsumerId());
				consumer.setFirstName(users.get(0).getConsumer().getFirstName());
				verfiedUser.setUserId(users.get(0).getUserId());
				verfiedUser.setUserName(users.get(0).getUserName());
				verfiedUser.setUserStatus(users.get(0).getUserStatus());
				verfiedUser.setConsumer(consumer);
				return verfiedUser;
			} else
				throw new EntityNotFoundException("The password is not correct.");
		} else
			throw new EntityNotFoundException("There is No Account associated with the Usrname or Email.");
	}

	private void checkForVerificationStatu(User user) {
		if (UserStatus.PENDING.equals(user.getUserStatus())) {
			throw new EntityNotFoundException("Please check you email and verifiy your account.");
		}
		if (UserStatus.LOCKED.equals(user.getUserStatus())) {
			throw new EntityNotFoundException("Your Account is locked!");
		}
	}

}
