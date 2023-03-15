import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css'],
})
export class BoardAdminComponent implements OnInit {
  content = '';
  users: User[] = [];
  user: User;
  isShowUserList = false;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.content = 'Admin Dashboard';
  }

  async getAllUsers() {
    (await this.userService.getAllUsers()).subscribe((data) => {
      this.users = data;
      this.isShowUserList = true;
    });
  }

  updateRates() {
    this.isShowUserList = false;
    //this.router.navigate(['rate-details', id]);
  }

  goToConverter() {
    this.isShowUserList = false;
    //this.router.navigate(['rate-details', id]);
  }

  async updateUser(user: User) {
    (await this.userService.updateUser(this.user)).subscribe({
      next: (d) => {
        console.log('Updated');
        this.getAllUsers();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user).subscribe((data) => {
      console.log(data);
      this.getAllUsers();
    });
  }
}
