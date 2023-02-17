import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../model/user';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';

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
    this.getUser();
  }

  async getUser() {
    (await this.authService.getCurrentUser()).subscribe({
      next: (resData) => {
        this.user = resData;
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
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

  onBack() {
    this.router.navigateByUrl('/profile');
  }
}
