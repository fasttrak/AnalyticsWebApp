package com.webapp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webapp.AnalyticsWebAppApplication;
import com.webapp.bo.UserBO;
import com.webapp.entity.User;
import com.webapp.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations="../../../Beans.xml", classes=AnalyticsWebAppApplication.class)
@ActiveProfiles(profiles="test")
public class UserControllerIT {

	@Autowired
	UserBO userBO;
	
	@Autowired
	UserRepository userRepository;
	
	@Before
	public void createUserRecord(){
		User user=new User();
		user.setId("570e9a41ffc0edc23e66a106");
		user.setUsername("admin");
		user.setName("admin");
		user.setPassword("admin");
		user.setPosition("admin");
		user.setRole("admin");
		user.setImagePath("imagepath");
		user.setSessionId(12345);
		userRepository.save(user);
	}
	
	@Test
	public void validateLoginTest(){
		User user=new User();
		user.setUsername("admin");
		user.setPassword("admin");
		ResponseEntity responseEntity = userBO.validateLogin(user);
		assertNotNull(responseEntity);
		assertNotNull(user.getId());
		assertEquals("570e9a41ffc0edc23e66a106", user.getId());
	}
	
	@Test
	public void getUserTest(){
		ResponseEntity responseEntity = userBO.getUser("12345");
		assertNotNull(responseEntity);
		Object responseObject=responseEntity.getBody();
		assertNotNull(responseObject);
		assertEquals(User.class, responseObject.getClass());
		User responseUser=(User)responseObject;
		assertEquals("570e9a41ffc0edc23e66a106", responseUser.getId());
		assertEquals("admin", responseUser.getName());
		assertEquals("imagepath", responseUser.getImagePath());
		assertEquals("admin", responseUser.getRole());
		assertEquals("admin", responseUser.getPosition());
		assertEquals("admin", responseUser.getUsername());
	}
	
	@Test
	public void updateUserTestOne(){
		User user=new User();
		user.setName("newadminname");
		user.setImagePath("newimagepath");
		user.setRole("newadminrole");
		user.setPosition("newposition");
		ResponseEntity responseEntity = userBO.updateUser(user);
		assertNotNull(responseEntity);
		Object responseObject=responseEntity.getBody();
		assertNotNull(responseObject);
		assertEquals(IllegalArgumentException.class, responseObject.getClass());
	}
	
	@Test
	public void updateUserTestTwo(){
		User user=new User();
		user.setId("570e9a41ffc0edc23e66a106");
		user.setName("newadminname");
		user.setImagePath("newimagepath");
		user.setRole("newadminrole");
		user.setPosition("newposition");
		ResponseEntity responseEntity = userBO.updateUser(user);
		assertNotNull(responseEntity);
		Object responseObject=responseEntity.getBody();
		assertNotNull(responseObject);
		assertEquals(User.class, responseObject.getClass());
		User responseUser=(User)responseObject;
		assertEquals("570e9a41ffc0edc23e66a106", responseUser.getId());
		assertEquals("newadminname", responseUser.getName());
		assertEquals("newimagepath", responseUser.getImagePath());
		assertEquals("newadminrole", responseUser.getRole());
		assertEquals("newposition", responseUser.getPosition());
	}
	
	@Test
	public void addUserTest(){
		User user=new User();
		user.setUsername("newusername");
		user.setName("newadminname");
		user.setRole("newadminrole");
		user.setPosition("newposition");
		user.setPassword("default");
		ResponseEntity responseEntity = userBO.addUser(user);
		assertNotNull(responseEntity);
		Object responseObject=responseEntity.getBody();
		assertNotNull(responseObject);
		assertEquals(User.class, responseObject.getClass());
		List<User> allUsersDB=userRepository.findAll();
		assertEquals(2, allUsersDB.size());
		for(User userDB:allUsersDB){
			if(!userDB.getId().equals("570e9a41ffc0edc23e66a106")){
				assertEquals("newadminname", userDB.getName());
				assertEquals("https://cdn1.iconfinder.com/data/icons/user-pictures/100/male3-512.png", userDB.getImagePath());
				assertEquals("newadminrole", userDB.getRole());
				assertEquals("newposition", userDB.getPosition());
				assertEquals("default", userDB.getPassword());
				assertEquals("newusername", userDB.getUsername());
			}
		}
	}
	
	@Test
	public void deleteUserTest(){
		List<User> allUsersDB=userRepository.findAll();
		assertEquals(2, allUsersDB.size());
		for(User userDB:allUsersDB){
			if(!userDB.getId().equals("570e9a41ffc0edc23e66a106")){
				userBO.deleteUser(userDB);
			}
		}
		allUsersDB=userRepository.findAll();
		assertNotNull(allUsersDB);
		assertEquals(1, allUsersDB.size());
		User dbOneUser=allUsersDB.get(0);
		assertEquals("570e9a41ffc0edc23e66a106", dbOneUser.getId());
	}
	
	@Test
	public void changeUserPasswordTest(){
		User user=new User();
		user.setOldPassword("admin");
		user.setPassword("admin123");
		ResponseEntity responseEntity = userBO.changeUserPassword(user, "12345");
		assertNotNull(responseEntity);
		Object responseObject=responseEntity.getBody();
		assertNotNull(responseObject);
		assertEquals(Boolean.class, responseObject.getClass());
		User savedDBUser=userRepository.findOne("570e9a41ffc0edc23e66a106");
		assertNotNull(savedDBUser);
		assertEquals("admin123", savedDBUser.getPassword());
	}
	
	@After
	public void deleteUserRecord(){
		User user=userRepository.findOne("570e9a41ffc0edc23e66a106");
		userRepository.delete(user);
	}
	
}
