import { Component } from '@angular/core';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css'],
})
export class BoardUserComponent {
  content = '';

  constructor() {}

  ngOnInit(): void {
    this.content = 'To be implemented!';
  }
}
