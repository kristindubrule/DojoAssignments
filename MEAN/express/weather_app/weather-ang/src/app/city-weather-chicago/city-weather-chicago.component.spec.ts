import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CityWeatherChicagoComponent } from './city-weather-chicago.component';

describe('CityWeatherChicagoComponent', () => {
  let component: CityWeatherChicagoComponent;
  let fixture: ComponentFixture<CityWeatherChicagoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CityWeatherChicagoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CityWeatherChicagoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
