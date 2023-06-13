import { Component, OnInit } from '@angular/core';
import { Tweet } from '../models/tweet.model';
import { TweetService } from '../services/tweet.service';
import { User } from '../models/user.model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css'],
})
export class TweetComponent implements OnInit {
  tweets: Tweet[] = [];
  newTweet: Tweet = new Tweet();

  constructor(
    private tweetService: TweetService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getTweets();
  }

  getTweets(): void {
    this.tweetService.getTweets().subscribe((tweets: Tweet[]) => {
      this.tweets = tweets;
    });
  }

  createTweet(): void {
    const user = this.authService.getCurrentUser();
    this.tweetService.createTweet(user.id, this.newTweet).subscribe(() => {
      this.newTweet = new Tweet();
      this.getTweets();
    });
  }
}
