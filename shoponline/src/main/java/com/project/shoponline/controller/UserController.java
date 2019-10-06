package com.project.shoponline.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shoponline.model.module1.User;
import com.project.shoponline.model.module3.Advertiser;
import com.project.shoponline.model.module3.CJPI;
import com.project.shoponline.service.AdvertiserLookupService;
import com.project.shoponline.service.EmailService;
import com.project.shoponline.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	EmailService emailservive;

	@Autowired
	AdvertiserLookupService advertiserLookupService;

	@RequestMapping(value = "/getUser/{userID}", method = RequestMethod.GET)
	public Optional<User> getUserByID(@PathVariable String userID) {
		return userService.getUserByUserID(userID);
	}

	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public User getUser(@RequestBody User user) throws Exception {
		return userService.verifyUserLogin(user);
	}

	@RequestMapping(value = "/updateUser/{type}", method = RequestMethod.PATCH)
	public void updateUserData(@RequestBody User user, @PathVariable String type ) throws Exception {
		userService.updateUserData(user , type);
	}

	@RequestMapping(value = "/verifyEmailAddress", method = RequestMethod.GET)
	public String verifyEmailAddress(@RequestParam("Context") String context) throws Exception {
		return emailservive.verifyEmailAddress(context);
	}
	
	@RequestMapping(value = "/pwdrestConfirm", method = RequestMethod.GET)
	public String pwdrestConfirm(@RequestParam("Context") String context) throws Exception {
		return emailservive.confirmPasswordReset(context);
	}

	
}
