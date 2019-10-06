package com.project.shoponline.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.module3.Link;

@Repository
public interface LinkSearchRepo extends CrudRepository<Link, Long> {
	// List<Long>findAdvertiserId();
//	 advertiserId
	 List<Link> findAllByAdvertiserId(String advertiserID);
	 @Query("SELECT p FROM Link p GROUP BY p.advertiserId")
	 List<Link> findAllByLinkType(String linkType);
//	 @Query("SELECT p FROM Link p where p.linkType equals = Banner")
	 List<Link> findByAdvertiserIdAndLinkType(String advertiserID,String linkType);
}