import { Employee } from './employee';

import { TestBed } from '@angular/core/testing';
import { ApolloTestingModule } from 'apollo-angular/testing';

beforeEach(() => {
  TestBed.configureTestingModule({
    imports: [
      ApolloTestingModule
    ]
  });
});

describe('Employee', () => {
  it('should create an instance', () => {
    expect(new Employee("13", "andrew", (new Date()).toDateString(), "London", 1000)).toBeTruthy();
  });
});
