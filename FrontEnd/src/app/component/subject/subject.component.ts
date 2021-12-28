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
  pageSize: number = 5;
  pageTotalItems: number = 0;
  pageSizeOptions: number[] = [5, 10, 25];

  sortBy: string = 'title';
  sortOrder: string = 'asc';

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private subjectService: SubjectService) {
  }

  ngOnInit(): void {
    this.getSubjects(this.page, this.pageSize, this.sortBy, this.sortOrder)
  }

  public getSubjects(page: number, size: number, sortBy: string, sortOrder: string) {
    this.subjectService.readAll(page, size, sortBy, sortOrder)
      .subscribe(
        response => {
          console.log('response', response);
          const {content, totalElements, pageNumber} = response;
          this.pageTotalItems = totalElements;
          this.page = pageNumber;
          this.dataSource = new MatTableDataSource<Subject>(content);
        }
      )
  }

  public onPageChange(event: PageEvent) {
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

  public onSortChange(event: Sort) {
    this.paginator.firstPage();
    this.page = 0;
    this.sortBy = event.active;
    this.sortOrder = event.direction;
    this.getSubjects(this.page, this.pageSize, this.sortBy, this.sortOrder);
  }
}
