package com.project.CapstoneProject.Repository;

import com.project.CapstoneProject.Entity.Comment;
import com.project.CapstoneProject.Service.CommentService;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface CommentRepository extends MongoRepository<Comment, Long> {
	
	Comment save(Comment comment);
	ArrayList<Comment> findAllByPostId(Long postId);
	ArrayList<Comment> findAll();
	
	void deleteBycommentId(Long commentId);
	
	Optional<Comment> findBycommentId(Long commentId);
	//ArrayList<Comment> findBypostId(Long PostId);
}

