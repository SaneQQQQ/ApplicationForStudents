import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GroupComponent} from './component/group/group.component';
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

@NgModule({
  declarations: [
    AppComponent, GroupComponent, SubjectComponent, AddSubjectComponent, UpdateSubjectComponent
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
    MatInputModule
  ],
  providers: [GroupService, SubjectService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
