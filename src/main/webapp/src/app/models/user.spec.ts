import { User } from './user.model';

describe('User', () => {
  it('should create an instance', () => {
    const user: User = {
      username: 'testUser',
      password: 'passwordtest',
      email: 'test@example.com',
      displayName: 'Test User',
      bio: 'This is a test bio',
      createdAt: new Date(),
      updatedAt: new Date(),
    };
    expect(user).toBeTruthy();
  });
});
