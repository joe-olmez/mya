import { User } from './../../model/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';

const API_BASE_URL = environment.apiServerUrl; // http://localhost:5000

// const httpOptions = {
//   headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
// };

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  // Signin-POST: http://localhost:5000//api/auth/signin
  async login(user: User): Promise<Observable<any>> {
    let url = API_BASE_URL + '/api/auth/signin';
    return this.http.post(url, user);
  }

  // Signup-POST: http://localhost:5000//api/auth/signup
  async register(user: User): Promise<Observable<any>> {
    let url = API_BASE_URL + '/api/auth/signup';
    console.log('Signup url:', url);
    return this.http.post(url, user);
  }
}
