package com.webapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.webapp.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public User findByUsername(String name);
	
	public User findBySessionId(Integer sessionId);
	
}
