package com.project.shoponline.dao.customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module1.KeyTimeStamp;
import com.project.shoponline.model.module1.User;

public interface KeyTimeStampRepository extends CrudRepository<KeyTimeStamp, Long> {
	List<KeyTimeStamp> findByKeyGenerated(String userName);
}
