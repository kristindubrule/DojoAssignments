import { Component,
  Input,
  ElementRef,
  ViewChild,
  Renderer,
  forwardRef,
  OnInit,
  SimpleChange,
  SimpleChanges,
  Output,
  EventEmitter } from '@angular/core';
  import {ControlValueAccessor, NG_VALUE_ACCESSOR} from '@angular/forms';
  import { BehaviorSubject, Observable, Subscription } from "rxjs";
  import { Subject } from 'rxjs/Subject';
  import 'rxjs/add/operator/debounceTime';
  import 'rxjs/add/operator/distinctUntilChanged';

  const INLINE_EDIT_CONTROL_VALUE_ACCESSOR = {
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => EditComponent),
    multi: true
  };

@Component({
  selector: 'app-list-edit',
  templateUrl: './edit.component.html',
  providers: [INLINE_EDIT_CONTROL_VALUE_ACCESSOR],
  styleUrls: ['./edit.component.css']
})

export class EditComponent implements ControlValueAccessor, OnInit {
  @ViewChild('inlineEditControl') inlineEditControl; // input DOM element
  @Input() label: string = '';  // Label value for input element
  @Input() type; // The type of input element
  @Input() required: boolean = false; // Is input requried?
  @Input() disabled: boolean = false; // Is input disabled?
  @Input() curValue: any;
  @Input() eid: string;
  @Input() editfield: string;

  @Output() aTaskEventEmitter = new EventEmitter();

  private _value: string = ''; // Private variable for input value
  private preValue: string = ''; // The value before clicking to edit
  public editing: boolean = false; // Is Component in edit mode?
  public onChange: any = Function.prototype; // Trascend the onChange event
  public onTouched: any = Function.prototype; // Trascend the onTouch event
  curValueChanged: Subject<any> = new Subject<any>();
  sub: Subscription;

  //public $changeValues  = new BehaviorSubject<string>(this.ngOnChanges(changes));
  //public $changeValues : Rx.Subject = new Rx.BehaviorSubject();

  // const interval$ = Rx.Observable.interval(1000);
  // const subject = new Rx.Subject();
  // interval$.subscribe(subject);

  constructor(private _renderer: Renderer) { 
  }

  ngOnInit() {
    this._value = this.curValue;
  }

  // ngOnChanges(changes: SimpleChanges) {
  //   const value: SimpleChange = changes.curValue;
  //   if (value.previousValue != undefined) {
  //     this.aTaskEventEmitter.emit({id: this.id, editfield: this.editfield, value: value.currentValue});
  //   }
  // }

  changed(textstr: any) {
    this.curValueChanged.next(textstr);
  }

  // Control Value Accessors for ngModel
  get value(): any {
    return this._value;
  }

  set value(v: any) {
    if (v !== this._value) {
      this._value = v;
      this.onChange(v);
    }
  }

  // Required for ControlValueAccessor interface
  writeValue(value: any) {
    this._value = value;
  }

  // Required forControlValueAccessor interface
  public registerOnChange(fn: (_: any) => {}): void {
    this.onChange = fn;
  }

  // Required forControlValueAccessor interface
  public registerOnTouched(fn: () => {}): void {
    this.onTouched = fn;
  }

  // Do stuff when the input element loses focus
  onBlur($event: Event) {
    this.sub.unsubscribe();
    this.aTaskEventEmitter.emit({id: this.eid, editfield: this.editfield, value: this.curValue});
    this.editing = false;
  }

  // Start the editting process for the input element
  edit(value) {
    if (this.disabled) {
      return;
    }
    this.preValue = value;
    this.editing = true;
    this.curValueChanged = new Subject<any>();

    this.sub = this.curValueChanged.debounceTime(2500) // wait after the last event before emitting last event
      .distinctUntilChanged() // only emit if value is different from previous value
      .subscribe((curValue) => {
        this.curValue = curValue;
        this.aTaskEventEmitter.emit({id: this.eid, editfield: this.editfield, value: this.curValue});
    });

    // Focus on the input element just as the editing begins
    // setTimeout(_ => this._renderer.invokeElementMethod(this.inlineEditControl,
    //   'focus', []));
    setTimeout( () => this.inlineEditControl.nativeElement.focus());
  }

  toggleCheckbox() {
    this.curValue = (this.curValue == 'Not done') ? 'Done' : 'Not done';
    this.changed(this.curValue);
  }
}
