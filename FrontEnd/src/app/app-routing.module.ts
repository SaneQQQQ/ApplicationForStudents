import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GroupComponent} from './component/group/group.component';
import {SubjectComponent} from './component/subject/subject.component';

const routes: Routes = [
  {path: 'groups', component: GroupComponent},
  {path: 'subjects', component: SubjectComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
