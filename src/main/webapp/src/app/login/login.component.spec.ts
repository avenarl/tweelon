import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of, throwError } from 'rxjs';
import { LoginComponent } from './login.component';
import { UserService } from '../services/user.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let userService: UserService;

  const userServiceStub = {
    login: () => of({}),
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [HttpClientTestingModule],
      providers: [{ provide: UserService, useValue: userServiceStub }],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call login when logging in', () => {
    const spy = spyOn(userService, 'login').and.callThrough();
    component.login();
    expect(spy).toHaveBeenCalled();
  });

  it('should handle login error', () => {
    const spy = spyOn(console, 'log');
    spyOn(userService, 'login').and.returnValue(throwError('Error'));
    component.login();
    expect(spy).toHaveBeenCalledWith('Error logging in', 'Error');
  });
});
