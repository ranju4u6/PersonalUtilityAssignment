import { ITodo } from './todo';

export class AddNewTask{
    sessionId:string;
    newTask: ITodo;

    constructor(sessionId: string, newTask: ITodo){
        this.sessionId = sessionId;
        this.newTask = newTask;
    }
}