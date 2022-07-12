package com.revature.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

/**
 * Spring Data JPA is an add-on providing a framework that works
 * WITH (builds a layer on top of ) the Java Persistence API (JPA)
 * 
 * 
 */
@Repository  //Stereotype annotation (specification of @Component
public interface UserRepository extends JpaRepository<User, Integer>{

	User findUserByUsernameAndPwd(String username, String password);
	
	User findUserByUsername(String username);
	

}
