import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_BASE_URL = environment.apiServerUrl; // http://localhost:5000

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  getAdminBoard(): Observable<any> {
    let url = API_BASE_URL + '/user/all'; // http://localhost:5000//user/all
    return this.http.get(url);
  }
}
