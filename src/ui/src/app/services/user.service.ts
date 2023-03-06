import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PasswordWrapper } from '../model/password.wrapper';
import { User } from '../model/user';
import { environment } from './../../environments/environment';
import { AuthService } from './auth.service';

const API_BASE_URL = environment.apiServerUrl; // http://localhost:5000
const USER_URL = API_BASE_URL + '/api/v1/users';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private headerObj;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.headerObj = this.authService.getHeaderObject();
  }

  async getAdminBoard() {
    return this.http.get(USER_URL, this.headerObj);
  }

  async getUserByUsername(username: string) {
    let url = USER_URL + `'/username`;
    let httpOptions = this.authService.addHeaderAndParam('username', username);
    return this.http.get(url, httpOptions);
  }

  async updateUser(user: User) {
    return this.http.put(USER_URL, user, this.headerObj);
  }

  async updatePassword(passWrapper: PasswordWrapper) {
    let url = USER_URL + `/pass`;
    return this.http.put(url, passWrapper);
  }
}
