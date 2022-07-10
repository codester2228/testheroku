package com.project.CapstoneProject.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.CapstoneProject.Entity.DAOUser;

@Repository
public interface UserDao extends MongoRepository<DAOUser, Integer> {
	
	DAOUser findByUsername(String username);
	
}