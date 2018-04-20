import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AddQuoteComponent } from './add-quote.component';
import { RouterTestingModule } from '@angular/router/testing';
import { RouterLinkWithHref } from '@angular/router';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import { HttpService } from '../http.service';
import { HttpClient } from '@angular/common/http';

describe('AddQuoteComponent', () => {
  let component: AddQuoteComponent;
  let fixture: ComponentFixture<AddQuoteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          {path: 'add-quote/:id', component: AddQuoteComponent }
        ]),
        FormsModule,
        HttpClientModule
      ],
      providers: [
        HttpService,
        HttpClient
      ],
      declarations: [ AddQuoteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddQuoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', async(() => {
    expect(component).toBeTruthy();
  }));
});
