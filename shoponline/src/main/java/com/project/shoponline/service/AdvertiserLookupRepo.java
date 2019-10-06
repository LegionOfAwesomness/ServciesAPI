package com.project.shoponline.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module3.Advertiser;

@Repository
public interface AdvertiserLookupRepo extends CrudRepository<Advertiser, Long> {
//	List<Long>findAdvertiserId();
//	advertiserId
	List<Advertiser> findAll();
	
}
