package com.project.shoponline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module1.User;
import com.project.shoponline.model.module2.Account;
import com.project.shoponline.model.module2.Commission2;
import com.project.shoponline.model.module2.CommissionDistribution;
import com.project.shoponline.model.module3.Advertiser;
import com.project.shoponline.model.module3.CJPI;
import com.project.shoponline.model.module3.Link;
import com.project.shoponline.service.AdvertiserLookupService;
import com.project.shoponline.service.CommissionAndRewardService;
import com.project.shoponline.service.EmailService;
import com.project.shoponline.service.UserService;
@RestController
public class CommissionAndRewardController {
		
		@Autowired
		CommissionAndRewardService commisionAndRewardService;
//		
/*//		@Autowired
//		UserService userService;
*///
//		@Autowired
//		EmailService emailservive;

		@Autowired
		AdvertiserLookupService advertiserLookupService;

		@RequestMapping(value = "/applyCommission/{consumerId}", method = RequestMethod.POST)
		public String applyCommission(@RequestBody CommissionDistribution commissionDistributions, @PathVariable String consumerId) throws Exception {
			commisionAndRewardService.applyCommission(commissionDistributions.getCommisionAmmount(),consumerId);
			return "Commission Applied";
		}
		
		@RequestMapping(value = "/myCommission/{consumerDataId}", method = RequestMethod.GET)
		public ConsumerData getMyCommission(@PathVariable String consumerDataId) throws Exception {
			return commisionAndRewardService.getMyCommissions(consumerDataId);
		}
		
		@RequestMapping(value = "/getAccountInformation/{consumerId}", method = RequestMethod.GET)
		public List<Account> getAccountInformation(@PathVariable String consumerId) throws Exception {
			return commisionAndRewardService.getAccountInformation(consumerId);
		}
		@RequestMapping(value = "/createAccount/{consumerId}", method = RequestMethod.POST)
		public Account createAccount(@RequestBody Account account, @PathVariable String consumerId) throws Exception {
			return commisionAndRewardService.createAccount(consumerId, account);
		}
		
		
		
	}
