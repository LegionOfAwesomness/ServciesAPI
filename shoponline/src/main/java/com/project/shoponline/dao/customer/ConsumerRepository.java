package com.project.shoponline.dao.customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module2.Account;

@Repository
public interface ConsumerRepository extends CrudRepository<Consumer, Long> {
	Consumer findConsumerByconsumerId(Long id);
//	List<Account> findAccountByConsumerId(Long id);
}