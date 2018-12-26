package com.project.shoponline.dao.customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.Referrals;

@Repository
public interface ReferralsRepository extends CrudRepository<Referrals, Long> {
	List<Referrals> findByConsumerData_consumerDataId(long id);
}