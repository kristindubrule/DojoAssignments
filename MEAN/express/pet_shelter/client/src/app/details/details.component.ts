import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  message = [];
  pet: any;
  liked: boolean;

  constructor(
    private _httpService: HttpService,
    private _route: ActivatedRoute,
    private _router: Router
  ) { }

  ngOnInit() {
    this._route.params.subscribe((params: Params) => {
      if (params['id']) {
        this.getPet(params['id']);
      }
    });
    this.liked = false;
  }

  getPet(petId: string): void {
    const obs = this._httpService.getPet(petId);
        obs
          .subscribe(data => {
            if (data['error']) {
              this.message = [data['error']];
            } else {
              this.pet = data['pet'];
            }
            if (this.pet == null) {
              this.message = ['No pet found. Would you like to add it?'];
            }
          },
          error => {
            this.message = ['There was an error with this request. Would you like to add this pet?'];
          });
  }

  likePet(): void {
    console.log('like pet');
    this.pet.likes += 1;
    const obs = this._httpService.updatePet(this.pet);

    console.log(this.pet);
    obs.subscribe(data => {
      if (data['error']) {
        // tslint:disable-next-line:forin
        for (let message_text in data['error']) {
          this.message.push(data['error'][message_text].message);
        }
      } else {
        this.liked = true;
      }
    });
  }

  adoptPet(): void {
      console.log('Deleting ' + this.pet._id);
      const obs = this._httpService.deletePet(this.pet._id);
      obs.subscribe(data => {
        if (data['error']) {
          this.message = [data['error']];
        } else {
          this._router.navigate(['/']);
        }
      });
  }

}
