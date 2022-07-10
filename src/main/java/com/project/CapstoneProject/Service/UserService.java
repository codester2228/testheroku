package com.project.CapstoneProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.CapstoneProject.Controller.*;
import com.project.CapstoneProject.Entity.*;
import com.project.CapstoneProject.Repository.CommentRepository;
import com.project.CapstoneProject.Repository.PostRepository;
import com.project.CapstoneProject.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PostRepository post_repo;
	
	@Autowired
	PostService postService;
	
	@Autowired
	private PasswordEncoder bCryptEncoder;
	
	
	public ResponseObjectService findAll() {
		ResponseObjectService ros=new ResponseObjectService();
		ros.setPayload(userRepo.findAll());
	       ros.setStatus("success");
	       ros.setMessage("success");
	        return ros;
	}
	 public ResponseObjectService findById(BigInteger inputId) {
		 ResponseObjectService ros=new ResponseObjectService();
		 
		 Optional<User> optioalTargetUser = userRepo.findByUserId(inputId);
	        if (optioalTargetUser.isEmpty()) {
	            ros.setStatus("fail");
	            ros.setMessage("user id: " + inputId + " not found");
	            ros.setPayload(null);
	            
	        } else {
	            ros.setPayload(optioalTargetUser.get());
	            ros.setStatus("success");
	            ros.setMessage("success");
	            
	        }
		 
		 return ros;
	 }
	   public ResponseObjectService saveUser(User inputUser) {
		   ResponseObjectService ros=new ResponseObjectService();
		   
		   Optional<User> optionalTargetUser = userRepo.findByEmail(inputUser.getEmail());
	        if (optionalTargetUser.isPresent()) {
	        	System.out.println("yoooooo");
	            ros.setStatus("fail");
	            ros.setMessage("Email address " + inputUser.getEmail() + " existed");
	            ros.setPayload(null);
	            
	        } else {
	            
				inputUser.setPassword(bCryptEncoder.encode(inputUser.getPassword()));
	            
	            
	            User user = userRepo.save(inputUser);
	            List<Long> friendlist = user.getFriends();
	            if (friendlist == null) {
	            	friendlist = new ArrayList<>();
	            }
//	            friendlist.add(user.getId());
//	            user.setFriends(friendlist);
	            
	            this.update(user);
	            
	            ros.setPayload(user);
	            ros.setStatus("success");
	            ros.setMessage("success");
	        }
		
	            
	            return ros;
		   
		   
	        
	    }
	    public ResponseObjectService update(User inputUser) {
	    	ResponseObjectService ros=new ResponseObjectService();
	    	
	    	Optional<User> optUser = userRepo.findById(inputUser.getId());
	    	
	        if (optUser.isEmpty()) {
	        	 ros.setPayload(null);
		            ros.setStatus("fail");
		            ros.setMessage("no user found");
	        } else {
	            User targetUser = optUser.get();
	            if (inputUser.getPassword().equals(targetUser.getPassword())) {
	            	ros.setPayload(userRepo.save(inputUser));
		            ros.setStatus("Successfull");
		            ros.setMessage("Updated");
	            } else {
	            	ros.setPayload(null);
		            ros.setStatus("fail");
		            ros.setMessage("failed");
	            }
	        }
	    	
	        return ros;
	    }

	    public ResponseObjectService view(Long inputId) {
	    	ResponseObjectService ros=new ResponseObjectService();
	    	

	    	Optional<User> optUser = userRepo.findById(inputId);
	    	if(optUser.isEmpty()) {
	    		ros.setPayload(null);
	            ros.setStatus("fail");
	            ros.setMessage("No user Found");
	    		
	    	}
	    	else {
	    		
	    		User targetuser = optUser.get();
	    		
	    		ros.setPayload(targetuser);
	            ros.setStatus("Success");
	            ros.setMessage("Success");
	    		
	    	}
	    	
	        return ros;
	    }
	    
	    public ResponseObjectService getFriends( Long inputId) {
	    	ResponseObjectService ros=new ResponseObjectService();

	    	Optional<User> optUser = userRepo.findById(inputId);
	    	if(optUser.isEmpty()) {
	    		ros.setPayload(null);
	            ros.setStatus("fail");
	            ros.setMessage("No user Found");
	    		
	    	}
	    	else {
	    		User targetuser = optUser.get();
	    		List<Long> friendlist = targetuser.getFriends();
	    		List<User> userlist = new ArrayList<>();
	    		int s = friendlist.size();
	    		for(int i = 0;i<s;i++) {

	    	    	Optional<User> optUserlist = userRepo.findById(friendlist.get(i));
	    	    	if(optUserlist.isEmpty()) {
	    	    		continue;
	    	    		
	    	    	}
	    	    	else {
	    	    		User frienduser = optUserlist.get();
	    	    		userlist.add(frienduser);
	    	    		
	    	    	}
	    		}
	    		
	    		ros.setPayload(userlist);
	            ros.setStatus("Success");
	            ros.setMessage("Success");
		
	    	}
	    	
	    	
	        return ros;
	    }
	    public ResponseObjectService findFriend(Long inputId) {
	    	ResponseObjectService ros=new ResponseObjectService();
	    	Optional<User> optUser = userRepo.findById(inputId);
	    if(optUser.isEmpty()) {
	    		ros.setPayload(null);
	            ros.setStatus("fail");
	            ros.setMessage("No user Found");
	    		
	    	}
	    	else {
	    		User targetUser = optUser.get();
	    		ros.setPayload(targetUser);
	            ros.setStatus("Success");
	            ros.setMessage("User Found");
	    		
	    		
	    		
	    	}
	    	
	    	
	    	
	        return ros;
	    }
	    
	    public ResponseObjectService deleteProfile(Long inputId) {
	    	ResponseObjectService ros=new ResponseObjectService();
	    	
	    	Optional<User> optUser = userRepo.findById(inputId);
		    if(optUser.isEmpty()) {
		    		ros.setPayload(null);
		            ros.setStatus("fail");
		            ros.setMessage("No user Found");
		    		
		    	}
		    	else {
		    		User targetUser = optUser.get();
		    		//post deletion
		    		
		    		userRepo.deleteById(inputId);
		    		ros.setPayload(null);
		            ros.setMessage("Account Successfully deleted");
		            ros.setStatus("Success");
		    		
		    		
		    		
		    	}
	    	
	    	
	    	return ros;
	    }
	    
	    public ResponseObjectService addFriend(DoubleId inputId) {
	    	ResponseObjectService ros=new ResponseObjectService();
	    	
	    	Long id1 = inputId.getOne();
	    	Long id2 = inputId.getTwo();

	    	Optional<User> optUser1 = userRepo.findById(id1);
	    	Optional<User> optUser2 = userRepo.findById(id1);
	    	
	    	if(optUser1.isPresent()&&optUser2.isPresent()) {
	    		User targetUser1 = optUser1.get();
	    		User targetUser2 = optUser2.get();
	    		
	    		List<Long> commentlist1 = targetUser1.getFriends();
	    		List<Long> commentlist2 = targetUser2.getFriends();
	    		
	    		commentlist1.add(id2);
	    		commentlist2.add(id1);
	    		
	    		targetUser1.setFriends(commentlist1);
	    		targetUser2.setFriends(commentlist2);
	    		
	    		userRepo.save(targetUser2);
	    		userRepo.save(targetUser1);
	    		
	    		
	    		ros.setPayload(null);
	            ros.setStatus("Sucessfully added");
	            ros.setMessage("Success");
	    		
	    	}
	    	else {
	    		ros.setPayload(null);
	            ros.setStatus("fail");
	            ros.setMessage("Either user not present");
	    		
	    		
	    	}
	    	
	    	
	        return ros;
	    }
	    
	
	

}
