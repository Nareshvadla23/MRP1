import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { RegistrationComponent } from './registration/registration.component';
import { BillclaimComponent } from './billclaim/billclaim.component';
import { UpdationComponent } from './updation/updation.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    RegistrationComponent,
    BillclaimComponent,
    UpdationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }