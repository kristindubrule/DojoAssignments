import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {
  apikey: string;

  constructor(private _http: HttpClient) { }

  getWeather(city: string) {
    return this._http.get('http://api.openweathermap.org/data/2.5/weather?units=imperial&q=' + city + '&APPID=' + this.apikey);
  }

  setAPIKey(apikey: string) {
    this.apikey = apikey;
  }
}
