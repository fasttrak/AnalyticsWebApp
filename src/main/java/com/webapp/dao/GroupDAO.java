package com.webapp.dao;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.webapp.entity.Group;
import com.webapp.repository.GroupRepository;

public class GroupDAO {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Group updateGroup(Group group)throws Exception{
		Group updatedGroup=null;
		try{
			Group savedGroup=groupRepository.findOne(group.getId());
			BeanUtils.copyProperties(group, savedGroup); 
			updatedGroup=groupRepository.save(savedGroup);
		}catch(Exception e){
			throw e;
		}
		return updatedGroup;
	}
	
	public Group addGroup(Group group)throws Exception{
		Group updatedGroup=null;
		try{
			updatedGroup=groupRepository.save(group);
		}catch(Exception e){
			throw e;
		}
		return updatedGroup;
	}
	
	public void deleteGroup(Group group)throws Exception{
		try{
			Group savedGroup=groupRepository.findOne(group.getId());
			groupRepository.delete(savedGroup);
		}catch(Exception e){
			throw e;
		}
	}
	
	public List<Group> getAllGroups()throws Exception{
		List<Group> groups=null;
		try{
			groups=groupRepository.findAll();
		}catch(Exception e){
			throw e;
		}
		return groups;
	}
	
	public Group getGroup(String id)throws Exception{
		Group savedGroup=null;
		try{
			savedGroup=groupRepository.findOne(id);
		}catch(Exception e){
			throw e;
		}
		return savedGroup;
	}
}
