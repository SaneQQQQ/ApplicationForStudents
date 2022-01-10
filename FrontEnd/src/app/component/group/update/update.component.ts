import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {uniqueValidator} from "../../../validation/unique.validator";
import {GroupService} from "../../../service/group.service";
import {Group} from "../../../interface/group";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateGroupComponent implements OnInit {

  editForm!: FormGroup;

  title = new FormControl(this.data.title,
    [Validators.required, Validators.maxLength(5), Validators.pattern("(^[0-9]{3})([\\-][A-Z]$){0,1}"), uniqueValidator]);

  constructor(private groupService: GroupService,
              public dialogRef: MatDialogRef<UpdateGroupComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Group) {
  }

  public ngOnInit(): void {
    this.editForm = new FormGroup({
      id: new FormControl(this.data.id),
      title: this.title
    })
  }

  public onSubmit(): void {
    this.groupService.update(this.editForm.value).subscribe({
      next: (response: Group) => {
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
