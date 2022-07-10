package com.project.CapstoneProject.Repository;
import java.math.BigInteger;
import java.util.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.CapstoneProject.Entity.Post;

@Repository
public interface PostRepository extends MongoRepository<Post,Long> {
	@SuppressWarnings("unchecked")
	Post  save(Post post);
	ArrayList<Post> findAll();
	void deleteById(Long postId);
	
	ArrayList<Post> findByUserId(Long userId);
	//Optional<Post> find();
	Optional<Post> findById();

	Optional<Post> findByPostId(Long postId);
	

}
