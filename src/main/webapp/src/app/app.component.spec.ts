import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { UserService } from './services/user.service';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from './models/user.model';
import { of } from 'rxjs';

describe('AppComponent', () => {
  let userService: UserService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AppComponent],
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [UserService],
    }).compileComponents();

    userService = TestBed.inject(UserService);
    const mockUsers: User[] = [
      {
        username: 'testUser',
        email: 'testuser@example.com',
        password: 'testpassword',
        displayName: 'Test User',
        bio: 'This is a test bio',
        createdAt: new Date(),
        updatedAt: new Date(),
      },
    ];

    spyOn(userService, 'getAllUsers').and.returnValue(of(mockUsers));
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'webapp'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('Twitter Clone');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('title')?.textContent).toContain(
      'Twitter Clone'
    );
  });
});
