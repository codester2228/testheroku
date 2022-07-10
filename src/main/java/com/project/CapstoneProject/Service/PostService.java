package com.project.CapstoneProject.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.CapstoneProject.Entity.Comment;
import com.project.CapstoneProject.Entity.Post;
import com.project.CapstoneProject.Repository.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postrepo;
	public Post SubmitPosttoDB(Post post) {
		
		return postrepo.save(post);
	}
	public ArrayList<Post> RetrievePostFromDB(){
		return postrepo.findAll();
		
	}
	//get user post
	public ArrayList<Post> postById(Long userId){
		ArrayList<Post> postlist = postrepo.findByUserId(userId);
		Comparator<Long> comparator = Collections.reverseOrder();
		Collections.reverseOrder(comparator);
		return postlist;
	}
	
	//delete
	public void DeleteFromDB(Long postId) {
		postrepo.deleteById(postId);
	}
	
	public boolean updatePostComment(Post post){
		//Optional<Post> targetoptionalpost = postrepo.findById(post.getId());
		//if(targetoptionalpost.isEmpty()) {
			//return false;
		//}
		//else {
			//Post targetpost = targetoptionalpost.get();
//			targetpost.setCommentslist(commentlist);
			postrepo.save(post);
			
		
		return true;
	//	}
		
	}

}