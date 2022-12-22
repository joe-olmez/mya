import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { User } from './../../model/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  user: User = new User();

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  async onSubmit(signupForm: NgForm) {
    const formUser = signupForm.value;
    console.log('Form value: ', formUser);

    (await this.authService.register(formUser)).subscribe({
      next: (resData) => console.log('Response data:', resData),
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });

    this.router.navigateByUrl('/home');
  }
}
