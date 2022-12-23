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
    let params = new HttpParams();
    params = params.append('username', 'joe');
    let url = API_BASE_URL + `/user/`; // http://localhost:5000//user
    return this.http.get(url, { params: params });
  }
}
