import { CityWeatherChicagoComponent } from './city-weather-chicago/city-weather-chicago.component';
import { CityWeatherSanjoseComponent } from './city-weather-sanjose/city-weather-sanjose.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'weather/:city', component: CityWeatherChicagoComponent },
  { path: 'sanjose', component: CityWeatherSanjoseComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
