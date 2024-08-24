package com.jpa.api.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.jpa.api.entities.User;
import com.jpa.api.model.UserRepository;


@Service
public class UserService{

	@Autowired
	private UserRepository userRepo;
	
	
	//Pagination And Sorting....................................
	public Page<User> getPagedUsers(int page, int size, String sortBy, String sortOrder){
		 
		//By default its Ascending.................
		Sort sort = Sort.by(Sort.Order.by(sortBy));
	    if(sortOrder.equalsIgnoreCase("desc")) 
	    	sort = sort.descending();
	        
		Pageable pageable = (Pageable) PageRequest.of(page, size, sort);
        return userRepo.findAll(pageable);
    }
	
	
	@Transactional
	public User saveUser(User user){
		User savedUser = userRepo.save(user);
		return savedUser;
	}
	
	@Transactional
	public User updateUser(User newUser, int id){
		
		User oldUser = userRepo.getOne(id);
		
		if(newUser.getName()!=null || !newUser.getName().equals(""))
		 oldUser.setName(newUser.getName());
		if(newUser.getPass()!=null || !newUser.getPass().equals(""))
			oldUser.setPass(newUser.getPass());
		if(newUser.getType()!=null || !newUser.getType().equals(""))
			oldUser.setType(newUser.getType());
	
		User savedUser = userRepo.save(oldUser);
		return savedUser;
	}
	
	@Transactional
	public void deleteUser(int id){
		User getUser = userRepo.getOne(id);
		userRepo.delete(getUser);
	}
	
	public User getUser(int id){
		User savedUser = userRepo.getOne(id);
		return savedUser;
	}
	
	public List<User> getAllUser(){
		List<User> users = userRepo.findAll();
		return users;
	}
	
	public List<User> getBytypeAndpass(String type, String pass){
		
		List<User> users = userRepo.getByTypeAndPass(type, pass);
		return users;
	}
	
	
	public List<User> getUserByLike(String pattern){
		
		List<User> users = userRepo.getUserByNameContaining(pattern);
		return users;
	}
	
}
