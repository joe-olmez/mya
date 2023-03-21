import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-update-rate',
  templateUrl: './update-emp.component.html',
  styleUrls: ['./update-emp.component.css'],
})
export class UpdateEmpComponent implements OnInit {
  id!: number;
  emp: Employee = new Employee();

  constructor(
    private empService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  async ngOnInit(): Promise<void> {
    this.id = this.route.snapshot.params['id'];

    (await this.empService.getEmployeeById(this.id)).subscribe({
      next: (data) => {
        this.emp = data;
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  async onSubmit() {
    this.emp.id = this.id;
    (await this.empService.updateEmployee(this.emp)).subscribe({
      next: (data) => {
        console.log('Received data', data);
        this.goToEmpList();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  goToEmpList() {
    this.router.navigate(['/employees']);
  }

  onBack() {
    this.goToEmpList();
  }
}
