import { Component, OnInit } from '@angular/core';
import { Group } from 'src/app/interface/group';
import { GroupService } from 'src/app/service/group.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {
  public groups: Group[] = [];

  constructor(private groupService: GroupService) {}

  ngOnInit(): void {
      this.getGroups();
  }

  public getGroups(): void {
    this.groupService.readAll().subscribe(
      (response: Group[]) => {
        this.groups = response
      }
    );
  }
}
