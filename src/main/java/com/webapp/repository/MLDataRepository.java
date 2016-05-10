package com.webapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webapp.entity.Analytic;
import com.webapp.entity.MLData;

public interface MLDataRepository extends MongoRepository<MLData, String>{

}
