import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private httpClient: HttpClient) { }

  register(userData: any): Observable<any> {
    const url = 'http://localhost:8080/auth/register'
    return this.httpClient.post(url, userData);
  }
}
