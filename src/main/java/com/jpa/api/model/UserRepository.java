package com.jpa.api.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpa.api.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public List<User> getByTypeAndPass(String type, String pass);

	@Query("SELECT u FROM User u WHERE u.name LIKE %:pattern%")
	public List<User> getUserByNameContaining(@Param("pattern") String ptrn);

	@Query(value="SELECT u FROM User u", nativeQuery=true)
	public List<User> getUserByTypeIn();

}
