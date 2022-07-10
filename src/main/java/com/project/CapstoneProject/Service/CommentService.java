package com.project.CapstoneProject.Service;
//com.mongodb.client.MongoCollection<com.project.CapstoneProject> getCollection(post)

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.project.CapstoneProject.Entity.Comment;
import com.project.CapstoneProject.Service.*;
import org.springframework.stereotype.Service;
import com.project.CapstoneProject.Entity.Post;
import com.project.CapstoneProject.Repository.CommentRepository;
import com.project.CapstoneProject.Repository.PostRepository;



@Service
public class CommentService{
	
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostRepository post_repo;
	
	@Autowired
	PostService postService;
	
	
	
	
	public ResponseObjectService submitCommentToDB(Comment comment) {
		
		
		//PostService post_repo = new PostService();
		
		ResponseObjectService ros = new ResponseObjectService(); 
		
		comment.setTimeStamp(Instant.now());
		
		System.out.println("faiiiiiiissssssssssssiii");
		Long postId = comment.getPostId();
		ArrayList<Post> ne;
		ne = post_repo.findAll();
		for(int i = 0;i<4;i++) {
			
			System.out.println(ne.get(i).getPostId());
		}
		
		
		System.out.println(postId);
		
		Optional<Post> targetoptionalpost = post_repo.findByPostId(postId); 
		
		System.out.println(targetoptionalpost);
		
		Post targetpost = null;
		if(targetoptionalpost.isEmpty()){
			ros.setStatus("Action Failed");
			ros.setMessage("Action Failed. No user found");
			ros.setPayload(null);
			System.out.println("faiiiiiiiiii");
	
		}
		else {
			commentRepo.save(comment);
			System.out.println("aaaaafaiiiiiiiiiaaai");
			targetpost=targetoptionalpost.get();
			
			 comment.setTimeStamp(Instant.now());
			
			List<Comment> commentlist;
			commentlist = targetpost.getCommentslist();
			if(commentlist==null) {
				commentlist = new ArrayList<>();
			}
			 commentlist.add(comment);
			 
			 targetpost.setCommentslist(commentlist);
			 postService.updatePostComment(targetpost);
			 System.out.println(postId+""+commentlist);
			 
			 
			 ros.setMessage("Comment added successfully");
			 ros.setStatus("Success");
			 ros.setPayload(comment);
			 
			 
			
			 
			 
			 
			
		}
		
		return ros;
		
		
		
		
		
		
	
		
		
	}
	
	public ResponseObjectService getAllCommentsForPost(Long postId){
		
		ResponseObjectService ros = new ResponseObjectService();
		
		
		Optional<Post> targetoptionalpost = post_repo.findByPostId(postId); 
		Post targetpost=null;
		if(targetoptionalpost.isEmpty()) {
			
			ros.setPayload(null);
			ros.setMessage("No Post found");
			ros.setStatus("Failed");
		
		}
		else {
			
			targetpost = targetoptionalpost.get();
			List<Comment> commentlist = targetpost.getCommentslist();
			ros.setMessage(String.valueOf(commentlist.size())+" Comments found");
			ros.setStatus("Success");
			System.out.println(postId+""+commentlist);
			
			ros.setPayload(commentlist);
			

			
		}
		
		//ArrayList<Comment> commentList=commentRepo.findAllByPostId(postId);
	
		
		/*
		for(int i=0;i<commentList.size();i++) {
			Comments commentItem=commentList.get(i);
			commentItem.setUserName(userService.displayUserMetaData(commentItem.getUserId()).getUserName());
		}
		*/
		return  ros;	
	}
	
	public ResponseObjectService deleteFromPost(Long commentId) {
		
		ResponseObjectService ros = new ResponseObjectService();
		
		
		Optional<Comment> targetoptionalcomment = commentRepo.findBycommentId(commentId); 
		
		
		
		Comment targetcomment=null;
		System.out.println(targetoptionalcomment);
		
		
		if(targetoptionalcomment.isPresent()) {
			

			
			targetcomment = targetoptionalcomment.get();
			
			Long post_id = targetcomment.getPostId();
			
			Optional<Post> targetoptionalpost = post_repo.findByPostId(post_id);
			System.out.println(targetoptionalpost);
			Post targetpost=null;

			if(targetoptionalpost.isEmpty()) {
				ros.setMessage("No Post found");
				ros.setPayload(null);
				ros.setStatus("Fail");
				
			}
			else {
				targetpost = targetoptionalpost.get();
				
			
			List<Comment> commentlist = targetpost.getCommentslist();
			
			commentlist.remove(targetcomment);
			targetpost.setCommentslist(commentlist);
			postService.updatePostComment(targetpost);
			
			commentRepo.deleteBycommentId(commentId);

			
			ros.setMessage("Comment Deleted");
			ros.setPayload(targetpost);
			ros.setStatus("Success");
			
			}
	
		}
		else {
			ros.setMessage("No comment found");
			ros.setPayload(null);
			ros.setStatus("Fail");
	
			
		}
		
		
		return ros;
		
	}
	
	
	
	
	
	
	
	
}

