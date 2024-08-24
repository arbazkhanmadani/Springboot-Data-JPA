package com.jpa.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.api.entities.User;
import com.jpa.api.services.UserService;

@RestController
@RequestMapping("/jpsuser")
public class UserController{

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/save-user")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		
		try{
			User savedUser = userService.saveUser(user);
		
			if(savedUser!=null)
				 return new ResponseEntity<User>(savedUser, HttpStatus.OK);
			else return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		}catch(Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
	
	@DeleteMapping("/delete-user/userid/{uid}")
	public ResponseEntity<String> saveUser(@PathVariable int uid){
		
		try{
			userService.deleteUser(uid);
			return new ResponseEntity<String>("Deleted successfully...", HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
	
	@GetMapping("/get-user/userid/{uid}")
	public ResponseEntity<User> getUser(@PathVariable int uid){
		
		try{
			User retrivedUser = userService.getUser(uid);
			
			if(retrivedUser!=null)
				 return new ResponseEntity<User>(retrivedUser, HttpStatus.OK);
			else return new ResponseEntity("No user found...", HttpStatus.OK);
			 
		}catch(Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
	
	@GetMapping("/get-alluser")
	public ResponseEntity<List<User>> getAllUser(){
		
		try{
			List<User> users = userService.getAllUser();
			
			if(users!=null)
				 return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			else return new ResponseEntity("No users found...", HttpStatus.OK);
			 
		}catch(Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
}
