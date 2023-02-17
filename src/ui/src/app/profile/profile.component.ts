import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { AuthService } from './../services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  curUser: User;

  constructor(private authService: AuthService) {}

  async ngOnInit() {
    this.curUser = this.authService.getCurrentUser();
  }
}
