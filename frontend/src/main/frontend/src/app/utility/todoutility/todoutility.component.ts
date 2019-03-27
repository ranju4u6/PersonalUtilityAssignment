import { Component, OnInit } from '@angular/core';
import { ToDoUtilityService } from './todoutility.service';
import { UserService } from 'src/app/user/user.service';
import { User } from 'src/app/user/user';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ITodo } from './todo';
import { DeleteTask } from './deletetask';
import { AddNewTask } from './addtask';
import * as moment from 'moment';

@Component({
  selector: 'app-todoutility',
  templateUrl: './todoutility.component.html',
  styleUrls: ['./todoutility.component.css']
})
export class TodoutilityComponent implements OnInit {
  
  private user: User;
  public addTaskForm: FormGroup;
  public taskList: ITodo[];

  constructor(private toDoService: ToDoUtilityService, 
    private userService: UserService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.user = this.userService.getUser();
    this.addTaskForm = this.formBuilder.group({
      task:['', Validators.required],
      targetDate:['', [Validators.required, dateValidator]],
      userId: this.user.userId
    });

    this.toDoService.getAllTasks(this.user.userId, this.user.sessionId).subscribe(
      data=>{
        this.taskList = data;
      }, errorData=>{
        console.log("Failed to get the tasks");
      }
    );
  }

  public deleteTask(taskId: string): void{
    if(!confirm("Do you want to delete this task?")){
      return;
    }

    let taskToDelete;
    this.taskList.forEach(task => {
      if(task.id === taskId){
        taskToDelete = task;
      }
    });

    this.toDoService.deleteTask(new DeleteTask(this.user.sessionId, taskToDelete)).subscribe(
      status=>{
        this.taskList.forEach(task => {
          if(task.id === taskId){
            this.taskList.splice(this.taskList.indexOf(task), 1);
          }
        });
      }, errorData=>{
        console.log("Unable to delete task");
      }
    );
  }

  public addTask(): void{
    let newTask = JSON.parse(JSON.stringify(this.addTaskForm.value));
    newTask.i

    this.toDoService.addNewTask(new AddNewTask(this.user.sessionId, newTask)).subscribe(
      newTask=>{
        this.taskList.push(newTask);
      }, errorData => {
        console.log("Unable to add task");
      }
    );
  }

}

function dateValidator(control: AbstractControl): {[key:string]:boolean}|null{
  // if(!(control && control.value)){
  //   return { 'dateVaidator': true };
  // }

  if (control && control.value && !moment(control.value, 'YYYY-MM-DD', true).isValid()) {
    return { 'dateVaidator': true };
  }
  return null;
}
