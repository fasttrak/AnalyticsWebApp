package com.webapp.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.webapp.dao.GroupDAO;
import com.webapp.dto.GroupDTO;
import com.webapp.dto.MapDTO;
import com.webapp.entity.Group;
import com.webapp.entity.User;

public class GroupBO {

	@Autowired
	GroupDAO groupDAO;
	
	public ResponseEntity updateGroup(GroupDTO groupDTO) {
		Group savedGroup=null;
		try{
			Group group=new Group();
			BeanUtils.copyProperties(groupDTO, group);
			Map<String, Boolean> tabAccessMap=new HashMap<String, Boolean>(groupDTO.getTabAccess().size());
			for(MapDTO mapDTO:groupDTO.getTabAccess()){
				tabAccessMap.put(mapDTO.getKey(),mapDTO.isValue());
			}
			group.setTabAccess(tabAccessMap);
			savedGroup=groupDAO.updateGroup(group);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Group>(savedGroup, HttpStatus.OK);
	}

	public ResponseEntity addGroup(GroupDTO groupDTO) {
		Group savedGroup=null;
		try{
			Group group=new Group();
			BeanUtils.copyProperties(groupDTO, group);
			Map<String, Boolean> tabAccessMap=new HashMap<String, Boolean>(groupDTO.getTabAccess().size());
			for(MapDTO mapDTO:groupDTO.getTabAccess()){
				tabAccessMap.put(mapDTO.getKey(),mapDTO.isValue());
			}
			group.setTabAccess(tabAccessMap);
			savedGroup=groupDAO.addGroup(group);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Group>(savedGroup, HttpStatus.OK);
	}
	
	public ResponseEntity deleteGroup(Group group) {
		try{
			groupDAO.deleteGroup(group);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	public ResponseEntity getAllGroups() {
		List<GroupDTO> allGroupDTOs=null;
		try{
			List<Group> allGroups=groupDAO.getAllGroups();
			allGroupDTOs=new ArrayList<GroupDTO>();
			for(Group group: allGroups){
				GroupDTO groupDTO=new GroupDTO();
				BeanUtils.copyProperties(group, groupDTO);
				Map<String, Boolean> accessMap=group.getTabAccess();
				List<MapDTO> mapDTOs=new ArrayList<MapDTO>(accessMap.size());
				groupDTO.setTabAccess(mapDTOs);
				for(Entry<String, Boolean> entry:accessMap.entrySet()){
					MapDTO mapDTO=new MapDTO();
					mapDTO.setKey(entry.getKey());
					mapDTO.setValue(entry.getValue());
					mapDTOs.add(mapDTO);
				}
				allGroupDTOs.add(groupDTO);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<GroupDTO>>(allGroupDTOs, HttpStatus.OK);
	}
	
	public ResponseEntity getGroup(String id) {
		Group group=null;
		try{
			group=groupDAO.getGroup(id);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}
	
}
