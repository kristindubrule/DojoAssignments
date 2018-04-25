import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  author: any;
  message: any;
  
  constructor(
    private _httpService: HttpService,
    private _route: ActivatedRoute,
    private _router: Router) { }

  getAuthor(authorId: string) {
    const obs = this._httpService.getAuthor(authorId);
    obs.subscribe(data => {
      if (data['error']) {
        this.message = [data['error']];
        console.log(data['error']);
      } else {
        this.author = data['author'];
        console.log(this.author);
      }
    });
  }

  ngOnInit() {
    this._route.params.subscribe((params: Params) => {
      this.getAuthor(params['id']);
    });
  }

  deleteQuote(quoteId: string) {
    const obs = this._httpService.deleteQuote(this.author._id, quoteId);
    obs.subscribe(data => {
      if (data['error']) {
        this.message = [data['error']];
        console.log(data['error']);
      } else {
        this.getAuthor(this.author._id);
      }
    });
  }

  vote(quoteId: string, change: number) {
    const obs = this._httpService.vote(this.author._id, quoteId, change);
    obs.subscribe(data => {
      if (data['error']) {
        this.message = [data['error']];
        console.log(data['error']);
      } else {
        this.getAuthor(this.author._id);
      }
    });
  }
}
