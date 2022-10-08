import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MemberService } from '../memberService.service';

@Component({
  selector: 'app-billclaim',
  templateUrl: './billclaim.component.html',
  styleUrls: ['./billclaim.component.css']
})
export class BillclaimComponent implements OnInit {

  claimForm = new FormGroup({
    name: new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z].*")]),
    providerName: new FormControl("", [Validators.required]),
    memberId: new FormControl("", [Validators.required,Validators.maxLength(5)]),
    dateofDischarge: new FormControl("", [Validators.required]),
    dateofAdmission: new FormControl("", [Validators.required]),
    dob: new FormControl("", [Validators.required]),
    billAmount: new FormControl("", [Validators.required]),
  })

  constructor(private memberService: MemberService,private router: Router) { }

  ngOnInit(): void {
  }

  submitClaim()
   {
    this.memberService.billClaim(this.claimForm.getRawValue()).subscribe(
      (data:any) => {
        alert(data.message)
        this.router.navigate(['/home'])
      }, (error:any) => {
        alert(error)
      }

    )
   }

  get Name(): FormControl {
    return this.claimForm.get("name") as FormControl;
  }
  get ProviderName(): FormControl {
    return this.claimForm.get("providerName") as FormControl;
  }
  get DateofDischarge(): FormControl {
    return this.claimForm.get("dateofDischarge") as FormControl;
  }
  get DateofAdmission(): FormControl {
    return this.claimForm.get("dateofAdmission") as FormControl;
  }
  get Dob(): FormControl {
    return this.claimForm.get("dob") as FormControl;
  }
  get MemberId(): FormControl {
    return this.claimForm.get("memberId") as FormControl;
  }
  get BillAmount(): FormControl {
    return this.claimForm.get("billAmount") as FormControl;
  }

}
