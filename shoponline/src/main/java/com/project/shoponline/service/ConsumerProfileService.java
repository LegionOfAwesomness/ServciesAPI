package com.project.shoponline.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shoponline.dao.customer.ConsumerDataRepository;
import com.project.shoponline.dao.customer.ConsumerRepository;
import com.project.shoponline.dao.customer.ReferralsRepository;
import com.project.shoponline.dao.customer.UserRepository;
import com.project.shoponline.model.module1.Address;
import com.project.shoponline.model.module1.ApplicationStatus;
import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module1.MembershipType;
import com.project.shoponline.model.module1.Referrals;
import com.project.shoponline.model.module1.Role;
import com.project.shoponline.model.module1.RoleType;
import com.project.shoponline.model.module1.User;
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

	public Consumer getConsumerProfile(String email, String referralCode) {
		List<ConsumerData> consumerData = consumerDataRepository.findByEmailOrReferralCode(email, referralCode);
		if (!consumerData.isEmpty()) {
			return consumerData.get(0).getConsumer();
		} else
			return null;
	}

	public Consumer createConsumerProfile(Consumer consumer) throws Exception {
		Password passwordUtil = new Password();
		String encyptedPass = passwordUtil.getSaltedHash(consumer.getUsers().get(0).getPassword());
		consumer.getUsers().get(0).setPassword(encyptedPass);
		ConsumerData consumerData = consumer.getConsumerData();
		consumerData.setReferralCode(generateReferralCode(consumer.getFirstName()));
//		consumerData.setMembershipType(consumer.getConsumerData().);
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
		String upToNCharacters = firstName.substring(0, Math.min(firstName.length(), 5));
		String randomCode = (new RandomString(8)).nextString();
		// String randomCode = (new
		// RandomString(4+(5-upToNCharacters.length()))).nextString();
		// String randomCode = (new
		// RandomString(4+(5-firstName.length()))).nextString();
		// String referralCode= upToNCharacters+randomCode;
		String referralCode = randomCode;
		List<ConsumerData> cReferral = consumerDataRepository.findByReferralCode(referralCode);
		if (cReferral.isEmpty()) {
			System.out.println("referalcode**" + referralCode);
			return referralCode;
		} else
			return generateReferralCode(firstName);
	}

	public List<Referrals> getAllReferrals(String id) {
		ConsumerData cReferral= consumerDataRepository.findByConsumerDataId(Long.valueOf(id));
		return referralListRepository.findByConsumerData_consumerDataId(Long.valueOf(cReferral.getReferredBy()));
	}

	public Iterable<Consumer> getAllConsumers() {
		return consumerProfileRepository.findAll();
	}

	public Consumer getConsumerByConsumerID(String id) {
		return consumerProfileRepository.findConsumerByconsumerId(Long.valueOf(id));
	}
	
	public Consumer updateConsumerProfile(Consumer consumer) {
		Optional<Consumer> optionalConsumer = consumerProfileRepository.findById(consumer.getConsumerId());
		if (optionalConsumer.isPresent()) {
			Optional.ofNullable(consumer.getFirstName()).ifPresent(usr->optionalConsumer.get().setFirstName(consumer.getFirstName()));
			Optional.ofNullable(consumer.getLastName()).ifPresent(usr->optionalConsumer.get().setLastName(consumer.getLastName()));
			Optional.ofNullable(consumer.getPhoneNumber()).ifPresent(usr->optionalConsumer.get().setPhoneNumber(consumer.getPhoneNumber()));
			
			if(null!=consumer.getAddress()) {
				Address address= optionalConsumer.get().getAddress();
//				adress.set
				Optional.ofNullable(consumer.getAddress().getLine1()).ifPresent(usr->address.setLine1(consumer.getAddress().getLine1()));
				Optional.ofNullable(consumer.getAddress().getLine2()).ifPresent(usr->address.setLine2(consumer.getAddress().getLine2()));
				Optional.ofNullable(consumer.getAddress().getCity()).ifPresent(usr->address.setCity(consumer.getAddress().getCity()));
				Optional.ofNullable(consumer.getAddress().getState()).ifPresent(usr->address.setState(consumer.getAddress().getState()));
				Optional.ofNullable(consumer.getAddress().getZipcode()).ifPresent(usr->address.setZipcode(consumer.getAddress().getZipcode()));
				Optional.ofNullable(consumer.getAddress().getCountry()).ifPresent(usr->address.setCountry(consumer.getAddress().getCountry()));
				optionalConsumer.get().setAddress(address);
				
			}
			
			return consumerProfileRepository.save(optionalConsumer.get());
		}
		return null;
	}

	public ConsumerData updateStatusConsumerProfile(String id) {
		Consumer consumer = consumerProfileRepository.findConsumerByconsumerId(Long.valueOf(id));
		if (null != consumer) {
			ConsumerData consumerData = consumer.getConsumerData();
			consumerData.setMembershipType(MembershipType.EXCLUSIVE);
			return consumerDataRepository.save(consumerData);
		}
		return null;
	}

	public ConsumerData getConsumerDataByID(String id) {
		return consumerDataRepository.findByConsumerDataId(Long.valueOf(id));
	}

	public void removeConsumerByConsumerID(String id) {
		consumerProfileRepository.deleteById(Long.valueOf(id));
	}

	public Role addUserAndRoleData(Consumer consumer) {
		User user = consumer.getUsers().get(0);
		List<Role> roles = new ArrayList();
		List<User> users = new ArrayList();
		users.add(consumer.getUsers().get(0));
		Role role = new Role();
		role.setRoleType(RoleType.CONSUMER);
		role.setUsers(users);
		roles.add(role);
		return role;
	}
}
