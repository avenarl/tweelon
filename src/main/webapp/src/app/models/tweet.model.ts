import { User } from './user.model';

export class Tweet {
  id: number;
  user: User;
  content: string;
  createdAt: Date;
  updatedAt: Date;

  constructor() {
    this.id = 0;
    this.user = {
      id: 1,
      username: '',
      password: '',
      email: '',
      displayName: '',
      bio: '',
      createdAt: new Date(),
      updatedAt: new Date(),
    };
    this.content = '';
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }
}
