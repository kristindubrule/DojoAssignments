import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AddComponent } from './add.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { RouterLinkWithHref } from '@angular/router';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import { HttpService } from '../http.service';
import { HttpClient } from '@angular/common/http';

describe('AddComponent', () => {
  let component: AddComponent;
  let fixture: ComponentFixture<AddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { path: 'add', component: AddComponent },
          { path: 'add/:id', component: AddComponent }
        ]),
        FormsModule,
        HttpClientModule
      ],
      providers: [
        HttpService,
        HttpClient
      ],
      declarations: [ AddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
