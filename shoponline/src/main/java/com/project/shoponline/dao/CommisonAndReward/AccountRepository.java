package com.project.shoponline.dao.CommisonAndReward;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.module2.Account;
import com.project.shoponline.model.module2.Commission2;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
