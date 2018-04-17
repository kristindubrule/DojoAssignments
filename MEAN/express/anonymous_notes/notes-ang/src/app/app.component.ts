import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  notes: any;

  constructor(private _httpService: HttpService) {}

  ngOnInit() {
    this.getNotes();
  }

  getNotes() {
    // this.notes = this._httpService.getNotes();
    const obs = this._httpService.getNotes();
    console.log('subscribing');
    obs.subscribe(data => {
      this.notes = data['notes'];
    });
  }
}
