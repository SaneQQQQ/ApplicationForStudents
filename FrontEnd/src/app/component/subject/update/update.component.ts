import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SubjectService} from "../../../service/subject.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Subject} from "../../../interface/subject";
import {uniqueValidator} from "../../../validation/unique.validator";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateSubjectComponent implements OnInit {

  editForm!: FormGroup;

  title = new FormControl(this.data.title,
    [Validators.required, Validators.maxLength(128), uniqueValidator]);

  constructor(private subjectService: SubjectService,
              public dialogRef: MatDialogRef<UpdateSubjectComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Subject) {
  }

  public ngOnInit(): void {
    this.editForm = new FormGroup({
      id: new FormControl(this.data.id),
      title: this.title
    })
  }

  public onSubmit(): void {
    this.subjectService.update(this.editForm.value).subscribe({
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
