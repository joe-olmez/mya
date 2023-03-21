import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Employee } from '../model/employee';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private empURL = environment.employeesUrl;
  private headerObj;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.headerObj = this.authService.getHeaderObject();
  }

  // POST
  async createEmployee(emp: Employee) {
    let url = this.empURL + `/add`;
    return this.http.post(url, emp, this.headerObj);
  }

  // GET
  async getEmployeeList(): Promise<Observable<Employee[]>> {
    let url = this.empURL + `/all`;
    console.log('Request url: ', url);
    return this.http.get<Employee[]>(url, this.headerObj);
  }

  // PUT
  async updateEmployee(emp: Employee): Promise<Observable<Object>> {
    let url = this.empURL + `/update`;
    return this.http.put(url, emp, this.headerObj);
  }

  async getEmployeeById(id: number): Promise<Observable<Employee>> {
    let url = this.empURL + `/${id}`;
    console.log('Request url: ', url);
    return this.http.get<Employee>(url, this.headerObj);
  }

  async deleteEmployee(id: number): Promise<Observable<Object>> {
    let url = this.empURL + `/delete/${id}`;
    return this.http.delete(url, this.headerObj);
  }
}
