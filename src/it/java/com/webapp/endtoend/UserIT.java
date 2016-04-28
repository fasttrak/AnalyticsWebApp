package com.webapp.endtoend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.AnalyticsWebAppApplication;
import com.webapp.dto.PaginationDTO;
import com.webapp.entity.User;
import com.webapp.repository.UserRepository;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations="../../../Beans.xml", classes=AnalyticsWebAppApplication.class)
@ActiveProfiles(profiles="test")
@WebIntegrationTest
public class UserIT {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private RestTemplate restTemplate = new TestRestTemplate();
	
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
	public void validateLoginTest() throws JsonProcessingException{
		User reqUser=new User();
		reqUser.setUsername("admin");
		reqUser.setPassword("admin");
		ResponseEntity<User> response=restTemplate.postForEntity("http://localhost:8083/webapp/user/login", reqUser, User.class, Collections.EMPTY_MAP);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		Object responseBody = response.getBody();
		assertNotNull(responseBody);
		assertEquals(User.class, responseBody.getClass());
	}
	
	@Test
	public void getUserTest() throws JsonProcessingException{
		User reqUser=new User();
		reqUser.setUsername("admin");
		reqUser.setPassword("admin");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Cookie", "sessionid=12345");
		requestHeaders.add("Cookie", "username=admin");
		HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
		ResponseEntity response = restTemplate.exchange(
			    "http://localhost:8083/webapp/user/getUser",
			    HttpMethod.GET,
			    requestEntity,
			    User.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		Object responseBody = response.getBody();
		assertNotNull(responseBody);
		assertEquals(User.class, responseBody.getClass());
	}
	
	@Test
	public void updateUserTest() throws JsonProcessingException{
		User reqUser=new User();
		reqUser.setId("570e9a41ffc0edc23e66a106");
		reqUser.setName("newadminname");
		reqUser.setImagePath("newimagepath");
		reqUser.setRole("newadminrole");
		reqUser.setPosition("newposition");
		ResponseEntity<User> response=restTemplate.postForEntity("http://localhost:8083/webapp/user/updateUser", reqUser, User.class, Collections.EMPTY_MAP);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		Object responseBody = response.getBody();
		assertNotNull(responseBody);
		assertEquals(User.class, responseBody.getClass());
		User responseUser=(User)responseBody;
		assertEquals("570e9a41ffc0edc23e66a106", responseUser.getId());
		assertEquals("newadminname", responseUser.getName());
		assertEquals("newimagepath", responseUser.getImagePath());
		assertEquals("newadminrole", responseUser.getRole());
		assertEquals("newposition", responseUser.getPosition());
	}
	
	@Test
	public void addUserTest() throws JsonProcessingException{
		userRepository.deleteAll();
		User reqUser=new User();
		reqUser.setUsername("newusername");
		reqUser.setName("newadminname");
		reqUser.setRole("newadminrole");
		reqUser.setPosition("newposition");
		ResponseEntity<User> response=restTemplate.postForEntity("http://localhost:8083/webapp/user/addUser", 
				reqUser, User.class, Collections.EMPTY_MAP);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		Object responseBody = response.getBody();
		assertNotNull(responseBody);
		assertEquals(User.class, responseBody.getClass());
		List<User> users=userRepository.findAll();
		assertNotNull(users);
		assertEquals(1, users.size());
		User responseUser=(User)users.get(0);
		assertEquals("newusername", responseUser.getUsername());
		assertEquals("newadminname", responseUser.getName());
		assertEquals("https://cdn1.iconfinder.com/data/icons/user-pictures/100/male3-512.png", 
				responseUser.getImagePath());
		assertEquals("newadminrole", responseUser.getRole());
		assertEquals("newposition", responseUser.getPosition());
	}
	
	@Test
	public void deleteUserTest() throws JsonProcessingException{
		List<User> users=userRepository.findAll();
		assertNotNull(users);
		assertEquals(1, users.size());
		User user=new User();
		user.setId("570e9a41ffc0edc23e66a106");
		ResponseEntity<Boolean> response=restTemplate.postForEntity("http://localhost:8083/webapp/user/deleteUser", user, Boolean.class, Collections.EMPTY_MAP);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		users=userRepository.findAll();
		assertNotNull(users);
		assertEquals(0, users.size());
	}
	
	@Test
	public void changeUserPasswordTest() throws JsonProcessingException{
		User reqUser=new User();
		reqUser.setPassword("newpassword");
		reqUser.setOldPassword("admin");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Cookie", "sessionid=12345");
		HttpEntity<User> requestEntity = new HttpEntity(reqUser, requestHeaders);
		ResponseEntity response = restTemplate.exchange(
			    "http://localhost:8083/webapp/user/changeUserPassword",
			    HttpMethod.POST,
			    requestEntity,
			    Boolean.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		User dbUser=userRepository.findOne("570e9a41ffc0edc23e66a106");
		assertNotNull(dbUser);
		assertEquals("newpassword", dbUser.getPassword());
	}
	
	@Test
	public void getAllUsersTest() throws JsonProcessingException{
		PaginationDTO paginationDTO=new PaginationDTO();
		paginationDTO.setPage(1);
		paginationDTO.setSize(10);
		ResponseEntity<PaginationDTO> response=restTemplate.postForEntity("http://localhost:8083/webapp/user/getAllUsers", paginationDTO, PaginationDTO.class, Collections.EMPTY_MAP);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		Object responseBody=response.getBody();
		assertEquals(PaginationDTO.class, responseBody.getClass());
		PaginationDTO page=(PaginationDTO)responseBody;
		assertNotNull(page.getData());
		assertEquals(ArrayList.class, page.getData().getClass());
		ArrayList<User> users=(ArrayList)page.getData();
		assertEquals(1, users.size());
	}
	
	@After
	public void deleteUserRecord(){
		userRepository.deleteAll();
	}
	
}
