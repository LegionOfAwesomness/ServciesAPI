package com.project.shoponline.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shoponline.dao.customer.ConsumerDataRepository;
import com.project.shoponline.dao.customer.ConsumerRepository;
import com.project.shoponline.dao.customer.KeyTimeStampRepository;
import com.project.shoponline.dao.customer.UserRepository;
import com.project.shoponline.mail.AmazonEmail;
import com.project.shoponline.mail.SESFrom;
import com.project.shoponline.mail.SESProcessor;
import com.project.shoponline.model.module1.ApplicationStatus;
import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module1.KeyTimeStamp;
import com.project.shoponline.model.module1.User;
import com.project.shoponline.model.module1.UserStatus;
import com.project.shoponline.utils.Password;

@Service
public class EmailService {

	@Autowired
	private KeyTimeStampRepository keyTimeStampRepository;
	@Autowired
	private ConsumerRepository consumerRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConsumerDataRepository consumerDataRepository;
	

	private static SecretKey key;
  
	public void createLinkToVerify(Consumer consumer) throws Exception {
		System.out.println("REFERALLL**" + consumer.getConsumerData().getReferralCode());
		KeyTimeStamp keyTimeStamp = new KeyTimeStamp();
		keyTimeStamp.setExpirationTime("24");
		keyTimeStamp.setKey(consumer.getConsumerData().getReferralCode());
		keyTimeStamp.setTimeStamp(LocalDateTime.now());
		keyTimeStamp.setUser(consumer.getUsers().get(0));
		keyTimeStampRepository.save(keyTimeStamp);
	}
	


	public String sendEmailToVerify(Consumer consumer) throws Exception {
		Password passwordUtil = new Password();
		passwordUtil.generateKey(key);
		String encyptedCode  = passwordUtil.encryptString(consumer.getConsumerData().getReferralCode());
//		String encyptedCode = passwordUtil.getSaltedHash(consumer.getConsumerData().getReferralCode());
//		System.out.println("Verify Link****"+ "/verifyEmailAddress?Context=" + encyptedCode );
		System.out.println("Verify Link****"+ "/verifyEmailAddress?Context=" + consumer.getConsumerData().getReferralCode() );
		System.out.println("Verify Link****"+ "/verifyEmailAddress?Context=" + encyptedCode );
		
//		// send Email using ATTA from email
		SESProcessor.getInstance().add(new AmazonEmail(consumer.getConsumerData().getEmail(), SESFrom.ATTA, "kewlwallet",
				"<p><strong><span style=\"color: #0000ff;\">---- THIS IS FOR TEST PURPOSE, PLEASE IGNORE----</span></strong></p>\n"
						+ "<p>Dear Kewlwallet Customer,</p>\n"
						+ "<p>We have received a request to use this email to sign up for shoponline.kewlwallet.com account.</p>\n"
						+ "<p>If you requested this verification, please go to the following URL to confirm that you are authorized to use this email address:</p>\n"
						+ "<p>http://sandbox.kewlwallet.com:8080/serviceapi/verifyEmailAddress?Context=" + encyptedCode + "</p>\n"
						+ "<p>Your sign up process will not be processed unless you confirm through this URL. This link expires 24 hours after your original verification request.</p>\n"
						+ "<p>If you did NOT request to sign up to shoponline.kewlwallet.com do not click on the link.</p>\n"
						+ "<p>To learn more about our site please visit http://shoponline.kewlwallet.com</p>\n"
						+ "<p>Sincerely,</p>\n" + "<p>The Kewlwallet Team</p>"));

		return "Emails Sent!";
	}
	
	public String sendEmailToConfirm(Consumer consumer) throws Exception {
		Password passwordUtil = new Password();
		passwordUtil.generateKey(key);
		String encyptedCode  = passwordUtil.encryptString(consumer.getConsumerData().getReferralCode());
//		String encyptedCode = passwordUtil.getSaltedHash(consumer.getConsumerData().getReferralCode());
//		System.out.println("Verify Link****"+ "/verifyEmailAddress?Context=" + encyptedCode );
		System.out.println("Verify Link****"+ "/verifyEmailAddress?Context=" + consumer.getConsumerData().getReferralCode() );
		System.out.println("Verify Link****"+ "/verifyEmailAddress?Context=" + encyptedCode );
		
//		// send Email using ATTA from email
		SESProcessor.getInstance().add(new AmazonEmail(consumer.getConsumerData().getEmail(), SESFrom.ATTA, "kewlwallet",
				"<p><strong><span style=\"color: #0000ff;\">---- THIS IS FOR TEST PURPOSE, PLEASE IGNORE----</span></strong></p>\n"
						+ "<p>Dear Kewlwallet Customer,</p>\n"
						+ "<p>We have received a request to use this email to sign up for shoponline.kewlwallet.com account.</p>\n"
						+ "<p>If you requested this verification, please go to the following URL to confirm that you are authorized to use this email address:</p>\n"
						+ "<p>http://localhost:8080/pwdrestConfirm?Context=" + encyptedCode + "</p>\n"
						+ "<p>Your sign up process will not be processed unless you confirm through this URL. This link expires 1 hour after your original verification request.</p>\n"
						+ "<p>If you did NOT request to sign up to shoponline.kewlwallet.com do not click on the link.</p>\n"
						+ "<p>To learn more about our site please visit http://shoponline.kewlwallet.com</p>\n"
						+ "<p>Sincerely,</p>\n" + "<p>The Kewlwallet Team</p>"));

		return "Emails Sent!";
	}
	
