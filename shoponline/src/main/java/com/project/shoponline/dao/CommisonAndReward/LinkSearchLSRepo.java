
package com.project.shoponline.dao.CommisonAndReward;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.module4.LSLinkLocatorResponse;
import com.project.shoponline.model.module4.LSLinkReturn;

@Repository
public interface LinkSearchLSRepo extends CrudRepository<LSLinkReturn, Long> {
}
