import { User } from './user';

export class AddUserRequest{
    sessionId: string;
	updatedBy: string;
    userModel: User;
    
    constructor(sessionId:string, updatedBy:string, userModel: User){
        this.sessionId = sessionId;
        this.updatedBy = updatedBy;
        this.userModel = userModel;
    }
}