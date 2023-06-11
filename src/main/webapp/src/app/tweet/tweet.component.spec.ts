import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { TweetComponent } from './tweet.component';
import { TweetService } from '../services/tweet.service';
import { Tweet } from '../models/tweet.model';

describe('TweetComponent', () => {
  let component: TweetComponent;
  let fixture: ComponentFixture<TweetComponent>;
  let tweetService: TweetService;

  const tweetServiceStub = {
    getTweets: () => of([{} as Tweet]),
    createTweet: () => of({}),
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TweetComponent],
      imports: [HttpClientTestingModule],
      providers: [{ provide: TweetService, useValue: tweetServiceStub }],
    }).compileComponents();

    fixture = TestBed.createComponent(TweetComponent);
    component = fixture.componentInstance;
    tweetService = TestBed.inject(TweetService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call getTweets on init', () => {
    const spy = spyOn(tweetService, 'getTweets').and.callThrough();
    component.ngOnInit();
    expect(spy).toHaveBeenCalled();
  });

  it('should call createTweet when creating a tweet', () => {
    const spy = spyOn(tweetService, 'createTweet').and.callThrough();
    component.createTweet();
    expect(spy).toHaveBeenCalled();
  });
});
