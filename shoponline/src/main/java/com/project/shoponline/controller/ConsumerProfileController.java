package com.project.shoponline.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module1.Referrals;
import com.project.shoponline.model.module1.Role;
import com.project.shoponline.model.module1.RoleType;
import com.project.shoponline.model.module1.User;
import com.project.shoponline.model.module1.UserStatus;
import com.project.shoponline.model.module2.Account;
import com.project.shoponline.service.ConsumerProfileService;
import com.project.shoponline.service.EmailService;
import com.project.shoponline.service.UserService;

@RestController
public class ConsumerProfileController {

	@Autowired
	ConsumerProfileService consumerService;

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	private static Logger log = LoggerFactory.getLogger(ConsumerProfileController.class);
 
	@RequestMapping(value = "/passwordreset/{email}", method = RequestMethod.GET)
	public void resetPassword(@PathVariable("email") String email) {
		Consumer consumer = consumerService.getConsumerProfile(email, email);
		System.err.println(consumer);
		try {
			emailService.passwordLinkToVerify(consumer);
			emailService.sendEmailToConfirm(consumer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//emailService.sendEmailToVerify(consumer);*/
		
	}
	
	@RequestMapping(value = "/searchConsumerReferral/{id}", method = RequestMethod.GET)
	public Consumer getConsumerData(@PathVariable("id") String id) {
		return consumerService.getConsumerProfile(id, id);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testController() {
		log.info("---------------Controller-----------");
		return "service Deployed";
	}

	@RequestMapping(value = "/addConsumerProfile/{referrer}", method = RequestMethod.POST)
	public void createConsumerProfile(@RequestBody Consumer consumer, @PathVariable("referrer") String referrer)
			throws Exception {
		consumer.getConsumerData().setReferredBy(referrer);
		consumer.getConsumerData().setReferrerConsumerData(consumerService.getConsumerDataByID(referrer));
		createUser(consumer);
		ConsumerData consumerData = consumer.getConsumerData();
		consumerData.setConsumer(consumer);
		consumer.setConsumerData(consumerData);
		List<Account> accounts = consumer.getAccounts();
		for (Account acc : accounts) {
			acc.setConsumer(consumer);
		}
		consumer.setAccounts(accounts);
		Consumer consumerProfile = consumerService.createConsumerProfile(consumer);
		consumerService.addToReferralList(consumerProfile, referrer);
		emailService.createLinkToVerify(consumer);
		emailService.sendEmailToVerify(consumer);
	}

//	@RequestMapping(value = "/getMyReferrals/{id}", method = RequestMethod.GET)//get consumers a member referred
//	public List<Referrals> getAllReferralsforCustomer(@PathVariable("id") String id) {
//		return consumerService.getAllReferrals(id);
//	}
	@RequestMapping(value = "/getMyReferralsChain/{id}", method = RequestMethod.GET)//get the ladder upward a consumer refered by
	public List<Referrals> getReferrersAConsumerReferredByUpWard(@PathVariable("id") String id) {
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

	@RequestMapping(value = "/removeConsumerProfile/{id}", method = RequestMethod.DELETE)
	public String removeConsumerProfile(@PathVariable("id") String id) {
		consumerService.removeConsumerByConsumerID(id);
		return "Consumer Profile is Deleted!";
	}

	@RequestMapping(value = "/updateStatusConsumerProfileStatus/{id}", method = RequestMethod.PATCH)
	public ConsumerData updateStatusConsumerProfileStatus(@PathVariable("id") String id) {
		return consumerService.updateStatusConsumerProfile(id);
	}

	@RequestMapping(value = "/updateConsumerProfile", method = RequestMethod.PATCH)
	public Consumer updateStatusConsumerProfile(@RequestBody Consumer consumer) {
		return consumerService.updateConsumerProfile(consumer);
	}
	public void createUser(Consumer consumer) {
		User user = consumer.getUsers().get(0);
		consumer.getUsers().get(0).setUserStatus(UserStatus.PENDING);
		List<Role> roles = new ArrayList<Role>();
		List<User> users = new ArrayList<User>();
		users.add(consumer.getUsers().get(0));
		Role role = new Role();
		role.setRoleType(RoleType.CONSUMER);
		role.setUsers(users);
		roles.add(role);
		user.setRole(roles);
		user.setConsumer(consumer);
		user.setEmail(consumer.getConsumerData().getEmail());
		consumer.getUsers().get(0).setRole(roles);
	}

	public void populateConsumerProfileOnStartup(Consumer consumer) throws Exception {
		System.out.println("eeeeeeeeeeeee");
		consumer.getConsumerData().setReferredBy("11111");
		createUser(consumer);
		ConsumerData consumerData = consumer.getConsumerData();
		consumerData.setConsumer(consumer);
		consumer.setConsumerData(consumerData);
		List<Account> accounts = consumer.getAccounts();
		for (Account acc : accounts) {
			acc.setConsumer(consumer);
		}
		consumer.setAccounts(accounts);
		Consumer consumerProfile = consumerService.createConsumerProfile(consumer);
		consumerService.addToReferralList(consumerProfile, "11111");
	}
}
