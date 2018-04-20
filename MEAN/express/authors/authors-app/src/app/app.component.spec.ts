import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { RouterLinkWithHref } from '@angular/router';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ViewComponent } from './view/view.component';
import { AddComponent } from './add/add.component';
import { AddQuoteComponent } from './add-quote/add-quote.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { path: 'home', component: HomeComponent },
          { path: 'view/:id', component: ViewComponent },
          { path: '', component: HomeComponent },
          { path: 'add', component: AddComponent },
          { path: 'add/:id', component: AddComponent },
          { path: 'add-quote/:id', component: AddQuoteComponent }
        ]),
        FormsModule,
        HttpClientModule
      ],
      providers: [
        HttpService,
        HttpClient
      ],
      declarations: [
        AppComponent,
        HomeComponent,
        ViewComponent,
        AddComponent,
        AddQuoteComponent
      ],
    }).compileComponents();
    const fixture = TestBed.createComponent(AppComponent);
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  // it(`should have as title 'app'`, async(() => {
  //   const fixture = TestBed.createComponent(AppComponent);
  //   const app = fixture.debugElement.componentInstance;
  //   expect(app.title).toEqual('app');
  // }));
  // it('should render title in a h1 tag', async(() => {
  //   const fixture = TestBed.createComponent(AppComponent);
  //   fixture.detectChanges();
  //   const compiled = fixture.debugElement.nativeElement;
  //   expect(compiled.querySelector('h1').textContent).toContain('Welcome to app!');
  // }));
});
