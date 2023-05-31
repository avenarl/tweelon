import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  user: any = {};

  constructor(private userService: UserService) {}

  register() {
    this.userService.register(this.user).subscribe(
      (user) => console.log('User registered successfully!'),
      (error) => console.log('Error registering user', error)
    );
  }
}
