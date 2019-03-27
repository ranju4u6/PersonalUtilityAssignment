import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { User } from './user';
import { tap } from 'rxjs/operators';

@Injectable({
    providedIn:"root"
})
export class UserService{
    private loginUrl: string = "api/userLogin";
    private USER_COOKIE: string= "userCookie";

    redirectUrl: string;
    user: User;

    constructor(private http: HttpClient, private cookieService: CookieService){
    }

    public login(userName: string, password: string):Observable<User>{
        let param:any={'userName':userName, 'password':btoa(password)};
        return this.http.get<User>(this.loginUrl,{params: param}).pipe(
            tap(user=> {
                console.log(JSON.stringify(user));
                this.cookieService.set(this.USER_COOKIE, JSON.stringify(user), .125);
            })
        );
    }

    public isLoggedIn(): boolean{
        if(! this.user){
            if(this.cookieService.get(this.USER_COOKIE))
                this.user = JSON.parse(this.cookieService.get(this.USER_COOKIE));
        }

        return !!this.user;
    }

    public getUser(): User {
        return this.user;
    }
    public setUser(value: User) {
        this.user = value;
    }

    public logout(): void{
        this.cookieService.deleteAll('/','/');
        this.cookieService.deleteAll('../ ');
        this.cookieService.delete(this.USER_COOKIE,'/','/');
        this.user = null;
    }
    
    public setRedirectUrl(url: string): void{
        this.redirectUrl = url;
    }



}

