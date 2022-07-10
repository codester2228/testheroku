package com.project.CapstoneProject.Repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.CapstoneProject.Entity.Post;
import com.project.CapstoneProject.Entity.User;


@Repository
public interface UserRepository extends MongoRepository<User,Long> {
	
	Optional<User> findByEmail(String email);

	Optional<User> findById(BigInteger bigInteger);
	Optional<User> findByUserId(BigInteger id);

	User save(User user);
	void deleteById(Long id);
}
