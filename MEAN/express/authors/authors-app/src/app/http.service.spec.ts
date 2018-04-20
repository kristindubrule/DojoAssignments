import { TestBed, inject } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { HttpService } from './http.service';
import { HttpClientModule } from '@angular/common/http';

describe('HttpService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HttpService,
      HttpClient],
      imports: [
        HttpClientModule
      ]
    });
  });

  it('should be created', inject([HttpService], (service: HttpService) => {
    expect(service).toBeTruthy();
  }));
});
