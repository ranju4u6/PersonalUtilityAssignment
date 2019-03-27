package com.personal.utility.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.entity.ToDo;
import com.personal.utility.entity.User;
import com.personal.utility.model.ToDoModel;
import com.personal.utility.repository.ToDoRepository;
import com.personal.utility.repository.UserRepository;
import com.personal.utility.service.ManageToDoService;

@Service
public class ManageToDoServiceImpl implements ManageToDoService {
	
	@Autowired
	private ToDoRepository toDoRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional
	public ToDoModel addTask(ToDoModel toDoModel) {
		ToDo toDo = new ToDo();
		toDo.setActive(true);
		toDo.setTask(toDoModel.getTask());
		toDo.setUserId(userRepo.getOne(toDoModel.getUserId()));
		toDo.setUpdatedBy(toDoModel.getUserId());
		toDo.setUpdatedTime(new Date());
		try {
			toDo.setTargetDate(new SimpleDateFormat("YYYY-MM-DD").parse(toDoModel.getTargetDate()));
		} catch (ParseException e) {
		}
		
		toDoRepo.save(toDo);
		
		toDoModel.setId(toDo.getId());
		
		return toDoModel;
		
	}

	@Override
	@Transactional
	public List<ToDoModel> getAllTaskForUser(String userId) {
		User user = userRepo.getOne(userId);
		
		List<ToDoModel> toDoList = toDoRepo.findByUserId(user).stream().filter(ToDo::isActive).map((toDo)->{
			ToDoModel toDoModel = new ToDoModel();
			toDoModel.setId(toDo.getId());
			toDoModel.setTask(toDo.getTask());			
			toDoModel.setTargetDate(new SimpleDateFormat("yyyy-MM-dd").format(toDo.getTargetDate()));//for the time being
			toDoModel.setUserId(userId);
			
			return toDoModel;
		}).collect(Collectors.toCollection(ArrayList<ToDoModel>::new));
		
		return toDoList;
	}

	@Override
	@Transactional
	public boolean deleteTask(String taskId, String updatedUser) {
		ToDo toDo = toDoRepo.getOne(taskId);
		toDo.setActive(false);
		toDo.setUpdatedBy(updatedUser);
		
		toDoRepo.save(toDo);
		
		return true;
	}

	@Override
	@Transactional
	public List<ToDoModel> updateTask(List<ToDoModel> toDoList) {
		
		for(ToDoModel toDoModel: toDoList) {
			ToDo toDo = toDoRepo.getOne(toDoModel.getId());
			toDo.setTask(toDoModel.getTask());
			toDo.setTargetDate(new Date());//for the time being
			
			toDoRepo.save(toDo);
		}
		
		return toDoList;
	}

}
