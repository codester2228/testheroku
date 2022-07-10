package com.project.CapstoneProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.CapstoneProject.Entity.Comment;
import com.project.CapstoneProject.Repository.PostRepository;
import com.project.CapstoneProject.Service.CommentService;
import com.project.CapstoneProject.Service.ResponseObjectService;



@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	CommentService commentsService;
	
	@Autowired
	PostRepository post_repo;
	
	@PostMapping("/insertComment")
	private ResponseObjectService submitComment(@RequestBody Comment comment) {

		return commentsService.submitCommentToDB( comment);
		
	}
	
	
	@GetMapping("/{postId}")
	private ResponseObjectService getCommentsForPost(@PathVariable("postId") Long postId){
		return commentsService.getAllCommentsForPost(postId);
		
	}
	
	@DeleteMapping("/delete/{CommentId}")
	private ResponseObjectService deleteComment(@PathVariable Long CommentId) {

		return commentsService.deleteFromPost(CommentId);		
	}
	
	@PutMapping("/updateComment")
	private Comment updateComment(@RequestBody Comment comment) {
		
		//return commentsService.updateCommentToDB(comment);
		return null;
	}
	
	
	
	
	
	

}

