import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PasswordWrapper } from 'src/app/model/password.wrapper';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css'],
})
export class UpdatePasswordComponent implements OnInit {
  passWrapper = new PasswordWrapper();
  confirmPassword = '';

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.passWrapper.username = this.authService.getCurrentUserName();
  }

  async onSubmit() {
    if (this.passWrapper.rawPassword == this.confirmPassword) {
      (await this.userService.updatePassword(this.passWrapper)).subscribe({
        next: () => {
          console.log('Updated!');
          this.goToProfile();
        },
        error: (resError) => console.error(resError),
        complete: () => console.info('complete'),
      });
    } else {
      alert('No match!');
    }
  }

  goToProfile() {
    this.router.navigateByUrl('/profile');
  }

  onBack() {
    this.goToProfile();
  }
}
