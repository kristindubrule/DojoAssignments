import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CityWeatherSanjoseComponent } from './city-weather-sanjose.component';

describe('CityWeatherSanjoseComponent', () => {
  let component: CityWeatherSanjoseComponent;
  let fixture: ComponentFixture<CityWeatherSanjoseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CityWeatherSanjoseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CityWeatherSanjoseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
