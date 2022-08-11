import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {HttpErrorResponse} from "@angular/common/http";
import {StudentService} from "../../../service/student.service";
import {Mark} from "../../../interface/mark";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteMarkComponent implements OnInit {

  hasRelations: boolean = false;

  constructor(private studentService: StudentService,
              public dialogRef: MatDialogRef<DeleteMarkComponent>,
              @Inject(MAT_DIALOG_DATA) public data: { studentId: number, element: Mark }) {
  }

  public ngOnInit(): void {
  }

  public onSubmit(): void {
    this.studentService.deleteMark(this.data.studentId, this.data.element.subject.id).subscribe({
      next: () => {
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
