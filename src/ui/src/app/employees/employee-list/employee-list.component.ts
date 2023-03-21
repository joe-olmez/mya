import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css'],
})
export class EmployeeListComponent implements OnInit {
  empList: Employee[] = [];
  displayCreateButton = false;
  emp = new Employee();

  constructor(private empService: EmployeeService, private router: Router) {}

  ngOnInit(): void {
    this.getEmployeeList();
  }

  async getEmployeeList() {
    (await this.empService.getEmployeeList()).subscribe((data) => {
      this.empList = data;
    });
  }

  async createEmployee() {
    (await this.empService.createEmployee(this.emp)).subscribe({
      next: () => {
        this.displayCreateButton = false;
        this.goToEmpList();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  async deleteEmp(id: number) {
    (await this.empService.deleteEmployee(id)).subscribe((data) => {
      console.log(data);
      this.getEmployeeList();
    });
  }

  goToUpdateEmp(id: number) {
    this.router.navigate(['update-emp', id]);
  }

  goToEmpList() {
    this.getEmployeeList();
    this.router.navigate(['employees']);
  }

  toggleCreateButton() {
    this.displayCreateButton = !this.displayCreateButton;
  }

  onBack() {
    this.goToEmpList();
  }
}
