package com.project.shoponline.dao.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
	
}