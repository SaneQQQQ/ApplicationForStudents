import {Component, OnInit, ViewChild} from '@angular/core';
import {GroupService} from 'src/app/service/group.service';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSort, Sort} from "@angular/material/sort";
import {MatDialog} from "@angular/material/dialog";
import {AddGroupComponent} from "./add/add.component";
import {UpdateGroupComponent} from "./update/update.component";
import {DeleteGroupComponent} from "./delete/delete.component";
import {Group} from "../../interface/group";

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {

  displayedColumns: string[] = ['title', 'countOfStudents', 'actions'];
  dataSource!: MatTableDataSource<Group>;
  groups!: Group[];

  pageNumber: number = 0;
  pageSize: number = 10;
  pageTotalItems: number = this.pageNumber * this.pageSize;
  pageSizeOptions: number[] = [10, 15, 20, 25];

  sortBy!: string;
  sortOrder!: string;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private groupService: GroupService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getGroups(this.pageNumber, this.pageSize, this.sortBy, this.sortOrder)
  }

  public getGroups(page: number, pageSize: number, sortBy: string, sortOrder: string): void {
    this.groupService.readAll(page, pageSize, sortBy, sortOrder)
      .subscribe(
        response => {
          const {content, totalElements, pageNumber} = response;
          this.pageTotalItems = totalElements;
          this.pageNumber = pageNumber;
          this.groups = content;
          this.dataSource = new MatTableDataSource<Group>(this.groups);
        }
      )
  }

  public onPageChange(event: PageEvent): void {
    if (this.pageSize != event.pageSize) {
      this.paginator.firstPage();
      this.pageNumber = 0;
      this.pageTotalItems = event.length;
      this.pageSize = event.pageSize;
      this.getGroups(this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
    } else {
      this.pageNumber = event.pageIndex;
      this.pageTotalItems = event.length;
      this.getGroups(this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
    }
  }

  public onSortChange(event: Sort): void {
    this.paginator.firstPage();
    this.pageNumber = 0;
    this.sortBy = event.active;
    this.sortOrder = event.direction;
    this.getGroups(this.pageNumber, this.pageSize, this.sortBy, this.sortOrder);
  }

  public openAddDialog(): void {
    this.dialog.open(AddGroupComponent).afterClosed().subscribe(() => {
      this.clearSort();
    });
  }

  public openUpdateDialog(element: Group): void {
    this.dialog.open(UpdateGroupComponent, {
      data: element
    }).afterClosed().subscribe(() => {
      this.clearSort();
    });
  }

  public openDeleteDialog(element: Group): void {
    this.dialog.open(DeleteGroupComponent, {
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
