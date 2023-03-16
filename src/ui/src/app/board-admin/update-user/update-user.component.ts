import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css'],
})
export class UpdateUserComponent implements OnInit {
  id!: number;
  user = new User();

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  async ngOnInit(): Promise<void> {
    this.id = this.route.snapshot.params['id'];

    (await this.userService.getUserById(this.id)).subscribe({
      next: (resData) => {
        this.user = resData;
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  async onSubmit() {
    (await this.userService.updateUserById(this.id, this.user)).subscribe({
      next: (resData) => {
        console.log('Received data', resData);
        this.goToAdminBoard();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  goToAdminBoard() {
    this.router.navigate(['/admin-board']);
  }

  onBack() {
    this.goToAdminBoard();
  }
}
