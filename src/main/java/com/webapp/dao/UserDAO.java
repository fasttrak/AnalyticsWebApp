package com.webapp.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.webapp.dto.MapDTO;
import com.webapp.dto.PaginationDTO;
import com.webapp.entity.User;
import com.webapp.repository.UserRepository;

public class UserDAO {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public User validateLogin(User user)throws Exception{
		User savedUser=null;
		try{
			savedUser=userRepository.findByUsername(user.getUsername());
			//savedUser=mongoTemplate.findOne(new Query(Criteria.where("username").is("admin")), User.class, "Users");
			if(!savedUser.getPassword().equals(user.getPassword())){
				savedUser=user;
				throw new Exception("Invalid Username or Password");
			}
		}catch(Exception e){
			throw e;
		}
		return savedUser;
	}
	
	@CacheEvict("users")
	public User updateUser(User user)throws Exception{
		User updatedUser=null;
		try{
			User savedUser=userRepository.findOne(user.getId());
			BeanUtils.copyProperties(user, savedUser); 
			updatedUser=userRepository.save(savedUser);
		}catch(Exception e){
			throw e;
		}
		return updatedUser;
	}
	
	@CacheEvict("users")
	public User addUser(User user)throws Exception{
		User updatedUser=null;
		try{
			user.setImagePath("https://cdn1.iconfinder.com/data/icons/user-pictures/100/male3-512.png");
			updatedUser=userRepository.save(user);
		}catch(Exception e){
			throw e;
		}
		return updatedUser;
	}
	
	@CacheEvict("users")
	public void deleteUser(User user)throws Exception{
		try{
			User savedUser=userRepository.findOne(user.getId());
			userRepository.delete(savedUser);
		}catch(Exception e){
			throw e;
		}
	}
	
	@Cacheable("users")
	public User getUserFromSessionId(Integer sessionId)throws Exception{
		User savedUser=null;
		try{
			savedUser=userRepository.findBySessionId(sessionId);
		}catch(Exception e){
			throw e;
		}
		return savedUser;
	}

	public boolean changeUserPassword(User user, Integer sessionid) throws Exception {
		try{
			User dbUser=this.getUserFromSessionId(sessionid);
			if(dbUser.getPassword().equals(user.getOldPassword())){
				dbUser.setPassword(user.getPassword());
				userRepository.save(dbUser);
			}else{
				throw new Exception("password doesn't match");
			}
		}catch(Exception e){
			throw e;
		}
		return true;
	}

	public PaginationDTO getAllUsers(PaginationDTO paginationDTO, Set<String> validColumnNames)throws Exception{
		PaginationDTO responsePaginationDTO=null;
		try{
		    responsePaginationDTO=new PaginationDTO();
		    List<User> users=getPaginationUsers(paginationDTO, validColumnNames);
		    responsePaginationDTO.setData(users);
		    long totalRecords=getPaginationUsersCount(paginationDTO, validColumnNames);
		    responsePaginationDTO.setTotalRecords(totalRecords);
		}catch(Exception e){
			throw e;
		}
		return responsePaginationDTO;
	}
	
	private List<User> getPaginationUsers(PaginationDTO paginationDTO, Set<String> validColumnNames)throws Exception{
		List<User> users=null;
		try{
			Query query = new Query();
			List<MapDTO> filterMaps=paginationDTO.getFilterParams();
			if(filterMaps!=null){
				for(MapDTO mapDTO:filterMaps){
					if(validColumnNames.contains(mapDTO.getKey())){
						query.addCriteria(Criteria.where(mapDTO.getKey()).regex(mapDTO.getValueString()));
					}
				}
			}
			List<MapDTO> sortingMaps=paginationDTO.getSortingParams();
			if(sortingMaps!=null){
				for(MapDTO mapDTO:sortingMaps){
					if(validColumnNames.contains(mapDTO.getKey())){
						Direction sortDirection=Direction.DESC;
						if("asc".equals(mapDTO.getValueString())){
							sortDirection=Direction.ASC;
						}
						query.with(new Sort(new Order(sortDirection, mapDTO.getKey())));
					}
				}
			}
			query.skip((paginationDTO.getPage()-1)*paginationDTO.getSize());
		    query.limit(paginationDTO.getSize());
		    users = mongoTemplate.find(query, User.class);
		}catch(Exception e){
			throw e;
		}
		return users;
	}
	
	private long getPaginationUsersCount(PaginationDTO paginationDTO, Set<String> validColumnNames)throws Exception{
		long usersCount=0l;
		try{
			Query query = new Query();
			List<MapDTO> filterMaps=paginationDTO.getFilterParams();
			if(filterMaps!=null){
				for(MapDTO mapDTO:filterMaps){
					if(validColumnNames.contains(mapDTO.getKey())){
						query.addCriteria(Criteria.where(mapDTO.getKey()).regex(mapDTO.getValueString()));
					}
				}
			}
			usersCount = mongoTemplate.count(query, User.class);
		}catch(Exception e){
			throw e;
		}
		return usersCount;
	}
	
}
