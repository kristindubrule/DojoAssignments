import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {

  constructor(private _http: HttpClient) {
    this.getTasks();

    this.getTask('5acd1cb076bf8f597ebeb632');
  }

  getTasks() {
    const tempObservable = this._http.get('/tasks');

    tempObservable.subscribe(data => console.log('Get our tasks!', data));
  }

  getTask(taskId: string) {
    const tempObservable = this._http.get('/tasks/' + taskId);

    tempObservable.subscribe(data => console.log('Got task!', data));
  }

}
