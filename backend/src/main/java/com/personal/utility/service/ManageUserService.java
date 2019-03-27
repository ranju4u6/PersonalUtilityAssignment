package com.personal.utility.service;

import com.personal.utility.model.UserModel;

/**
 * Class to service the user related requests
 * @author renjith
 *
 */
public interface ManageUserService {
	
	/**
	 * Method to service the login request
	 * @param userName
	 * @param password
	 * @return
	 */
	UserModel login(String userName, String password);
	
	UserModel getUser(String userId);

}
