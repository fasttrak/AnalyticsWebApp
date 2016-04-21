package com.webapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webapp.entity.Analytic;

public interface AnalyticRepository extends MongoRepository<Analytic, String>{

}
