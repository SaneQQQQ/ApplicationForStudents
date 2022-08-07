import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GroupListComponent} from './component/group/group-list.component';
import {SubjectComponent} from './component/subject/subject.component';
import {GroupService} from './service/group.service';
import {SubjectService} from './service/subject.service';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatTableModule} from '@angular/material/table';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatDialogModule} from "@angular/material/dialog";
import {AddSubjectComponent} from './component/subject/add/add.component';
import {MatInputModule} from "@angular/material/input";
import {UpdateSubjectComponent} from "./component/subject/update/update.component";
import {DeleteSubjectComponent} from './component/subject/delete/delete.component';
import {AddGroupComponent} from "./component/group/add/add.component";
import {UpdateGroupComponent} from "./component/group/update/update.component";
import {DeleteGroupComponent} from "./component/group/delete/delete.component";
import {GroupComponent} from './component/group/group.component';
import {StudentService} from "./service/student.service";
import {StudentComponent} from './component/student-details/student.component';
import {StudentListComponent} from './component/student/student-list.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {DeleteStudentComponent} from "./component/student/delete/delete.component";
import {MatSelectModule} from "@angular/material/select";
import {AddEditStudentComponent} from './component/student/add-edit/add-edit.component';
import {DeleteMarkComponent} from "./component/student-details/delete/delete.component";
import {AddEditMarkComponent} from "./component/student-details/add-edit/add-edit.component";
import {MatSidenavModule} from "@angular/material/sidenav";


@NgModule({
  declarations: [
    AppComponent,
    SubjectComponent,
    GroupListComponent,
    GroupComponent,
    StudentListComponent,
    StudentComponent,
    AddSubjectComponent,
    UpdateSubjectComponent,
    DeleteSubjectComponent,
    AddGroupComponent,
    UpdateGroupComponent,
    DeleteGroupComponent,
    AddEditStudentComponent,
    DeleteStudentComponent,
    AddEditMarkComponent,
    DeleteMarkComponent,
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatTableModule,
        MatButtonModule,
        MatPaginatorModule,
        MatSortModule,
        MatToolbarModule,
        MatIconModule,
        MatDialogModule,
        MatInputModule,
        MatAutocompleteModule,
        MatSelectModule,
        MatSidenavModule
    ],
  providers: [GroupService, SubjectService, StudentService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
