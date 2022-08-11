import {Component, Inject, OnInit} from '@angular/core';
import {StudentService} from "../../../service/student.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subject} from "../../../interface/subject";
import {Mark} from "../../../interface/mark";
import {SubjectService} from "../../../service/subject.service";
import {noElementsValidator} from "../../../validation/noelements.validator";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditMarkComponent implements OnInit {

  addForm!: FormGroup;

  optionsToDelete!: Subject[];
  options!: Subject[];

  student = new FormControl(this.data.student)
  subject = new FormControl(this.data.element == null ? null : this.data.element.subject, [Validators.required, noElementsValidator]);
  mark = new FormControl(this.data.element == null ? null : this.data.element.mark, [Validators.required, Validators.pattern("[0-9]{1,3}$"), Validators.max(100), Validators.min(0)])

  constructor(private subjectService: SubjectService,
              private studentService: StudentService,
              public dialogRef: MatDialogRef<AddEditMarkComponent>,
              @Inject(MAT_DIALOG_DATA) public data: { student: { id: number }, firstName: string, lastName: string, element: Mark }) {
  }

  ngOnInit(): void {
    this.addForm = new FormGroup({
      student: this.student,
      subject: this.subject,
      mark: this.mark
    });
    if (this.data.element == null) {
      this.readSubjects(this.data.student.id);
    }
  }

  public readSubjects(id: number): void {
    this.studentService.readAllMarksByStudent(id, 0, 1000, '', '')
      .pipe(switchMap(response => {
        this.optionsToDelete = response.content.map(value => value.subject);
        return this.subjectService.readAll(0, 1000, '', '');
      })).subscribe(
      response => {
        this.options = response.content.filter(value => !this.optionsToDelete.find(remove => remove.id == value.id))
        if (this.options.length == 0) {
          this.subject.setErrors({'noelements': true})
        } else {
          this.subject.setErrors({'noelements': false})
        }
      });
  }

  public onSubmit(): void {
    if (this.data.element == null) {
      this.studentService.createMark(this.addForm.value).subscribe({
        next: () => {
          this.dialogRef.close();
        }
      });
    } else {
      this.studentService.updateMark(this.addForm.value).subscribe({
        next: () => {
          this.dialogRef.close();
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
    if (this.subject.hasError('noelements')) {
      return 'No options, student already has marks in all subjects';
    }
    return '';
  }

  public getMarkErrorMessage(): string {
    if (this.mark.hasError('required')) {
      return 'Mark can not be empty';
    }
    if (this.mark.hasError('pattern') || this.mark.hasError('max') || this.mark.hasError('min')) {
      return 'Mark should be integer between 0 and 100';
    }
    return '';
  }

}
