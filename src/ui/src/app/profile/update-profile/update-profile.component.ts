import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { UserService } from './../../services/user.service';
import { AuthService } from './../../services/auth.service';
import { User } from './../../../model/user';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css'],
})
export class UpdateProfileComponent implements OnInit {
  user = new User();

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCurrentUser();
  }

  async getCurrentUser() {
    let username = this.authService.getUsername();
    if (username != null) {
      (await this.userService.getUserByUsername(username)).subscribe({
        next: (resData) => {
          console.log('Current User in Profile:', resData);
          this.user = resData;
          return resData;
        },
        error: (error) => console.error(error),
        complete: () => console.info('complete'),
      });
    }
  }

  async onSubmit(updateForm: NgForm) {
    let formUser: User = updateForm.value;
    formUser.id = this.user.id;
    console.log('Form value: ', formUser);

    (await this.userService.updateUser(formUser)).subscribe({
      next: (resData) => {
        console.log('Response data:', resData);
        this.router.navigateByUrl('/profile');
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }
}
