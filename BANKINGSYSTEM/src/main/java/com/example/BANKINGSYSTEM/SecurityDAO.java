package com.example.BANKINGSYSTEM;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Repository("SECURITYRepo")
public interface SecurityDAO extends JpaRepositoryImplementation<User, Integer> {

	//@Query(value = "select * from administrators username = ?1", nativeQuery = true)
	@Query("select u from User u where u.username =:username ")
	public User getByUsername(@Param(value = "username") String username);

}
