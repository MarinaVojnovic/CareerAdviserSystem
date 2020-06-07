import { TestBed } from '@angular/core/testing';

import { TraitsResultService } from './traits-result.service';

describe('TraitsResultService', () => {
  let service: TraitsResultService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TraitsResultService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
