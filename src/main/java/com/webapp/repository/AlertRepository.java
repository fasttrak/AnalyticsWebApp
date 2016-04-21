package com.webapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webapp.entity.Alert;

public interface AlertRepository extends MongoRepository<Alert, String>{

}
