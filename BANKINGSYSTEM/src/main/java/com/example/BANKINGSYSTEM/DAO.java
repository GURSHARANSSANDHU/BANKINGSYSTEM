package com.example.BANKINGSYSTEM;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Repository("BANKING_REPOSITORY")
public interface DAO extends JpaRepositoryImplementation<Customer, Integer>  
{

}
