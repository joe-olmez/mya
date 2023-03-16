import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { SigninRequest } from '../model/signin.request';
import { SignupRequest } from '../model/signup.request';
import { User } from '../model/user';
import { environment } from './../../environments/environment';

const AUTH_URL = environment.authUrl; // http://localhost:5000//api/auth
const BASE_URL = environment.apiServerUrl; // http://localhost:5000
const USER_URL = BASE_URL + '/api/v1/users';
const TOKEN_KEY = 'auth-token';
const HEADER_KEY = 'Authorization';
const BEARER_KEY = 'Bearer ';
//
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser: User;
  helper = new JwtHelperService();

  constructor(private http: HttpClient) {}

  setToken(token: string) {
    console.log('Token in authService: ', token);
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

  private decodeToken(token: string): any {
    if (token == null) {
      return '';
    }
    return this.helper.decodeToken(token);
  }

  async getCurrentUser(): Promise<Observable<any>> {
    const url = USER_URL + '/username';
    let httpOptions = this.addHeaderAndParam(
      'username',
      this.getCurrentUserName()
    );
    return this.http.get(url, httpOptions);
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

  // For each request, add the JWT to the header and send it to the server.
  getHeaderObject(): object {
    let valueHeader = new HttpHeaders().set(
      HEADER_KEY,
      BEARER_KEY + this.getToken()
    );
    return { headers: valueHeader };
  }

  addHeaderAndParam(key: string, paramValue: any): object {
    let valueHeader = new HttpHeaders().set(
      HEADER_KEY,
      BEARER_KEY + this.getToken()
    );
    let valueParam = new HttpParams().set(key, paramValue);
    return {
      headers: valueHeader,
      params: valueParam,
    };
  }
}
