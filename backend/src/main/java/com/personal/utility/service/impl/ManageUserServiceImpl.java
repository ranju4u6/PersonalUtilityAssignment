package com.personal.utility.service.impl;

import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.entity.User;
import com.personal.utility.model.UserModel;
import com.personal.utility.model.UserTypeModel;
import com.personal.utility.repository.UserRepository;
import com.personal.utility.repository.UserTypeRepository;
import com.personal.utility.service.ManageUserService;

@Service
public class ManageUserServiceImpl implements ManageUserService {
	
	@Autowired
	private UserRepository userRepo;
	
//	@Autowired
//	private UserTypeRepository userTypeRepo;

	@Override
	@Transactional
	public UserModel login(String userName, String password) {
		User user = userRepo.findByUserName(userName);
		
		String decodedPassword = new String(Base64.getDecoder().decode(password));
		String storedPassword = new String(Base64.getDecoder().decode(user.getPassword()));
		
		if(user !=null && decodedPassword.equals(storedPassword) && user.isActive()) {
			setSessionId(user);
			
			UserModel userModel = new UserModel();
			userModel.setUserId(user.getId());
			userModel.setUserName(user.getUserName());
			userModel.setSessionId(user.getSessionId());
			
			UserTypeModel userType = new UserTypeModel();
			userType.setUserTypeId(user.getUserType().getId());
			userType.setUserType(user.getUserType().getUserType());
			userModel.setUserType(userType);
			
			return userModel;
		}
		
		return null;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private void setSessionId(User user) {
		String sessionId = UUID.randomUUID().toString();
		user.setSessionId(sessionId);
		
		userRepo.save(user);
	}
	
	@Transactional(readOnly=true)
	public UserModel getUser(String userId) {
		User user = userRepo.getOne(userId);
		UserModel userModel = new UserModel();
		userModel.setSessionId(user.getSessionId());
		userModel.setUserId(user.getId());
		userModel.setUserName(user.getUserName());
		
		UserTypeModel userType = new UserTypeModel();
		userType.setUserTypeId(user.getUserType().getId());
		userType.setUserType(user.getUserType().getUserType());
		userModel.setUserType(userType);
		
		return userModel;
	}

}
