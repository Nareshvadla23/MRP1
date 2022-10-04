import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BillclaimComponent } from './billclaim/billclaim.component';
import { HomepageComponent } from './homepage/homepage.component';
import { RegistrationComponent } from './registration/registration.component';
import { UpdationComponent } from './updation/updation.component';

const routes: Routes = [{ path: "home", component: HomepageComponent },
{ path: "register", component: RegistrationComponent },
{ path: "update", component: UpdationComponent },{ path: "claim", component: BillclaimComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
