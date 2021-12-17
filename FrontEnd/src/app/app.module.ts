import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GroupComponent } from './component/group/group.component';
import { GroupService } from './service/group.service';

@NgModule({
  // add components
  declarations: [
    AppComponent,
    GroupComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  // add services
  providers: [GroupService],
  bootstrap: [AppComponent]
})
export class AppModule { }
