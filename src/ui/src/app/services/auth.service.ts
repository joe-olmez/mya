import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { SigninRequest } from '../model/signin.request';
import { SignupRequest } from '../model/signup.request';
import { User } from '../model/user';
import { environment } from './../../environments/environment';

const TOKEN_KEY = 'auth-token';
const USERNAME_KEY = 'auth-username';
const USER_ROLE_KEY = 'auth-role';
const BASE_SERVER_URL = environment.apiServerUrl; // http://localhost:5000
const AUTH_URL = environment.authUrl; // http://localhost:5000//api/auth
//
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<User>;
  private currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(this.getToken())
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  setToken(jwt: any) {
    sessionStorage.setItem(TOKEN_KEY, jwt);
  }

  getToken(): any {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  getCurrentUser(): User {
    let rawUser = this.currentUserSubject.value;
    console.log('Response raw user: ', rawUser);
    const curUser = new User();
    curUser.email = rawUser.email;
    console.log('Response raw user email: ', rawUser.email);
    return this.currentUserSubject.value;
  }

  // *** Sign In ***
  async login(request: SigninRequest) {
    let url = AUTH_URL + '/signin';
    return this.http.post(url, request);
  }

  isLogged() {
    return sessionStorage.getItem(TOKEN_KEY) != null;
  }

  // *** Sign Up ***
  async register(request: SignupRequest) {
    let url = AUTH_URL + '/signup';
    console.log('Signup url:', url);
    return this.http.post(url, request);
  }

  // Logout
  logout(): void {
    window.sessionStorage.clear();
  }
}
