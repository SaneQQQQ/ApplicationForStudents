import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {SubjectService} from "../../../service/subject.service";

export function uniqueValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    return control.value.hasError('notunique') ? {notUnique: true} : null;
  }
}

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  addForm!: FormGroup;

  title = new FormControl('',
    [Validators.required, Validators.maxLength(128), uniqueValidator]);

  constructor(private subjectService: SubjectService, public dialogRef: MatDialogRef<AddComponent>) {
  }

  public ngOnInit(): void {
    this.addForm = new FormGroup({
      id: new FormControl(),
      title: this.title
    })
  }

  public onSubmit(): void {
    this.subjectService.create(this.addForm.value).subscribe({
      next: (response: any) => {
        this.dialogRef.close();
      },
      error: (err: any) => {
        this.title.setErrors({'notunique': true}
        )
      }
    });
  }

  public getErrorMessage(): string {
    if (this.title.hasError('required')) {
      return 'Title can not be empty';
    }
    if (this.title.hasError('maxlength')) {
      return 'Max length is 128 characters';
    }
    if (this.title.hasError('notunique')) {
      return 'This title already exist';
    }
    return '';
  }
}
