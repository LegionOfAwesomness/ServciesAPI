package com.project.shoponline.dao.customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.Consumer;
import com.project.shoponline.model.ConsumerData;

@Repository
public interface ConsumerDataRepository extends CrudRepository<ConsumerData, Long> {
	List<ConsumerData> findByEmailOrReferralCode(String email, String refferalCode);

	List<ConsumerData> findByEmail(String id);

	List<ConsumerData> findByReferralCode(String id);
	
	ConsumerData findByConsumerDataId(Long id);

}