import {Component, Inject, OnInit} from '@angular/core';
import {SubjectService} from "../../../service/subject.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Subject} from "../../../interface/subject";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteSubjectComponent implements OnInit {
  hasRelations: boolean = false;

  constructor(private subjectService: SubjectService,
              public dialogRef: MatDialogRef<DeleteSubjectComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Subject) {
  }

  public ngOnInit(): void {
  }

  public onSubmit(): void {
    this.subjectService.delete(this.data.id).subscribe({
      next: (response: string) => {
        this.dialogRef.close();
      },
      error: (err: HttpErrorResponse) => {
        if (err.status == 409) {
          this.hasRelations = true;
        }
      }
    });
  }
}
