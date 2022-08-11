import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSort, Sort} from "@angular/material/sort";
import {MatDialog} from "@angular/material/dialog";
import {Student} from "../../interface/student";
import {StudentService} from "../../service/student.service";
import {DeleteStudentComponent} from "./delete/delete.component";
import {AddEditStudentComponent} from "./add-edit/add-edit.component";

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'group.title', 'averageRank', 'actions'];
  dataSource!: MatTableDataSource<Student>;
  students!: Student[];

  pageNumber: number = 0;
  pageSize: number = 10;
  pageTotalItems: number = this.pageNumber * this.pageSize;
  pageSizeOptions: number[] = [10, 15, 20, 25];

  sortBy!: string;
  sortOrder!: string;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private studentService: StudentService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getStudents(this.pageNumber, this.pageSize, this.sortBy, this.sortOrder)
  }

  public getStudents(page: number, pageSize: number, sortBy: string, sortOrder: string): void {
    this.studentService.readAll(page, pageSize, sortBy, sortOrder)
      .subscribe(
        response => {
          const {content, totalElements, pageNumber} = response;
          this.pageTotalItems = totalElements;
          this.pageNumber = pageNumber;
          this.students = content;
          this.dataSource = new MatTableDataSource<Student>(this.students);
        }
      )
  }

  public onPageChange(event: PageEvent): void {
    if (this.pageSize != event.pageSize) {
      this.paginator.firstPage();
      this.pageNumber = 0;
      this.pageTotalItems = event.length;
      this.pageSize = event.pageSize;
      this.getStudents(this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
    } else {
      this.pageNumber = event.pageIndex;
      this.pageTotalItems = event.length;
      this.getStudents(this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
    }
  }

  public onSortChange(event: Sort): void {
    this.paginator.firstPage();
    this.pageNumber = 0;
    this.sortBy = event.active;
    this.sortOrder = event.direction;
    this.getStudents(this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
  }

  public openAddEditDialog(element?: Student): void {
    this.dialog.open(AddEditStudentComponent, {
      data: element
    }).afterClosed().subscribe(() => {
      this.clearSort();
    });
  }

  public openDeleteDialog(element: Student): void {
    this.dialog.open(DeleteStudentComponent, {
      data: element
    }).afterClosed().subscribe(() => {
      this.clearSort();
    });
  }

  private clearSort(): void {
    this.sort.sort({id: '', start: 'asc', disableClear: false});
    this.sortBy = '';
    this.sortOrder = '';
  }
}
