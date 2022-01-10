import {Component, Inject, OnInit} from '@angular/core';
import {StudentService} from "../../../service/student.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subject} from "../../../interface/subject";
import {Mark} from "../../../interface/mark";
import {SubjectService} from "../../../service/subject.service";
import {uniqueValidator} from "../../../validation/unique.validator";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditMarkComponent implements OnInit {

  addForm!: FormGroup;

  options!: Subject[];

  student = new FormControl(this.data.student)
  subject = new FormControl(this.data.element == null ? null : this.data.element.subject, [Validators.required, uniqueValidator]);
  mark = new FormControl(this.data.element == null ? null : this.data.element.mark, [Validators.required, Validators.pattern("[0-9]{1,3}$"), Validators.max(100), Validators.min(0)])

  constructor(private subjectService: SubjectService,
              private studentService: StudentService,
              public dialogRef: MatDialogRef<AddEditMarkComponent>,
              @Inject(MAT_DIALOG_DATA) public data: { student: { id: number }, firstName: string, lastName: string, element: Mark }) {
  }

  ngOnInit(): void {
    this.readGroups();
    this.addForm = new FormGroup({
      student: this.student,
      subject: this.subject,
      mark: this.mark
    });
  }

  public readGroups(): void {
    this.subjectService.readAll(0, 1000, '', '').subscribe(
      response => {
        this.options = response.content;
      }
    );
  }

  public onSubmit(): void {
    if (this.data.element == null) {
      this.studentService.createMark(this.addForm.value).subscribe({
        next: (response: Mark) => {
          this.dialogRef.close();
        },
        error: (err: HttpErrorResponse) => {
          if (err.status == 409) {
            this.subject.setErrors({'notunique': true})
          }
        }
      });
    } else {
      this.studentService.updateMark(this.addForm.value).subscribe({
        next: (response: Mark) => {
          this.dialogRef.close();
        },
        error: (err: HttpErrorResponse) => {
          if (err.status == 409) {
            this.subject.setErrors({'notunique': true})
          }
        }
      });
    }
  }

  public compareWith(o1: Subject, o2: Subject): boolean {
    return o1.id == o2?.id;
  }

  public getSubjectErrorMessage(): string {
    if (this.subject.hasError('required')) {
      return 'Subject can not be empty';
    }
    if (this.subject.hasError('notunique')) {
      return 'Mark with this subject already exist';
    }
    return '';
  }

  public getMarkErrorMessage(): string {
    if (this.mark.hasError('required')) {
      return 'Mark can not be empty';
    }
    if (this.mark.hasError('pattern')) {
      return 'Mark should be integer between 0 and 100';
    }
    if (this.mark.hasError('min')) {
      return 'Mark min size 0';
    }
    if (this.mark.hasError('max')) {
      return 'Mark max size 100';
    }
    return '';
  }

}
