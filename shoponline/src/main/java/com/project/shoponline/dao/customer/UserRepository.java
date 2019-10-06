package com.project.shoponline.dao.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.module1.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	// Consumer findByconsumerId(Long id);
	List<User> findByUserNameOrEmail(String userName, String email);
	//Optional<User> findByUseOrEmail(String userName, String email);
//	Optional<User>  findByUserId(long id);
}