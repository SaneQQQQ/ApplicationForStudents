import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {GroupService} from "../../../service/group.service";
import {Group} from "../../../interface/group";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteGroupComponent implements OnInit {

  hasRelations: boolean = false;

  constructor(private groupService: GroupService,
              public dialogRef: MatDialogRef<DeleteGroupComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Group) {
  }

  public ngOnInit(): void {
  }

  public onSubmit(): void {
    this.groupService.delete(this.data.id).subscribe({
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
