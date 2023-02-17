import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { SigninRequest } from '../model/signin.request';
import { SignupRequest } from '../model/signup.request';
import { User } from '../model/user';
import { environment } from './../../environments/environment';

const TOKEN_KEY = 'auth-token';
const AUTH_URL = environment.authUrl; // http://localhost:5000//api/auth
const BASE_URL = environment.apiServerUrl; // http://localhost:5000
//
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser: User;
  helper = new JwtHelperService();

  constructor(private http: HttpClient) {}

  setToken(token: string) {
    console.log('in AutService token', token);
    sessionStorage.setItem(TOKEN_KEY, token);
  }

  getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  getCurrentUserName(): string {
    let token = this.getToken();
    if (token != null) {
      let decoded = this.decodeToken(token);
      return decoded.sub;
    }
    return '';
  }

  getCurrentUserRole(): string {
    let token = this.getToken();
    if (token != null) {
      let decoded = this.decodeToken(token);
      return decoded.authorities[0].authority;
    }
    return '';
  }

  decodeToken(token: string): any {
    if (token == null) {
      return '';
    }
    return this.helper.decodeToken(token);
  }

  async getCurrentUser(): Promise<Observable<any>> {
    let curUsername = this.getCurrentUserName();
    let queryParams = new HttpParams();
    queryParams = queryParams.append('username', curUsername);
    let url = BASE_URL + '/user';
    return this.http.get(url, { params: queryParams });
  }

  // *** Sign In ***
  async login(request: SigninRequest): Promise<Observable<any>> {
    let url = AUTH_URL + '/signin';
    return this.http.post(url, request);
  }

  isLogged() {
    return sessionStorage.getItem(TOKEN_KEY) != null;
  }

  isAdmin(): boolean {
    return this.getCurrentUserRole() == 'Admin';
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
