import { User } from './../../model/user';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

const API_BASE_URL = environment.apiServerUrl; // http://localhost:5000

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  async getAdminBoard() {
    let url = API_BASE_URL + '/user/all'; // http://localhost:5000//user/all
    return this.http.get(url);
  }

  async getUserByUsername(username: string) {
    let url = API_BASE_URL + `/user/`; // http://localhost:5000//user
    let param = this.addParam('username', username);
    return this.http.get(url, param);
  }

  async updateUser(user: User) {
    let url = API_BASE_URL + `/user/update`; // http://localhost:5000//user/update
    return this.http.put(url, user);
  }

  private addParam(key: string, value: any) {
    let httpParams = new HttpParams();
    httpParams = httpParams.append(key, value);
    let objectParam = { params: httpParams };
    return objectParam;
  }
}
