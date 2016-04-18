package com.webapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webapp.entity.Group;

public interface GroupRepository extends MongoRepository<Group, String>{

	
}
