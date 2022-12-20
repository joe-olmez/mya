import { User } from './../../model/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';

const API_BASE_URL = environment.apiServerUrl; // http://localhost:8080

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  //signin
  login(username: string, password: string): Observable<any> {
    let signinUrl = API_BASE_URL + '/api/auth/signin';
    return this.http.post(signinUrl, { username, password }, httpOptions);
  }

  // signup
  register(user: User): Observable<any> {
    let signupUrl = API_BASE_URL + '/api/auth/signup';
    console.log('Signup url:', signupUrl);
    return this.http.post(signupUrl, user, httpOptions);
  }
}
