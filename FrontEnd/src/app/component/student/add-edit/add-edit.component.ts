import {Component, Inject, OnInit} from '@angular/core';
import {GroupService} from "../../../service/group.service";
import {StudentService} from "../../../service/student.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Group} from "../../../interface/group";
import {Student} from "../../../interface/student";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {uniqueValidator} from "../../../validation/unique.validator";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditStudentComponent implements OnInit {

  addForm!: FormGroup;

  options!: Group[];

  firstName = new FormControl(this.data == null ? '' : this.data.firstName,
    [Validators.required, Validators.maxLength(128)]);
  lastName = new FormControl(this.data == null ? '' : this.data.lastName,
    [Validators.required, Validators.maxLength(128)]);
  email = new FormControl(this.data == null ? '' : this.data.email,
    [Validators.required, Validators.maxLength(128), Validators.email, uniqueValidator]);
  group = new FormControl(this.data == null ? null : this.data.group, Validators.required);

  constructor(private groupService: GroupService,
              private studentService: StudentService,
              public dialogRef: MatDialogRef<AddEditStudentComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Student) {
  }

  ngOnInit(): void {
    this.readGroups();
    this.addForm = new FormGroup({
      id: new FormControl(this.data == null ? null : this.data.id),
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      group: this.group
    });
  }

  public readGroups(): void {
    this.groupService.readAll(0, 1000, '', '').subscribe(
      response => {
        this.options = response.content;
      }
    );
  }

  public onSubmit(): void {
    if (this.data == null) {
      this.studentService.create(this.addForm.value).subscribe({
        next: (response: Student) => {
          this.dialogRef.close();
        },
        error: (err: HttpErrorResponse) => {
          if (err.status == 409) {
            this.email.setErrors({'notunique': true})
          }
        }
      });
    } else {
      this.studentService.update(this.addForm.value).subscribe({
        next: (response: Student) => {
          this.dialogRef.close();
        },
        error: (err: HttpErrorResponse) => {
          if (err.status == 409) {
            this.email.setErrors({'notunique': true})
          }
        }
      });
    }
  }

  public compareWith(o1: Group, o2: Group): boolean {
    return o1.id == o2?.id;
  }

  public getEmailErrorMessage(): string {
    if (this.email.hasError('required')) {
      return 'Email can not be empty';
    }
    if (this.email.hasError('maxlength')) {
      return 'Max length is 128 characters';
    }
    if (this.email.hasError('email')) {
      return 'Please enter a valid email address';
    }
    if (this.email.hasError('notunique')) {
      return 'Student with this email already exist';
    }
    return '';
  }

  public getFirstNameErrorMessage(): string {
    if (this.firstName.hasError('required')) {
      return 'First name can not be empty';
    }
    if (this.firstName.hasError('maxlength')) {
      return 'Max length is 128 characters';
    }
    return '';
  }

  public getLastNameErrorMessage(): string {
    if (this.lastName.hasError('required')) {
      return 'Last Name can not be empty';
    }
    if (this.lastName.hasError('maxlength')) {
      return 'Max length is 128 characters';
    }
    return '';
  }

  public getGroupErrorMessage(): string {
    if (this.group.hasError('required')) {
      return 'Please select group';
    }
    return '';
  }
}
