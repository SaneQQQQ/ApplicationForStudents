import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {uniqueValidator} from "../../../validation/unique.validator";
import {GroupService} from "../../../service/group.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddGroupComponent implements OnInit {

  addForm!: FormGroup;

  title = new FormControl('',
    [Validators.required, Validators.maxLength(5), Validators.pattern("(^[0-9]{3})([\\-][A-Z]$){0,1}"), uniqueValidator]);

  constructor(private groupService: GroupService, public dialogRef: MatDialogRef<AddGroupComponent>) {
  }

  public ngOnInit(): void {
    this.addForm = new FormGroup({
      id: new FormControl(),
      title: this.title
    })
  }

  public onSubmit(): void {
    this.groupService.create(this.addForm.value).subscribe({
      next: () => {
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
      return 'Max length is 5 characters';
    }
    if (this.title.hasError('pattern')) {
      return 'Title must be like 333 or 333-A';
    }
    if (this.title.hasError('notunique')) {
      return 'Group with this title already exist';
    }
    return '';
  }
}
