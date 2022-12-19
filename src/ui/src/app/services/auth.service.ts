import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';

const apiBaseUrl = environment.apiServerUrl; // http://localhost:8080

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
    let siginUrl = apiBaseUrl + '/api/auth/signin';
    return this.http.post(siginUrl, { username, password }, httpOptions);
  }

  // signup
  register(username: string, email: string, password: string): Observable<any> {
    let sigupUrl = apiBaseUrl + '/api/auth/signup';
    return this.http.post(sigupUrl, { username, email, password }, httpOptions);
  }
}
