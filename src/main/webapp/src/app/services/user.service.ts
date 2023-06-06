import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiServerUrl = 'http://localhost:8080/api/v1/user';

  constructor(private http: HttpClient) {}

  register(user: User) {
    return this.http.post<User>(`${this.apiServerUrl}/register`, user);
  }

  login(user: User) {
    return this.http.post<User>(`${this.apiServerUrl}/login`, user);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiServerUrl}/users`);
  }
}