	public String  verifyEmailAddress(String context ) throws Exception{
//		Password passwordUtil = new Password();
//		passwordUtil.check(password, stored)
		Password passwordUtil = new Password();
		String decyptedCode = passwordUtil.decryptString(context);
		System.out.println("decrypted----------"+decyptedCode);
		List<KeyTimeStamp> KeyTimeStampList = keyTimeStampRepository.findByKeyGenerated(decyptedCode);
		if(!KeyTimeStampList.isEmpty()) {
			LocalDateTime fromDateTime= KeyTimeStampList.get(0).getTimeStamp();
			LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

			long hours = tempDateTime.until( LocalDateTime.now(), ChronoUnit.HOURS);
			if(hours<24) {
				updateUserApplicationStatus(KeyTimeStampList.get(0));
				updateConsumerApplicationStatus(KeyTimeStampList.get(0));
				return "Your Email is succesfully verfied. You can now go on and login to your kewlwallet accunt. Thanks!";
			}
			 return "the link is already expired. Please ceate a new link on the sign up page.";
		}
		 return "the link you provide is not correct.";
	}
	
	
	public String  confirmPasswordReset(String context ) throws Exception{
//		Password passwordUtil = new Password();
//		passwordUtil.check(password, stored)
		Password passwordUtil = new Password();
		String decyptedCode = passwordUtil.decryptString(context);
		System.out.println("decrypted----------"+decyptedCode);
		List<KeyTimeStamp> KeyTimeStampList = keyTimeStampRepository.findByKeyGenerated(decyptedCode);
		if(!KeyTimeStampList.isEmpty()) {
			LocalDateTime fromDateTime= KeyTimeStampList.get(0).getTimeStamp();
			LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

			long hours = tempDateTime.until( LocalDateTime.now(), ChronoUnit.HOURS);
			System.err.println(hours);
	//		if(hours<24) {
				//updateUserApplicationStatus(KeyTimeStampList.get(0));
				updateUserPWDReset(KeyTimeStampList.get(0));
				return "Your Email is succesfully verfied. You can now go on and login to your kewlwallet accunt. Thanks!" + "http://localhost:3000/change-password";
	//		}
	//		 return "the link is already expired. Please ceate a new link on the Reset Password page.";
		}
		return "the link you provide is not correct.";
	}
	
	
	public void updateConsumerApplicationStatus(KeyTimeStamp keyTimeStamp){
		long userID = keyTimeStamp.getUser().getUserId();
		Optional<User> user = userRepository.findById(userID);
		if(user.isPresent()) {
			Consumer consumer = consumerRepository.findConsumerByconsumerId(user.get().getConsumer().getConsumerId());
			long consumerDataId= consumer.getConsumerData().getConsumerDataId();
			ConsumerData consumerData = consumerDataRepository.findByConsumerDataId(consumerDataId);
		//	consumerData.setMembershipType(ApplicationStatus.VERIFIED);
			consumerDataRepository.save(consumerData);
			
		}
		
	}
	
	public void updateUserApplicationStatus(KeyTimeStamp keyTimeStamp){
		long userID = keyTimeStamp.getUser().getUserId();
		Optional<User> user = userRepository.findById(userID);
		if(user.isPresent()) {
			User userVerified = user.get();
			userVerified.setUserStatus(UserStatus.ACTIVE);
			userRepository.save(userVerified);
		}
		
	}
	
	public void passwordLinkToVerify(Consumer consumer) throws Exception {
		System.out.println("REFERALLL**" + consumer.getConsumerData().getEmail());
		KeyTimeStamp keyTimeStamp = new KeyTimeStamp();
		keyTimeStamp.setExpirationTime("24");
		keyTimeStamp.setKey(consumer.getConsumerData().getEmail());
		keyTimeStamp.setTimeStamp(LocalDateTime.now());
		keyTimeStamp.setUser(consumer.getUsers().get(0));
		keyTimeStampRepository.save(keyTimeStamp);
	}
	
	
	
	
	public void updateUserPWDReset(KeyTimeStamp keyTimeStamp){
		long userID = keyTimeStamp.getUser().getUserId();
		Optional<User> user = userRepository.findById(userID);
		if(user.isPresent()) {
			User userVerified = user.get();
			userVerified.setPwdReset(true);
			userRepository.save(userVerified);
		}
		System.err.println(user);
		
	}

}