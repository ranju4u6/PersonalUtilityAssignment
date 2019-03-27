package com.personal.utility.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.utility.model.UserModel;
import com.personal.utility.service.ManageUserService;

/**
 * Controller class that handles the login request or user related operations
 * @author renjith
 *
 */

@RestController
public class LoginController {
	
	Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ManageUserService userService;

	@RequestMapping("api/userLogin")
	public ResponseEntity<UserModel> login(@RequestParam(name="userName") String userName,
			@RequestParam(name="password") String password){
		log.info("Received login request");
		UserModel user = this.userService.login(userName, password);
		
		if(user != null) return new ResponseEntity<UserModel>(user, HttpStatus.OK);
		else {
			log.info("Un Authorized User {}", userName);
			return new ResponseEntity<UserModel>(HttpStatus.UNAUTHORIZED);
		}
	}

}
