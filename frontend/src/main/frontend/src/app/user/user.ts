import { IUserType } from './usertype';

export class User {
    constructor(public userName: string = '',
     public userId: string = '', 
     public userType: IUserType = null, 
     public sessionId: string = '',
     public password: string='') {
     
    }
}