package com.project.shoponline.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shoponline.dao.CommisonAndReward.AccountRepository;
import com.project.shoponline.dao.CommisonAndReward.CommissionRepository;
import com.project.shoponline.dao.CommisonAndReward.RewardRepository;
import com.project.shoponline.dao.customer.ConsumerDataRepository;
import com.project.shoponline.dao.customer.ConsumerRepository;
import com.project.shoponline.dao.customer.ReferralsRepository;
import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module2.Account;
import com.project.shoponline.model.module2.Commission2;
import com.project.shoponline.model.module2.CommissionDistribution;
import com.project.shoponline.model.module2.Transaction;
import com.project.shoponline.utils.RandomString;

@Service
public class CommissionAndRewardService {

	@Autowired
	CommissionRepository commissionRepository;

	@Autowired
	RewardRepository rewardRepository;

	@Autowired
	ConsumerRepository consumerProfileRepository;

	@Autowired
	ConsumerDataRepository consumerDataRepository;

	@Autowired
	ReferralsRepository referralListRepository;
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ConsumerProfileService consumerProfileService;

	public void applyCommission(Double commissionAmount, String consumerId) {
		ConsumerData parentConsumer = consumerDataRepository.findByConsumerDataId(Long.valueOf(consumerId));
		ConsumerData kewlWalletAccount = consumerDataRepository.findByConsumerDataId(Long.valueOf("1"));// manually
	
		Map<Integer, Double> mymap = new HashMap<Integer, Double>();
		mymap.put(1, 0.5);// 10
		mymap.put(2, 0.08);// 1.6
		mymap.put(3, 0.06);// 1.2
		mymap.put(4, 0.05);// 1
		mymap.put(5, 0.03);// 0.6
		mymap.put(6, 0.025);// 0.15
		mymap.put(7, 0.02);
		mymap.put(8, 0.015);
		mymap.put(9, 0.01);
		mymap.put(10, 0.005);
		System.out.println("parentConsumer---" + parentConsumer.getMembershipType());
		System.out.println("kewlWalletAccount---" + kewlWalletAccount.toString());
		if (null != parentConsumer) {
			ConsumerData referrerConsumerData = parentConsumer;
			Double kewlwalletComission = 1.0;
			// for (int i = 0; i < commissionDistributions.size(); i++) {
			
			for (int i = 1; i < 11; i++) {
				 System.out.println("kewlwalletComission---"+kewlwalletComission);
				if (referrerConsumerData.getConsumerDataId()==1) 
					parentConsumerLoop(i, (commissionAmount * kewlwalletComission), referrerConsumerData, kewlWalletAccount);	
				else if (referrerConsumerData.getMembershipType().toString().equals("EXCLUSIVE")
						|| (referrerConsumerData.getMembershipType().toString().equals("STANDARD") && i < 3)
						|| (referrerConsumerData.getMembershipType().toString().equals("PREMIUM") && i < 6)) {
					kewlwalletComission -=mymap.get(i);
					System.out.println("EXCLUSIVE--------" + referrerConsumerData.getMembershipType().toString()+"---"+i);
				parentConsumerLoop(i, (commissionAmount * mymap.get(i)), referrerConsumerData, kewlWalletAccount);
				}
				if (null != referrerConsumerData) {
					referrerConsumerData = referrerConsumerData.getReferrerConsumerData();
				}
				if (null == referrerConsumerData) 
					break;
			}
			System.out.println("Commision Applied");		}
	}

	public ConsumerData getMyCommissions(String consumerDataId) {
		return consumerDataRepository.findByConsumerDataId(Long.valueOf(consumerDataId));
	}

	private void parentConsumerLoop(int level, Double commissionAmount, ConsumerData referrerConsumerData,
			ConsumerData kewlWalletConsumerData) {
		System.out.println("counter--");
		Commission2 commission = new Commission2();
		ConsumerData consumerData = null;
		Account account = null;
		if (null != referrerConsumerData && referrerConsumerData.getConsumerDataId()!=1) {
			System.out.println("counter-23-");
			account = referrerConsumerData.getConsumer().getAccounts().get(0);
			BigDecimal newBalance = account.getBalance().add(BigDecimal.valueOf(commissionAmount));
			account.setBalance(newBalance);
			commission.setAccount(account);
		} else {
			account = kewlWalletConsumerData.getConsumer().getAccounts().get(0);
			BigDecimal newBalance = account.getBalance().add(BigDecimal.valueOf(commissionAmount));
			account.setBalance(newBalance);
			commission.setAccount(account);
		}
		commission.setCommissionPercent(BigDecimal.valueOf(commissionAmount));
		commission.setCommissionLevel(level);
		String transactionCode = (new RandomString(14)).nextString();
		Transaction transaction = new Transaction();
		transaction.setTransactionCode(transactionCode);
		transaction.setTransactionDate(LocalDateTime.now());
		// transaction.setTransactionAmount(commm);
		transaction.setTransactionDescription("Commission Applied");

		commission.setTransaction(transaction);
		Commission2 commision = commissionRepository.save(commission);
		System.out.println("commision--------" + commission.toString());

	}

	public List<Account> getAccountInformation(String consumerID) {
		if (null != consumerProfileRepository.findConsumerByconsumerId(Long.valueOf(consumerID))) {
			return consumerProfileRepository.findConsumerByconsumerId(Long.valueOf(consumerID)).getAccounts();
		}
		return null;
	}

	public Account createAccount(String consumerID, Account account) {
		Consumer consumer = consumerProfileRepository.findConsumerByconsumerId(Long.valueOf(consumerID));
		account.setConsumer(consumer);
		return accountRepository.save(account);
	}
}
