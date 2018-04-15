import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  apikey: string;

  constructor(
    private _httpService: HttpService,
    private _route: ActivatedRoute,
    private _router: Router)
    {}

  ngOnInit() {
    this._route.params.subscribe((params: Params) => console.log(params['city']));
  }
  setAPIKey() {
    this._httpService.setAPIKey(this.apikey);
    console.log('set API key to ' + this.apikey);
  }
}
