package com.project.shoponline.dao.customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.shoponline.model.ConsumerData;
import com.project.shoponline.model.KeyTimeStamp;
import com.project.shoponline.model.User;

public interface KeyTimeStampRepository extends CrudRepository<KeyTimeStamp, Long> {
	List<KeyTimeStamp> findByKeyGenerated(String userName);
}
