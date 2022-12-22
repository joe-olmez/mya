import { User } from './../../model/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';

const API_BASE_URL = environment.apiServerUrl; // http://localhost:5000
const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
//
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  // Sign Up
  async register(user: User): Promise<Observable<any>> {
    let url = API_BASE_URL + '/api/auth/signup'; // http://localhost:5000//api/auth/signup
    console.log('Signup url:', url);
    return this.http.post(url, user);
  }

  // Login
  async login(user: User): Promise<Observable<any>> {
    let url = API_BASE_URL + '/api/auth/signin'; // http://localhost:5000//api/auth/signin
    return this.http.post(url, user);
  }

  // Logout
  logout(): void {
    window.sessionStorage.clear();
  }

  //
  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
    console.log('---Logged in user:', user);
    const curUser = this.copyUser(user);
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(curUser));
  }

  public getCurrentUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }

  private copyUser(user: any): User {
    let curUser = new User();
    curUser.id = user.id;
    curUser.firstName = user.firstName;
    curUser.lastName = user.lastName;
    curUser.username = user.username;
    curUser.email = user.email;
    curUser.role = user.userType;
    return curUser;
  }

  public isAdmin(): boolean {
    const user: User = this.getCurrentUser();
    return user.role == 'ADMIN';
  }

  public isLoggedIn(): boolean {
    return this.getToken() != null;
  }

  public isAdminUser(user: User): boolean {
    return user.role == 'ADMIN';
  }
}
