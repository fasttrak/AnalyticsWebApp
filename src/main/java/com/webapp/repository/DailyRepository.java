package com.webapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.webapp.entity.Daily;


public interface DailyRepository extends MongoRepository<Daily, String>{

	@Query("{'day' : {'$gte' : ?0, '$lte' : ?0}}")
	public List<Daily> findDailyDocumentsBetweenDays(int startDay, int endDay);
	
}
