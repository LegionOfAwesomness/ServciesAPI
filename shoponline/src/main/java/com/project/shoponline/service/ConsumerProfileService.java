package com.project.shoponline.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shoponline.dao.customer.ConsumerRepository;
import com.project.shoponline.dao.customer.ConsumerDataRepository;
import com.project.shoponline.dao.customer.ReferralsRepository;
import com.project.shoponline.dao.customer.UserRepository;
import com.project.shoponline.model.ApplicationStatus;
import com.project.shoponline.model.Consumer;
import com.project.shoponline.model.ConsumerData;
import com.project.shoponline.model.Referrals;
import com.project.shoponline.model.Role;
import com.project.shoponline.model.RoleType;
import com.project.shoponline.model.User;
import com.project.shoponline.model.UserStatus;
import com.project.shoponline.utils.Password;
import com.project.shoponline.utils.RandomString;

@Service
public class ConsumerProfileService {

	@Autowired
	ConsumerDataRepository consumerDataRepository;
	
	@Autowired
	ConsumerRepository consumerProfileRepository;
	
	@Autowired
	ReferralsRepository referralListRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<ConsumerData> getConsumerData(String email, String referralCode) {
		List<ConsumerData> consumerData = consumerDataRepository.findByEmailOrReferralCode(email, referralCode);
	if(!consumerData.isEmpty()) {
		return consumerData;
	}else 
		throw new EntityNotFoundException("The Referral code or the email doesn't exist in our database.");
	}

	public Consumer createConsumerProfile(Consumer consumer) throws Exception {
		Password passwordUtil= new Password();
		String encyptedPass =  passwordUtil.getSaltedHash(consumer.getUser().getPassword());
		consumer.getUser().setPassword(encyptedPass);
//		if (null != consumer.getConsumerData()) {
			ConsumerData consumerData = consumer.getConsumerData();
			consumerData.setReferralCode(generateReferralCode(consumer.getFirstName()));
			consumerData.setApplicationStatus(ApplicationStatus.PENDING);
//		} else {
//			throw new NullPointerException("Referral Code is required");
//		}
		return consumerProfileRepository.save(consumer);
	}

	public void addToReferralList(Consumer referredConsumer, String referrer) {
		Referrals referrals = new Referrals();
		ConsumerData consumerReferrer = new ConsumerData();
		consumerReferrer.setConsumerDataId(Long.valueOf(referrer));
		referrals.setConsumerData(consumerReferrer);
		referrals.setReferralCode(referredConsumer.getConsumerData().getReferralCode());
		referralListRepository.save(referrals);
	}

	private String generateReferralCode(String firstName) {
		String upToNCharacters= firstName.substring(0, Math.min(firstName.length(), 5));
		String randomCode = (new RandomString(4+(5-upToNCharacters.length()))).nextString();
		String referralCode= upToNCharacters+randomCode;
		List<ConsumerData> cReferral = consumerDataRepository.findByReferralCode(referralCode);
		if (cReferral.isEmpty()) {
			System.out.println("referalcode**" + referralCode);
			return referralCode;
		} else
			return generateReferralCode(firstName);
	}

	public List<Referrals> getAllReferrals(String id) {
		return referralListRepository.findByConsumerData_consumerDataId(Long.valueOf(id));
	}

	public Iterable<Consumer> getAllConsumers() {
		return consumerProfileRepository.findAll();
	}

	public Consumer getConsumerByConsumerID(String id) {
		return consumerProfileRepository.findByconsumerId(Long.valueOf(id));
	}
	
	public ConsumerData updateStatusConsumerProfile(String id) {
		Consumer consumer = consumerProfileRepository.findByconsumerId(Long.valueOf(id));
		if(null!= consumer ) {
			ConsumerData consumerData = consumer.getConsumerData();
			consumerData.setApplicationStatus(ApplicationStatus.VERIFIED);
			return consumerDataRepository.save(consumerData);
		}
		return null;
	}

	public Role addUserAndRoleData(Consumer consumer) {
		User user = consumer.getUser();
//		consumer.getUser().setUserStatus(UserStatus.PENDING);
		List<Role> roles = new ArrayList();
		List<User> users = new ArrayList();
		users.add(consumer.getUser());
		Role role = new Role();
		role.setRoleType(RoleType.CONSUMER);
		role.setUsers(users);
		roles.add(role);
		return role;
//		users.add(roles);

//		user.setConsumer(consumer);
//		consumer.getUser().setRole(roles);
//		roleService.save(role);
	}
}
