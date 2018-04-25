import { TestBed, inject } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { HttpService } from './http.service';
import { HttpClientModule } from '@angular/common/http';

describe('HttpService', () => {
  let service: HttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HttpService,
      HttpClient],
      imports: [
        HttpClientModule
      ]
    });
    service = TestBed.get(HttpService);
  });

  test('should exist', () => {
    expect(service).toBeDefined();
  });
});
