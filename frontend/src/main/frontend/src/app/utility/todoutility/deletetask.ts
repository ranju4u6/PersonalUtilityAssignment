import { ITodo } from './todo';

export class DeleteTask{
    sessionId:string;
    task: ITodo;

    constructor(sessionId: string, task: ITodo){
        this.sessionId = sessionId;
        this.task = task;
    }
}