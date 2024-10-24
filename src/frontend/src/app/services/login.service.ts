import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  login(email: string, password: string){
    const url = 'http://localhost:8080/auth/login'
    return this.httpClient.post<LoginResponse>(url, {email, password}).pipe(
      tap ((value) => {
        console.log(value)
        sessionStorage.setItem("auth-token", value.token)
      })
    )
  }
}
