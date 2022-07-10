package com.project.CapstoneProject.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.CapstoneProject.Entity.Post;
import com.project.CapstoneProject.Service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	  @Autowired
	 PostService postservice;
	@PostMapping("")
	private Post submitpost(@RequestBody Post post) {
		return postservice.SubmitPosttoDB(post);
	}
	@GetMapping("")
	private  ArrayList<Post> getallpost(){
		return postservice.RetrievePostFromDB();
	}
	//get all post of user
	@PostMapping("/userPost/{userId}")
	public ArrayList<Post> postById(@PathVariable ("userId" )Long userId)
	{
		return postservice.postById(userId);
	}
    
    @DeleteMapping("/delete/{postId}")
    private String deletepost(@PathVariable("postId") Long postId) {
    	postservice.DeleteFromDB(postId);
    	return "post deleted sucessfully";
    }
}