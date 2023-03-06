import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignupRequest } from '../model/signup.request';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  signup = new SignupRequest();

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  onSubmit() {
    console.log(this.signup);
    this.signupUser();
  }

  async signupUser() {
    (await this.authService.register(this.signup)).subscribe({
      next: () => {
        console.log('Signup User:', this.signup);
        alert('Created a new user');
        this.goToHome();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  goToHome() {
    this.router.navigateByUrl('/home');
  }
}
