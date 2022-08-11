import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSort, Sort} from "@angular/material/sort";
import {GroupService} from "../../service/group.service";
import {MatTableDataSource} from "@angular/material/table";
import {Student} from "../../interface/student";
import {StudentService} from "../../service/student.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  groupId!: number;
  groupTitle!: string;

  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'averageRank'];
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

  constructor(private groupService: GroupService,
              private studentService: StudentService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap
      .subscribe(params => {
        this.groupId = Number(params.get('id'));
        this.getGroup(this.groupId);
        this.getStudentsByGroupId(this.groupId, this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
      })
  }

  public getGroup(id: number): void {
    this.groupService.read(id)
      .subscribe(
        response => this.groupTitle = response.title
      )
  }

  public getStudentsByGroupId(groupId: number, page: number, pageSize: number, sortBy: string, order: string): void {
    this.studentService.readAllByGroupId(groupId, page, pageSize, sortBy, order)
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
      this.getStudentsByGroupId(this.groupId, this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
    } else {
      this.pageNumber = event.pageIndex;
      this.pageTotalItems = event.length;
      this.getStudentsByGroupId(this.groupId, this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
    }
  }

  public onSortChange(event: Sort): void {
    this.paginator.firstPage();
    this.pageNumber = 0;
    this.sortBy = event.active;
    this.sortOrder = event.direction;
    this.getStudentsByGroupId(this.groupId, this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
  }
}
