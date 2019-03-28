import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { User } from '../user/user';
import { IUserType } from '../user/usertype';
import { UserService } from '../user/user.service';
import { AddUserRequest } from '../user/adduser';
import { DeleteUserRequest } from '../user/deleteuser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  public addUserForm: FormGroup;
  public userList: User[];
  public userTypes: IUserType[];
  private user; User;

  constructor(private formBuilder: FormBuilder,
     private userService: UserService,
     private router: Router) { }

  ngOnInit() {
    this.addUserForm = this.formBuilder.group({
      userName: ["", [Validators.required, Validators.minLength(3)]],
      passwordGroup: this.formBuilder.group({
        password: ["", [Validators.required, Validators.minLength(3)]],
        confirmPassword: [""]
      }, { validator: confirmPassword }),
      userTypeId: ["", Validators.required]
    });

    this.user = this.userService.getUser();

    if(this.user.userType.userType === 'USER'){
      this.router.navigate(['welcome/home']);
    }

    this.userService.getAllUsers().subscribe(
      data => {
        this.userList = data;
      }, errorData => {

      }
    );

    this.userService.getAllUserTypes().subscribe(
      data => {
        this.userTypes = data;
      }, errorData => {

      }
    );
  }

  public addUser(): void {
    const newUserType = this.getUserType(this.addUserForm.get("userTypeId").value);
    const newUser: User = {
      userId: "",
      sessionId: "",
      userName: this.addUserForm.get("userName").value,
      password: btoa(this.addUserForm.get("passwordGroup.password").value),
      userType: newUserType
    }

    this.userService.addUser(new AddUserRequest(this.user.sessionId, this.user.userId, newUser)).subscribe(
      data => {
        console.log("new used added");
        this.userList.push(data);
        this.addUserForm.reset();
      }, errorData => {

      }
    );
  }

  public deleteUser(userId: string): void{
    if(! confirm("Do you want to delete user?")) return;
    if(this.user.userId === userId){
      confirm("Cannot delete logged in user");
      return;
    }

    this.userService.deleteUser
    (new DeleteUserRequest(this.user.sessionId, this.user.userId,this.getUser(userId))).subscribe(
      data=>{
        this.userList.splice(this.userList.indexOf(this.getUser(userId)), 1);
      }, errorData=>{

      }
    );
  }

  private getUser(userId:string):User{
    let identifiedUser= null;
    this.userList.forEach(
      user =>{
        if(user.userId === userId){
          identifiedUser = user;
        }
      }
    );

    return identifiedUser;
  }

  private getUserType(userTypeId: string): IUserType {
    let identifiedUserType = null;
    this.userTypes.forEach(
      userType => {
        if (userType.userTypeId === userTypeId) {
          identifiedUserType = userType;
        }
      }
    );

    return identifiedUserType;
  }

}

function confirmPassword(control: AbstractControl): { [key: string]: boolean } | null {
  const password = control.get("password");
  const confirmPassword = control.get("confirmPassword");

  if ((password && confirmPassword) && (password.value === confirmPassword.value)) {
    return null;
  } else {
    return { "passwordValidationStatus": true };
  }
}
