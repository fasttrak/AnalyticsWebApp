package com.webapp.test.dao;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webapp.AnalyticsWebAppApplication;
import com.webapp.dao.UserDAO;
import com.webapp.dto.MapDTO;
import com.webapp.dto.PaginationDTO;
import com.webapp.entity.User;
import com.webapp.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations="../../../../Beans.xml", classes=AnalyticsWebAppApplication.class)
public class UserDAOTest {

	@Mock
	UserRepository userRepositoryMock;
	
	@Mock
	MongoTemplate mongoTemplateMock;
	
	@InjectMocks
	UserDAO userDAO;
	
	@Before
    public void setupMock() {
		MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void testMockCreation(){
        assertNotNull(userDAO);
    }
	
	@Test
    public void testValidateLogin() throws Exception{
		User dbUser=getUserObject();
        when(userRepositoryMock.findByUsername(any(String.class))).thenReturn(dbUser);
        User requestUser=getUserObject();
        User responseUser=userDAO.validateLogin(requestUser);
        assertNotNull(responseUser);
        assertEquals(dbUser.getId(), responseUser.getId());
    }
	
	@Test
    public void testUpdateUser() throws Exception{
		User dbUser=getUserObject();
        when(userRepositoryMock.findOne(any(String.class))).thenReturn(dbUser);
        when(userRepositoryMock.save(any(User.class))).thenReturn(dbUser);
        User requestUser=getUserObject();
        User responseUser=userDAO.updateUser(requestUser);
        assertNotNull(responseUser);
        assertEquals(responseUser, dbUser);
    }
	
	@Test
    public void testOneAddUser() throws Exception{
		User dbUser=getUserObject();
		dbUser.setImagePath("https://cdn1.iconfinder.com/data/icons/user-pictures/100/male3-512.png");
        when(userRepositoryMock.save(any(User.class))).thenReturn(dbUser);
        User requestUser=getUserObject();
        User responseUser=userDAO.addUser(requestUser);
        assertNotNull(responseUser);
        assertEquals(responseUser, dbUser);
    }
	
	@Test
    public void testDeleteUser() throws Exception{
		User dbUser=getUserObject();
		when(userRepositoryMock.findOne(any(String.class))).thenReturn(dbUser);
		doNothing().when(userRepositoryMock).delete(any(User.class));
    }
	
	@Test
    public void testTwoDeleteUser() throws Exception{
		doThrow(Exception.class).when(userRepositoryMock).delete(any(User.class));
    }
	
	@Test
    public void testGetUserFromSessionId() throws Exception{
		User dbUser=getUserObject();
		when(userRepositoryMock.findBySessionId(any(Integer.class))).thenReturn(dbUser);
		User responseUser=userDAO.getUserFromSessionId(null);
		assertNotNull(responseUser);
        assertEquals(responseUser, dbUser);
    }
	
	@Test
    public void testOneChangeUserPassword() throws Exception{
		User dbUser=getUserObject();
		when(userRepositoryMock.findBySessionId(any(Integer.class))).thenReturn(dbUser);
		when(userRepositoryMock.save(any(User.class))).thenReturn(dbUser);
		User reqUser=getUserObject();;
		reqUser.setOldPassword("anypassword");
		reqUser.setPassword("abcd");
		boolean result=userDAO.changeUserPassword(reqUser, 123);
        assertEquals(true, result);
    }
	
	@Test(expected=Exception.class)
    public void testTwoAddUser() throws Exception{
        when(userRepositoryMock.save(any(User.class))).thenReturn(null);
        User responseUser=userDAO.addUser(null);
    }
	
	@Test(expected=Exception.class)
    public void testTwoChangeUserPassword() throws Exception{
		User dbUser=getUserObject();
		when(userRepositoryMock.findBySessionId(any(Integer.class))).thenReturn(dbUser);
		when(userRepositoryMock.save(any(User.class))).thenReturn(dbUser);
		User reqUser=getUserObject();;
		reqUser.setOldPassword("anypassw0rdd");
		reqUser.setPassword("abcd");
		boolean result=userDAO.changeUserPassword(reqUser, 123);
    }
	
	@Test
    public void testGetAllUsers() throws Exception{
		PaginationDTO reqPaginationDTO=getPaginationDTO();
		User oneUser=getUserObject();
		User twoUser=getUserObject();
		List<User> users=new ArrayList<User>();
		users.add(oneUser);
		users.add(twoUser);
		when(mongoTemplateMock.find(anyObject(), eq(User.class))).thenReturn(users);
		when(mongoTemplateMock.count(anyObject(), eq(User.class))).thenReturn(1l);
		Set<String> validaColumns=new HashSet<String>();
		validaColumns.add("name");
		PaginationDTO resPaginationDTO=userDAO.getAllUsers(reqPaginationDTO, validaColumns);
		assertNotNull(resPaginationDTO);
		assertEquals(1l, resPaginationDTO.getTotalRecords());
    }
	
	private User getUserObject(){
		User user=new User();
		user.setUsername("anyname");
		user.setPassword("anypassword");
		user.setImagePath("http://imagepath");
		user.setName("swapnil joshi");
		user.setRole("admin");
		user.setId("12345");
		user.setPosition("infrastructure admin");
        return user;
	}
	
	private PaginationDTO getPaginationDTO(){
		PaginationDTO paginationDTO=new PaginationDTO();
		paginationDTO.setPage(1);
		paginationDTO.setSize(10);
		List<MapDTO> filterMapDTOs=new ArrayList<MapDTO>();
		MapDTO filterMapDTO=new MapDTO();
		filterMapDTO.setKey("name");
		filterMapDTO.setValueString("swa");
		filterMapDTOs.add(filterMapDTO);
		paginationDTO.setFilterParams(filterMapDTOs);
		List<MapDTO> sortingMapDTOs=new ArrayList<MapDTO>();
		MapDTO sortingMapDTO=new MapDTO();
		sortingMapDTO.setKey("name");
		sortingMapDTO.setValueString("asc");
		sortingMapDTOs.add(sortingMapDTO);
		paginationDTO.setSortingParams(sortingMapDTOs);
		return paginationDTO;
	}
}
