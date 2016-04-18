package com.webapp.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.webapp.dao.UserDAO;
import com.webapp.dto.PaginationDTO;
import com.webapp.entity.User;
import com.webapp.repository.UserRepository;
import com.webapp.util.WebAppUtil;

public class UserBO {

	@Autowired
	UserDAO userDAO;
	
	public ResponseEntity validateLogin(User user) {
		try{
			User validatedUser=userDAO.validateLogin(user);
			Integer sessionid = WebAppUtil.generateIdValue(100);
			validatedUser.setSessionId(sessionid);
			User updatedUser=userDAO.updateUser(validatedUser);
			BeanUtils.copyProperties(updatedUser, user);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	public ResponseEntity getUser(String sessionId) {
		User savedUser=null;
		try{
			savedUser=userDAO.getUserFromSessionId(Integer.parseInt(sessionId));
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}

	public ResponseEntity updateUser(User user) {
		User savedUser=null;
		try{
			savedUser=userDAO.updateUser(user);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}

	public ResponseEntity addUser(User user) {
		User savedUser=null;
		try{
			savedUser=userDAO.addUser(user);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	public ResponseEntity deleteUser(User user) {
		try{
			userDAO.deleteUser(user);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	public ResponseEntity changeUserPassword(User user, String sessionid) {
		try{
			userDAO.changeUserPassword(user, Integer.parseInt(sessionid));
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	public ResponseEntity getAllUsers(PaginationDTO paginationDTO) {
		PaginationDTO responsePaginationDTO=null;
		try{
			Set<String> validColumnNames=new HashSet<String>();
			validColumnNames.add("name");
			validColumnNames.add("position");
			validColumnNames.add("username");
			validColumnNames.add("role");
			responsePaginationDTO=userDAO.getAllUsers(paginationDTO, validColumnNames);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PaginationDTO>(responsePaginationDTO, HttpStatus.OK);
	}
	
}
