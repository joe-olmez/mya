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

  // For each request, add the JWT to the header and send it to the server.
  getHeaderObject(): object {
    let headersValue = new HttpHeaders().set(
      HEADER_KEY,
      BEARER_KEY + this.getToken()
    );
    const headerObject = {
      headers: headersValue,
    };
    return headerObject;
  }

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
    const url = BASE_URL + '/api/v1/users/username';
    let param = this.addParam('username', this.getCurrentUserName());
    return this.http.get(url, param);
  }

  addParam(key: string, value: any) {
    let httpParams = new HttpParams();
    httpParams = httpParams.append(key, value);
    let objectParam = { params: httpParams };
    return objectParam;
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
