package com.personal.utility.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.utility.model.AddTaskModel;
import com.personal.utility.model.DeleteTaskModel;
import com.personal.utility.model.ToDoModel;
import com.personal.utility.service.ManageToDoService;
import com.personal.utility.service.ManageUserService;

/**
 * Handles all requests for Task/To Do
 * @author renjith
 *
 */

@RestController
public class TaskController {
	
	@Autowired
	private ManageToDoService toDoService;
	
	@Autowired
	private ManageUserService userService;
	
	/**
	 * Handles the task addition
	 * @param addTakModel
	 * @return
	 */
	@PostMapping("api/addTask")
	public ResponseEntity<ToDoModel> addTask(@RequestBody AddTaskModel addTakModel){
		System.out.println(addTakModel);
		String orgSessionId = userService.getUser(addTakModel.getNewTask().getUserId()).getSessionId();
		String receivedSessionId = addTakModel.getSessionId();
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<ToDoModel>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			ToDoModel todoModel = toDoService.addTask(addTakModel.getNewTask());
			return new ResponseEntity<ToDoModel>(todoModel, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<ToDoModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("api/deleteTask")
	public ResponseEntity<Boolean> deleteTask(@RequestBody DeleteTaskModel deleteTaskModel){
		String orgSessionId = userService.getUser(deleteTaskModel.getTask().getUserId()).getSessionId();
		String receivedSessionId = deleteTaskModel.getSessionId();
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			Boolean status = toDoService.deleteTask(deleteTaskModel.getTask().getId(), deleteTaskModel.getTask().getUserId());
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("api/getAllTask")
	public ResponseEntity<List<ToDoModel>> getAllTasks(@RequestParam("userId") String userId, 
			@RequestParam("sessionId") String sessionId){
		String orgSessionId = userService.getUser(userId).getSessionId();
		String receivedSessionId = sessionId;
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<List<ToDoModel>>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			List<ToDoModel> toDoList = toDoService.getAllTaskForUser(userId);
			return new ResponseEntity<List<ToDoModel>>(toDoList, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<ToDoModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
