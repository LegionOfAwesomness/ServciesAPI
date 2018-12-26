package com.project.shoponline.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.shoponline.mail.AmazonEmail;
import com.project.shoponline.mail.SESFrom;
import com.project.shoponline.mail.SESProcessor;
import com.project.shoponline.model.Consumer;
import com.project.shoponline.model.User;
import com.project.shoponline.service.EmailService;
import com.project.shoponline.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailservive;

//	@RequestMapping(value = "/user/get", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<String> findUser(Model model, @RequestParam String userName) {
//
//		String user = new Gson().toJson(userService.getUserByUserName(userName));
//		final HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		return new ResponseEntity<String>(user, httpHeaders, HttpStatus.OK);
//
//	}

	@RequestMapping(value = "/getUser/{user}", method = RequestMethod.GET)
	public List<User> getUser(@PathVariable String user) {
		return userService.getUserByUserNameOrEmail(user);
	}
	
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public User getUser(@RequestBody User user) throws Exception {
		return userService.verifyUserLogin(user);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.PATCH)
	public void updateUserData(@RequestBody User user) throws Exception{
		userService.updateUserData(user);
	}
	
	@RequestMapping(value = "/verifyEmailAddress", method = RequestMethod.GET)
	public String  verifyEmailAddress(@RequestParam("Context") String context) {
		return emailservive.verifyEmailAddress(context);
	}
	
	}
