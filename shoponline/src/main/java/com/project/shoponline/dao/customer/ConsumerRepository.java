package com.project.shoponline.dao.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.Consumer;

@Repository
public interface ConsumerRepository extends CrudRepository<Consumer, Long> {
	Consumer findByconsumerId(Long id);
}