import {Component, OnInit, ViewChild} from '@angular/core';
import {SubjectService} from "../../service/subject.service";
import {Subject} from "../../interface/subject";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort, Sort} from "@angular/material/sort";
import {MatPaginator, PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {

  displayedColumns: string[] = ['title', 'actions'];
  dataSource!: MatTableDataSource<Subject>;

  page: number = 0;
  pageSize: number = 10;
  pageTotalItems: number = this.page * this.pageSize;
  pageSizeOptions: number[] = [10, 15, 20, 25];

  sortBy!: string;
  sortOrder!: string;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private subjectService: SubjectService) {
  }

  ngOnInit(): void {
    this.getSubjects(this.page, this.pageSize, this.sortBy, this.sortOrder)
  }

  public getSubjects(page: number, size: number, sortBy: string, sortOrder: string): void {
    this.subjectService.readAll(page, size, sortBy, sortOrder)
      .subscribe(
        response => {
          const {content, totalElements, number} = response;
          this.pageTotalItems = totalElements;
          this.page = number;
          this.dataSource = new MatTableDataSource<Subject>(content);
        }
      )
  }

  public onPageChange(event: PageEvent): void {
    if (this.pageSize != event.pageSize) {
      this.paginator.firstPage();
      this.page = 0;
      this.pageTotalItems = event.length;
      this.pageSize = event.pageSize;
      this.getSubjects(this.page, this.pageSize, this.sortBy, this.sortOrder);

    } else {
      this.page = event.pageIndex;
      this.pageTotalItems = event.length;
      this.getSubjects(this.page, this.pageSize, this.sortBy, this.sortOrder);
    }
  }

  public onSortChange(event: Sort): void {
    this.paginator.firstPage();
    this.page = 0;
    this.sortBy = event.active;
    this.sortOrder = event.direction;
    this.getSubjects(this.page, this.pageSize, this.sortBy, this.sortOrder);
  }
}
