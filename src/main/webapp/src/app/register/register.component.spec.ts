import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterComponent } from './register.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { UserService } from '../services/user.service';
import { of } from 'rxjs';
import { User } from '../models/user.model';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let userService: UserService;
  let userServiceSpy: jasmine.Spy;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      imports: [HttpClientTestingModule, FormsModule],
      providers: [UserService],
    }).compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    userService = TestBed.get(UserService);

    const mockUser: User = {
      username: 'testUser',
      email: 'testuser@example.com',
      password: 'testpassword',
      displayName: 'Test User',
      bio: 'This is a test bio',
      createdAt: new Date(),
      updatedAt: new Date(),
    };

    userServiceSpy = spyOn(userService, 'register').and.returnValue(
      of(mockUser)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Register
  it('should call register on the service when register is called', () => {
    const user: User = {
      username: 'testUser',
      email: 'testuser@example.com',
      password: 'testpassword',
      displayName: 'Test User',
      bio: 'This is a test bio',
      createdAt: new Date(),
      updatedAt: new Date(),
    };

    component.user = user;
    component.register();
    expect(userServiceSpy).toHaveBeenCalled();
  });
});
