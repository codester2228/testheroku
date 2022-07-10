package com.project.CapstoneProject.Controller;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.CapstoneProject.Entity.User;
import com.project.CapstoneProject.Entity.UserSignIn;
import com.project.CapstoneProject.Repository.UserRepository;
import com.project.CapstoneProject.Service.JwtUserDetailsService;
import com.project.CapstoneProject.Service.ResponseObjectService;
import com.project.CapstoneProject.Service.UserService;
import com.project.CapstoneProject.config.JwtTokenUtil;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	  @Autowired
	    private UserService userService;

	    @Autowired
	    private UserRepository userRepo;

	    @GetMapping("/find")
	    public ResponseObjectService findAllUsers() {
	        return userService.findAll();
	    }

	    @GetMapping("/finduser/{userId}")
	    public ResponseObjectService findById(@PathVariable BigInteger inputId) {
	        return userService.findById(inputId);
	    }

	   
	   

	    @PostMapping("/save")
	    public ResponseObjectService saveUser(@RequestBody User inputUser) {
	        return userService.saveUser(inputUser);
	    }
	    

//	    @PostMapping("/signin")
//	    public ResponseObjectService userSignIn(@RequestBody UserSignIn inputUser) {
//	        try {
//	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inputUser.getEmail(), inputUser.getPassword()));
//	            String token = jwtTokenUtil.generateToken(inputUser.getEmail());
//	            
//	            Optional<User> optUser = userRepo.findByEmail(inputUser.getEmail());
//	            User user = optUser.get();
//	            user.setPassword("");
//	            return new ResponseObjectService("success", "authenticated", new AuthorizedEntity(user, token));
//	        } catch (Exception ex) {
//	            return new ResponseObjectService("fail", "unauthenticated", null);
//	        }
//	    }
	    

	    @PutMapping("/profile/update")
	    public ResponseObjectService update(@RequestBody User inputUser) {
	        return userService.update(inputUser);
	    }

	    @GetMapping("/profile/view/{userID}")
	    public ResponseObjectService profile(@PathVariable Long inputId) {
	        return userService.view(inputId);
	    }
	    @DeleteMapping("/profile/delete/{userID}")
	    public ResponseObjectService deleteProfile(@PathVariable Long inputId) {
	        return userService.deleteProfile(inputId);
	    }
	    
}
