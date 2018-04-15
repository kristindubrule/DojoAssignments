import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpService } from './http.service';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { CityWeatherSanjoseComponent } from './city-weather-sanjose/city-weather-sanjose.component';
import { CityWeatherChicagoComponent } from './city-weather-chicago/city-weather-chicago.component';


@NgModule({
  declarations: [
    AppComponent,
    CityWeatherSanjoseComponent,
    CityWeatherChicagoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    HttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
