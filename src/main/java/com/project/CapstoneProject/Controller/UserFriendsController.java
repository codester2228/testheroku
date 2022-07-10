package com.project.CapstoneProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.CapstoneProject.Entity.DoubleId;
import com.project.CapstoneProject.Repository.UserRepository;
import com.project.CapstoneProject.Service.ResponseObjectService;
import com.project.CapstoneProject.Service.UserService;

@RestController
@RequestMapping("/friends")
public class UserFriendsController {
	
	  @Autowired
	    private UserService userService;

	    @Autowired
	    private UserRepository userRepo;
	
	  @GetMapping("/{userId}")
	    public ResponseObjectService friends(@PathVariable Long inputId) {
	        return userService.getFriends(inputId);
	    }

	   

	    @PostMapping("/findfriend/{userId}")
	    public ResponseObjectService findFriend(@PathVariable Long inputId) {
	        return userService.findFriend(inputId);
	    }
	    

	    @PostMapping("/addfriend/{userDoubleIdObject}")
	    public ResponseObjectService addFriend(@RequestBody DoubleId inputId) {
	        return userService.addFriend(inputId);
	    }

}
