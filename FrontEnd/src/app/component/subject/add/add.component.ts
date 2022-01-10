import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {SubjectService} from "../../../service/subject.service";
import {uniqueValidator} from "../../../validation/unique.validator";
import {Subject} from "../../../interface/subject";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddSubjectComponent implements OnInit {

  addForm!: FormGroup;

  title = new FormControl('',
    [Validators.required, Validators.maxLength(128), uniqueValidator]);

  constructor(private subjectService: SubjectService, public dialogRef: MatDialogRef<AddSubjectComponent>) {
  }

  public ngOnInit(): void {
    this.addForm = new FormGroup({
      id: new FormControl(),
      title: this.title
    })
  }

  public onSubmit(): void {
    this.subjectService.create(this.addForm.value).subscribe({
      next: (response: Subject) => {
        this.dialogRef.close();
      },
      error: (err: HttpErrorResponse) => {
        if (err.status == 409) {
          this.title.setErrors({'notunique': true})
        }
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
      return 'Subject with this title already exist';
    }
    return '';
  }
}
