import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MemberService } from '../memberService.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-updation',
  templateUrl: './updation.component.html',
  styleUrls: ['./updation.component.css']
})
export class UpdationComponent implements OnInit {
  registerForm = new FormGroup({
    memberId:new FormControl(),
    name: new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z].*")]),
    address: new FormControl("", [Validators.required]),
    state: new FormControl("", [Validators.required]),
    country: new FormControl("", [Validators.required]),
    emailAddress: new FormControl("", [Validators.required, Validators.email]),
    pan: new FormControl("", [Validators.required, Validators.minLength(12), Validators.maxLength(12)]),
    contactNo: new FormControl("", [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern("[0-9]*")]),
    dob: new FormControl("", [Validators.required]),
  })

  constructor(private memberService:MemberService,private router: Router) { }

  ngOnInit(): void {
    
  }

  getMember()
  {
    const observable = this.memberService.getMemberById(this.registerForm.get("memberId")?.value).
    subscribe((memberFromServer: any) => {
      console.log(memberFromServer)
     this.registerForm.patchValue(
       {
         memberId:memberFromServer.memberId,
         name:memberFromServer.name,
         address:memberFromServer.address,
         dob:memberFromServer.dob,
         state:memberFromServer.state,
         country:memberFromServer.country,
         contactNo:memberFromServer.contactNo,
         pan:memberFromServer.pan,
         emailAddress:memberFromServer.emailAddress
       }
     )
     console.log(this.registerForm.get("memberId"))
      }
    , (error: any) => {
      alert(error.error.errorMessage)
    }
    );
  }

  

  updateMember() {
    console.log(this.registerForm.get);
   
    this.memberService.updateMember(this.registerForm.getRawValue()).subscribe(
      (data:any) => {
        alert(data.message)
        this.router.navigate(['/home'])
      }
    )
  }

  get Name(): FormControl {
    return this.registerForm.get("name") as FormControl;
  }
  get Address(): FormControl {
    return this.registerForm.get("address") as FormControl;
  }
  get State(): FormControl {
    return this.registerForm.get("state") as FormControl;
  }
  get Country(): FormControl {
    return this.registerForm.get("country") as FormControl;
  }
  get EmailAddress(): FormControl {
    return this.registerForm.get("emailAddress") as FormControl;
  }
  get Pan(): FormControl {
    return this.registerForm.get("pan") as FormControl;
  }
  get ContactNo(): FormControl {
    return this.registerForm.get("contactNo") as FormControl;
  }
  get Dob(): FormControl {
    return this.registerForm.get("dob") as FormControl;
  }

}
function values(values: any, arg1: {}) {
  throw new Error('Function not implemented.');
}

