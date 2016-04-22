package com.webapp.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.webapp.entity.Daily;
import com.webapp.entity.Hourly;
import com.webapp.entity.User;
import com.webapp.repository.DailyRepository;
import com.webapp.repository.HourlyRepository;

public class HistoricalDataDAO {

	@Autowired
	DailyRepository dailyRepository;
	
	@Autowired
	HourlyRepository hourlyRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Daily> findDailyDocumentsBetweenDays(String ip, Date startDate, Date endDate){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(startDate);
		int startDay=calendar.get(Calendar.DATE);
		int startMonth=calendar.get(Calendar.MONTH)+1;
		int startYear=calendar.get(Calendar.YEAR);
		
		calendar.setTime(endDate);
		int endDay=calendar.get(Calendar.DATE);
		int endMonth=calendar.get(Calendar.MONTH)+1;
		int endYear=calendar.get(Calendar.YEAR);
		
		Query query = new Query();
		/*query.addCriteria(Criteria.where("day").gte(startDay).and("day").lte(endDay).and("month").gte(startMonth).and("day").lte(endMonth)
				.and("year").gte(startYear).and("year").lte(endYear).and("ip").is(ip));*/
		query.addCriteria(
				Criteria.where("ip").is(ip)
				.andOperator(
						Criteria.where("day").gte(startDay),
						Criteria.where("day").lte(endDay),
						Criteria.where("month").gte(startMonth),
						Criteria.where("month").lte(endMonth),
						Criteria.where("year").gte(startYear),
						Criteria.where("year").lte(endYear)
				)
		);
		query.with(new Sort(new Order(Direction.ASC, "id")));
		List<Daily> dailyList = mongoTemplate.find(query, Daily.class);
		return dailyList;
	}
	
	public List<Hourly> findHourlyDocumentsForDay(String ip, Date startDate){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(startDate);
		int day=calendar.get(Calendar.DATE);
		int month=calendar.get(Calendar.MONTH)+1;
		int year=calendar.get(Calendar.YEAR);
		Query query = new Query();
		//query.addCriteria(Criteria.where("day").is(day).and("month").is(month).and("year").is(year).and("hour").gte(0).and("hour").lte(23).and("ip").is(ip));
		query.addCriteria(
				Criteria.where("ip").is(ip)
				.andOperator(
						Criteria.where("day").is(day),
						Criteria.where("month").is(month),
						Criteria.where("year").is(year),
						Criteria.where("hour").gte(0),
						Criteria.where("hour").lte(24)
				)
		);		
		query.with(new Sort(new Order(Direction.ASC, "id")));
		List<Hourly> hourlyList = mongoTemplate.find(query, Hourly.class);
		return hourlyList;
	}
	
}
