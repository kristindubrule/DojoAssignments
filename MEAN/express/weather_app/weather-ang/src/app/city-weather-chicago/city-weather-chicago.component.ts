import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-city-weather-chicago',
  templateUrl: './city-weather-chicago.component.html',
  styleUrls: ['./city-weather-chicago.component.css']
})
export class CityWeatherChicagoComponent implements OnInit {
  weather: {};
  city: string;

  constructor(private _httpService: HttpService, private _route: ActivatedRoute,
    private _router: Router) {}

  ngOnInit() {
    this._route.params.subscribe((params: Params) => {
      this.city = params['city'];
      console.log(this.city);
     
      const obs = this._httpService.getWeather(this.city);
      obs.subscribe(data => {
        this.weather = { humidity: data['main']['humidity'],
                          temp_avg: data['main']['temp'],
                          temp_min: data['main']['temp'],
                          temp_max: data['main']['temp_max'],
                          status: data['weather'][0]['description'] };
        console.log(this.weather);
      });
    });
  }
}
