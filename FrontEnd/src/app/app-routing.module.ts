import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GroupListComponent} from './component/group/group-list.component';
import {SubjectComponent} from './component/subject/subject.component';
import {GroupComponent} from "./component/group/group.component";
import {StudentListComponent} from "./component/student/student-list.component";
import {StudentComponent} from "./component/student-details/student.component";

const routes: Routes = [
  {path: '', redirectTo: 'students', pathMatch: 'full'},
  {path: 'groups', component: GroupListComponent},
  {path: 'subjects', component: SubjectComponent},
  {path: 'students', component: StudentListComponent},
  {path: 'students/:id', component: StudentComponent},
  {path: 'groups/:id', component: GroupComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
