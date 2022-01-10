import {Component, OnInit, ViewChild} from '@angular/core';
import {StudentService} from "../../service/student.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSort, Sort} from "@angular/material/sort";
import {Mark} from "../../interface/mark";
import {MatDialog} from "@angular/material/dialog";
import {ActivatedRoute} from "@angular/router";
import {DeleteMarkComponent} from "./delete/delete.component";
import {AddEditMarkComponent} from "./add-edit/add-edit.component";

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  studentId!: number;
  studentFirstName!: string;
  studentLastName!: string;
  studentEmail!: string;
  studentGroupId!: number;
  studentGroupTitle!: string;
  studentAverageRank!: number;

  displayedColumns: string[] = ['subject', 'mark', 'actions'];
  dataSource!: MatTableDataSource<Mark>;
  marks!: Mark[];

  page: number = 0;
  pageSize: number = 10;
  pageTotalItems: number = this.page * this.pageSize;
  pageSizeOptions: number[] = [10, 15, 20, 25];

  sortBy!: string;
  sortOrder!: string;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private studentService: StudentService, public dialog: MatDialog, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap
      .subscribe(params => {
        this.studentId = Number(params.get('id'));
        this.getStudent(this.studentId);
        this.getMarksByStudentId(this.studentId, this.page, this.pageSize, this.sortBy, this.sortOrder);
      })
  }

  public getStudent(id: number): void {
    this.studentService.read(id)
      .subscribe(
        response => {
          this.studentFirstName = response.firstName,
            this.studentLastName = response.lastName,
            this.studentEmail = response.email,
            this.studentGroupId = response.group.id,
            this.studentGroupTitle = response.group.title,
            this.studentAverageRank = response.averageRank
        }
      )
  }

  public getMarksByStudentId(studentId: number, page: number, pageSize: number, sortBy: string, sortOrder: string): void {
    this.studentService.readAllMarksByStudent(studentId, page, pageSize, sortBy, sortOrder)
      .subscribe(
        response => {
          const {content, totalElements, number} = response;
          this.pageTotalItems = totalElements;
          this.page = number;
          this.marks = content;
          this.dataSource = new MatTableDataSource<Mark>(this.marks);
        }
      )
  }

  public onPageChange(event: PageEvent): void {
    if (this.pageSize != event.pageSize) {
      this.paginator.firstPage();
      this.page = 0;
      this.pageTotalItems = event.length;
      this.pageSize = event.pageSize;
      this.getMarksByStudentId(this.studentId, this.page, this.pageSize, this.sortBy, this.sortOrder);
    } else {
      this.page = event.pageIndex;
      this.pageTotalItems = event.length;
      this.getMarksByStudentId(this.studentId, this.page, this.pageSize, this.sortBy, this.sortOrder);
    }
  }

  public onSortChange(event: Sort): void {
    this.paginator.firstPage();
    this.page = 0;
    this.sortBy = event.active;
    this.sortOrder = event.direction;
    this.getMarksByStudentId(this.studentId, this.page, this.pageSize, this.sortBy, this.sortOrder);
  }

  private clearSort(): void {
    this.sort.sort({id: '', start: 'asc', disableClear: false});
    this.sortBy = '';
    this.sortOrder = '';
  }

  public openAddEditDialog(element?: Mark): void {
    const dialogRef = this.dialog.open(AddEditMarkComponent, {
      data: {
        student: {id: this.studentId},
        firstName: this.studentFirstName,
        lastName: this.studentLastName,
        element: element
      }
    });
    dialogRef.afterClosed().subscribe(response => {
      this.clearSort();
      this.getStudent(this.studentId);
    });
  }

  public openDeleteDialog(element: Mark): void {
    const dialogRef = this.dialog.open(DeleteMarkComponent, {
      data: {studentId: this.studentId, element: element}
    });
    dialogRef.afterClosed().subscribe(response => {
      this.clearSort();
      this.getStudent(this.studentId);
    });
  }
}
