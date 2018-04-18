import { Component, OnInit } from '@angular/core';
import { HttpBackend } from '@angular/common/http';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  pets = [];
  message = [];

  constructor(private _httpService: HttpService) { }

  ngOnInit() {
    this.getPets();
  }

  getPets() {
    const obs = this._httpService.getPets();
    obs.subscribe(data => {
      if (data['errors']) {
        // tslint:disable-next-line:forin
        for (let message_text in data['error']) {
          this.message.push(data['error'][message_text].message);
        }
      } else {
        this.pets = data['pets'];
      }
    });
  }

}
