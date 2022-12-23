import { UserService } from './user.service';
import { User } from './../../model/user';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';

const TOKEN_KEY = 'auth-token';
const USERNAME_KEY = 'auth-username';
const USER_ROLE_KEY = 'auth-role';
const API_BASE_URL = environment.apiServerUrl; // http://localhost:5000
//
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private userService: UserService) {}

  public storeToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    if (token != null) {
      window.sessionStorage.setItem(TOKEN_KEY, token);
    }
  }

  public storeUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    if (username != null) {
      window.sessionStorage.setItem(USERNAME_KEY, username);
    }
  }

  public storeUserRole(role: string) {
    window.sessionStorage.removeItem(USER_ROLE_KEY);
    if (role != null) {
      window.sessionStorage.setItem(USER_ROLE_KEY, role);
    }
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public getUsername(): string | null {
    return window.sessionStorage.getItem(USERNAME_KEY);
  }

  public getUserRole(): string | null {
    return window.sessionStorage.getItem(USER_ROLE_KEY);
  }

  public isAdmin(): boolean {
    const userRole = this.getUserRole();
    if (userRole == null) {
      return false;
    }
    let role = userRole.toLowerCase();
    return role == 'admin';
  }

  public isLoggedIn(): boolean {
    return this.getToken() != null;
  }

  // Sign Up *******************************************
  async register(user: User): Promise<Observable<any>> {
    let url = API_BASE_URL + '/api/auth/signup'; // http://localhost:5000//api/auth/signup
    console.log('Signup url:', url);
    return this.http.post(url, user);
  }

  // Login ********************************************
  async login(user: User): Promise<Observable<any>> {
    let url = API_BASE_URL + '/api/auth/signin'; // http://localhost:5000//api/auth/signin
    return this.http.post(url, user);
  }

  // Logout
  logout(): void {
    window.sessionStorage.clear();
  }
}
