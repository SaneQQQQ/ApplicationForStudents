import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GroupComponent} from './component/group/group.component';
import {SubjectComponent} from './component/subject/subject.component';
import {GroupService} from './service/group.service';
import {SubjectService} from './service/subject.service';
import {FormsModule} from "@angular/forms";
import {MatTableModule} from '@angular/material/table';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";


@NgModule({
  declarations: [
    AppComponent, GroupComponent, SubjectComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatButtonModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [GroupService, SubjectService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
