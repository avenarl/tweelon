import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { TweetService } from './tweet.service';
import { Tweet } from '../models/tweet.model';

describe('TweetService', () => {
  let service: TweetService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TweetService],
    });
    service = TestBed.inject(TweetService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch tweets', () => {
    const mockTweets: Tweet[] = [new Tweet(), new Tweet(), new Tweet()];

    service.getTweets().subscribe((tweets) => {
      expect(tweets.length).toBe(3);
      expect(tweets).toEqual(mockTweets);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/v1/tweet/tweets');
    expect(req.request.method).toBe('GET');
    req.flush(mockTweets);
  });

  it('create a tweet', () => {
    const newTweet = new Tweet();
    const userId = 1;

    service.createTweet(userId, newTweet).subscribe((res) => {
      expect(res).toEqual({});
    });

    const req = httpMock.expectOne(
      'http://localhost:8080/api/v1/tweet/' + userId
    );
    expect(req.request.method).toBe('POST');
    req.flush({});
  });
});
