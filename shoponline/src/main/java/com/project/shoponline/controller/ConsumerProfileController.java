package com.project.shoponline.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.shoponline.mail.AmazonEmail;
import com.project.shoponline.mail.SESFrom;
import com.project.shoponline.mail.SESProcessor;
import com.project.shoponline.model.Consumer;
import com.project.shoponline.model.ConsumerData;
import com.project.shoponline.model.Mail;
import com.project.shoponline.model.Referrals;
import com.project.shoponline.model.Role;
import com.project.shoponline.model.RoleType;
import com.project.shoponline.model.User;
import com.project.shoponline.model.UserStatus;
import com.project.shoponline.service.ConsumerProfileService;
import com.project.shoponline.service.EmailService;
import com.project.shoponline.service.UserService;
import com.project.shoponline.utils.Password;

@RestController
public class ConsumerProfileController {

	@Autowired
	ConsumerProfileService consumerService;
	
	 @Autowired
	 UserService userService;
	 
	 @Autowired
	 EmailService emailService;
	 
	 private static Logger log = LoggerFactory.getLogger(ConsumerProfileController.class);
	
	@RequestMapping(value = "/searchConsumerReferral/{id}", method = RequestMethod.GET)
	public List<ConsumerData> getConsumerData(@PathVariable("id") String id) {
		return consumerService.getConsumerData(id, id);
	}

	@RequestMapping(value = "/addConsumerProfile/{referrer}", method = RequestMethod.POST)
	public void createConsumerProfile(@RequestBody Consumer consumer, @PathVariable("referrer") String referrer)
			throws Exception {
		consumer.getConsumerData().setReferredBy(referrer);
		createUser(consumer);
		Consumer consumerProfile = consumerService.createConsumerProfile(consumer);
//		userService.createUserData(user);
		consumerService.addToReferralList(consumerProfile, referrer);
		emailService.createLinkToVerify(consumer);
//		emailService.sendEmailToVerify(consumer);
	}

	@RequestMapping(value = "/getAllReferrals/{id}", method = RequestMethod.GET)
	public List<Referrals> getAllReferralsforCustomer(@PathVariable("id") String id) {
		return consumerService.getAllReferrals(id);
	}

	@RequestMapping(value = "/getAllConsumers", method = RequestMethod.GET)
	public Iterable<Consumer> getAllConsumers() {
		return consumerService.getAllConsumers();
	}

	@RequestMapping(value = "/getConsumer/{id}", method = RequestMethod.GET)
	public Consumer getConsumerByConsumerID(@PathVariable("id") String id) {
		return consumerService.getConsumerByConsumerID(id);
	}
	
	@RequestMapping(value = "/updateStatusConsumerProfile/{id}", method = RequestMethod.PATCH)
	public ConsumerData updateStatusConsumerProfile(@PathVariable("id") String id) {
		return consumerService.updateStatusConsumerProfile(id);
	}
	
public void createUser(Consumer consumer) {
	User user = consumer.getUser();
	consumer.getUser().setUserStatus(UserStatus.PENDING);
	List<Role> roles = new ArrayList();
	List<User> users = new ArrayList();
	users.add(consumer.getUser());
	Role role = new Role();
	role.setRoleType(RoleType.CONSUMER);
	role.setUsers(users);
	roles.add(role);
	user.setRole(roles);
	user.setConsumer(consumer);
	consumer.getUser().setRole(roles);
}
	
}
