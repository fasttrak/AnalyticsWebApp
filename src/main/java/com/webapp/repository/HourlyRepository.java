package com.webapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.webapp.entity.Daily;
import com.webapp.entity.Hourly;

public interface HourlyRepository extends MongoRepository<Hourly, String>{

	@Query("{'hour' : {'$gte' : ?0, '$lte' : ?0}}")
	public List<Hourly> findHourlyDocumentsBetweenDays(String ip, int startHour, int endHour,
					int day, int month, int year);
	
}
