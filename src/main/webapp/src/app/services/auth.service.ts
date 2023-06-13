import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser?: User;
  constructor() {}

  login(user: User): Observable<User> {
    this.currentUser = user;
    return of(this.currentUser);
  }

  getCurrentUser(): User {
    if (!this.currentUser) {
      throw new Error('No user is currently logged in.');
    }
    return this.currentUser;
  }
}
