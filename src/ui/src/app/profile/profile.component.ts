import { UserService } from './../services/user.service';
import { AuthService } from './../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  curUser: any;
  curUserToken: any;

  constructor(
    private authService: AuthService,
    private userService: UserService
  ) {}

  async ngOnInit() {
    this.getCurrentUser();
    this.curUserToken = this.authService.getToken();
  }

  async getCurrentUser() {
    let username = this.authService.getUsername();
    if (username != null) {
      (await this.userService.getUserByUsername(username)).subscribe({
        next: (resData) => {
          console.log('Current User in Profile:', resData);
          this.curUser = resData;
          return resData;
        },
        error: (error) => console.error(error),
        complete: () => console.info('complete'),
      });
    }
  }
}
