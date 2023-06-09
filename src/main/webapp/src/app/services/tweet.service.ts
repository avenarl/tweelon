import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tweet } from './tweet.model';

@Injectable({
  providedIn: 'root',
})
export class TweetService {
  private baseUrl = 'http://localhost:8080/api/v1/tweet';

  constructor(private http: HttpClient) {}

  getTweets(): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(`${this.baseUrl}/tweets`);
  }

  createTweet(userId: number, tweet: Tweet): Observable<Object> {
    return this.http.post(`${this.baseUrl}/${userId}`, tweet);
  }
}
