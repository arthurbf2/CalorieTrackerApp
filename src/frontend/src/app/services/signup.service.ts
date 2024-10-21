import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  private apiUrl = 'http://localhost:8080'

  constructor(private httpClient: HttpClient) { }

  register(userData: any): Observable<any> {
    const url = `${this.apiUrl}/auth/register`;
    return this.httpClient.post(url, userData);
  }
}
