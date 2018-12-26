package com.project.shoponline.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shoponline.dao.customer.ConsumerDataRepository;
import com.project.shoponline.dao.customer.ConsumerRepository;
import com.project.shoponline.dao.customer.KeyTimeStampRepository;
import com.project.shoponline.dao.customer.UserRepository;
import com.project.shoponline.mail.AmazonEmail;
import com.project.shoponline.mail.SESFrom;
import com.project.shoponline.mail.SESProcessor;
import com.project.shoponline.model.ApplicationStatus;
import com.project.shoponline.model.Consumer;
import com.project.shoponline.model.ConsumerData;
import com.project.shoponline.model.KeyTimeStamp;
import com.project.shoponline.model.User;
import com.project.shoponline.model.UserStatus;
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
  
	public void createLinkToVerify(Consumer consumer) throws Exception {
		System.out.println("REFERALLL**" + consumer.getConsumerData().getReferralCode());
		KeyTimeStamp keyTimeStamp = new KeyTimeStamp();
		keyTimeStamp.setExpirationTime("24");
		keyTimeStamp.setKey(consumer.getConsumerData().getReferralCode());
		keyTimeStamp.setTimeStamp(LocalDateTime.now());
		keyTimeStamp.setUser(consumer.getUser());
		keyTimeStampRepository.save(keyTimeStamp);
	}

	public String sendEmailToVerify(Consumer consumer) throws Exception {
		Password passwordUtil = new Password();
//		String encyptedCode = passwordUtil.getSaltedHash(consumer.getConsumerData().getReferralCode());
//		System.out.println("Verify Link****"+ "/verifyEmailAddress?Context=" + encyptedCode );
		System.out.println("Verify Link****"+ "/verifyEmailAddress?Context=" + consumer.getConsumerData().getReferralCode() );
		
//		// send Email using ATTA from email
//		SESProcessor.getInstance().add(new AmazonEmail("abetarch@gmail.com", SESFrom.ATTA, "kewlwallet",
//				"<p><strong><span style=\"color: #0000ff;\">---- THIS IS FOR TEST PURPOSE, PLEASE IGNORE----</span></strong></p>\n"
//						+ "<p>Dear Kewlwallet Customer,</p>\n"
//						+ "<p>We have received a request to use this email to sign up for shoponline.kewlwallet.com account.</p>\n"
//						+ "<p>If you requested this verification, please go to the following URL to confirm that you are authorized to use this email address:</p>\n"
//						+ "<p>https://shoponline.kewlwallet.com/verifyEmailAddress?Context=" + encyptedCode + "</p>\n"
//						+ "<p>Your sign up process will not be processed unless you confirm through this URL. This link expires 24 hours after your original verification request.</p>\n"
//						+ "<p>If you did NOT request to sign up to shoponline.kewlwallet.com do not click on the link.</p>\n"
//						+ "<p>To learn more about our site please visit http://shoponline.kewlwallet.com</p>\n"
//						+ "<p>Sincerely,</p>\n" + "<p>The Kewlwallet Team</p>"));

		return "Emails Sent!";
	}
	
	public String  verifyEmailAddress(String context){
//		Password passwordUtil = new Password();
//		passwordUtil.check(password, stored)
//		String encyptedCode = passwordUtil.getSaltedHash(consumer.getConsumerData().getReferralCode());
		List<KeyTimeStamp> KeyTimeStampList = keyTimeStampRepository.findByKeyGenerated(context);
		if(!KeyTimeStampList.isEmpty()) {
//			KeyTimeStampList.get(0).toString();
			LocalDateTime fromDateTime= KeyTimeStampList.get(0).getTimeStamp();
			LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

			long hours = tempDateTime.until( LocalDateTime.now(), ChronoUnit.HOURS);
			System.out.println("****tempHourse"+hours);
			if(hours<24) {
				updateApplicationStatus(KeyTimeStampList.get(0));
				return "Your Email is succesfully verfied. You can now go on and login to your kewlwallet accunt. Thanks!";
			}
			 return "the link is already expired. Please ceate a new link on the sign up page.";
		}
		 return "the link you provide is not correct.";
	}
	
//	public void updateApplicationStatus(KeyTimeStamp keyTimeStamp){
//		long userID = keyTimeStamp.getUser().getUserId();
//		Optional<User> user = userRepository.findByUserId(userID);
//		if(user.isPresent()) {
//			Consumer consumer = consumerRepository.findByconsumerId(user.get().getConsumer().getConsumerId());
//			long consumerDataId= consumer.getConsumerData().getConsumerDataId();
//			ConsumerData consumerData = consumerDataRepository.findByConsumerDataId(consumerDataId);
//			consumerData.setApplicationStatus(ApplicationStatus.VERIFIED);
//			consumerDataRepository.save(consumerData);
//			
//		}
//		
//	}
	
	public void updateApplicationStatus(KeyTimeStamp keyTimeStamp){
		long userID = keyTimeStamp.getUser().getUserId();
		Optional<User> user = userRepository.findByUserId(userID);
		if(user.isPresent()) {
			User userVerified = user.get();
			userVerified.setUserStatus(UserStatus.ACTIVE);
			userRepository.save(userVerified);
		}
		
	}

}