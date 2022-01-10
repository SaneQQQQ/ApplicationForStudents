import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {HttpErrorResponse} from "@angular/common/http";
import {StudentService} from "../../../service/student.service";
import {Student} from "../../../interface/student";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteStudentComponent implements OnInit {

  hasRelations: boolean = false;

  constructor(private studentService: StudentService,
              public dialogRef: MatDialogRef<DeleteStudentComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Student) {
  }

  public ngOnInit(): void {
  }

  public onSubmit(): void {
    this.studentService.delete(this.data.id).subscribe({
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
