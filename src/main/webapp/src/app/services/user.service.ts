import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiServerUrl = 'http://localhost:8080/api/v1/user';

  constructor(private http: HttpClient) {}

  register(user: any) {
    return this.http.post<any[]>(`${this.apiServerUrl}/register`, user);
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/api/v1/user/users');
  }
}
