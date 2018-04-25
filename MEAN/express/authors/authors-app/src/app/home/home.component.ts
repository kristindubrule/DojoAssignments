import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  authors: any;
  message: string;

  constructor(private _httpService: HttpService, private _route: ActivatedRoute,
    private _router: Router) { }

  ngOnInit() {
    this.authors = [];
    this.getAuthors();
  }

  getAuthors() {
    const obs = this._httpService.getAuthors();
    obs.subscribe(data => {
      if (data['errors']) {
        this.message = data['errors'];
      } else {
        this.authors = data['authors'];
      }
    });
  }

  deleteAuthor(authorId: string) {
    console.log('Deleting ' + authorId);
    const obs = this._httpService.deleteAuthor(authorId);
    obs.subscribe(data => {
      if (data['error']) {
        this.message = data['error'];
      } else {
        this.getAuthors();
      }
    });
  }

}
