import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  author: {name: string, id: string};
  message: any;
  editFlag = false;
  instructions: string;

  constructor(private _httpService: HttpService, private _route: ActivatedRoute,
    private _router: Router) {
    this.author = {name: '', id: null};
  }

  getAuthor(authorId: string): void {
    const obs = this._httpService.getAuthor(authorId);
        obs
          .subscribe(data => {
            if (data['error']) {
              this.message = data['error'];
            } else {
              this.author = data['author'];
              this.editFlag = true;
            }
            if (this.author == null) {
              this.message = ['No author found. Would you like to add it?'];
              this.author = {name: '', id: ''};
              this.editFlag = false;
            } else {
              this.instructions = 'Edit an author';
            }
          },
          error => {
            this.message = ['There was an error with this request. Would you like to add this author?'];
          });
  }

  ngOnInit() {
    this._route.params.subscribe((params: Params) => {
      if (params['id']) {
        this.getAuthor(params['id']);
      } else {
        this.instructions = 'Add an author';
      }
    });
  }

  updateAuthor() {
    let obs;
    if (this.editFlag) {
      obs = this._httpService.updateAuthor(this.author);
    } else {
      obs = this._httpService.addAuthor(this.author);
    }
    obs.subscribe(data => {
      if (data['error']) {
        this.message = [];
        // tslint:disable-next-line:forin
        for (let message_text in data['error']) {
          this.message.push(data['error'][message_text].message);
        }
        console.log(this.message);
      } else {
        this.author = {name: '', id: null};
        this.message = '';
        this.editFlag = false;
        this._router.navigate(['/']);
      }
    });
  }
}
