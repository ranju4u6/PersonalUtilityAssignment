import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { User } from '../user/user';
import { IUserType } from '../user/usertype';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  public addUserForm: FormGroup;
  public userList: User[];
  public userTypes: IUserType[];

  constructor(private formBuilder: FormBuilder, private userService: UserService) { }

  ngOnInit() {
    this.addUserForm = this.formBuilder.group({
      userName: ["",[Validators.required, Validators.minLength(3)]],
      passwordGroup: this.formBuilder.group({
        password: ["", [Validators.required, Validators.maxLength(3)]],
        confirmPassword:[""]
      }, {validator: conformPassword}),
      userTypeId:["", Validators.required]
    });

    this.userService.getAllUsers().subscribe(
      data=>{
        this.userList = data;
      }, errorData=>{

      }
    );

    this.userService.getAllUserTypes().subscribe(
      data=>{
        this.userTypes = data;
      }, errorData=>{

      }
    );
  }

}

function conformPassword(control: AbstractControl):{[key:string]:boolean}|null{
  const password = control.get("password");
  const confirmPassword = control.get("confirmPassword");

  if((password && confirmPassword) && (password.value === confirmPassword.value)){
    return null;
  }else{
    return {"passwordValidationStatus":true};
  }
}
