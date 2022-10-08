import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Member } from '../member';
import { MemberService } from '../memberService.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  member: Member = new Member();
  registerForm = new FormGroup({
    name: new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z].*")]),
    address: new FormControl("", [Validators.required]),
    state: new FormControl("", [Validators.required]),
    country: new FormControl("", [Validators.required]),
    emailAddress: new FormControl("", [Validators.required, Validators.email]),
    pan: new FormControl("", [Validators.required, Validators.minLength(12), Validators.maxLength(12)]),
    contactNo: new FormControl("", [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern("[0-9]*")]),
    dob: new FormControl("", [Validators.required]),
  })

  constructor(private memberService: MemberService,private router: Router) { }

  ngOnInit(): void {

  }

  registerMember() {
    console.log(this.registerForm.get);

    this.memberService.registerMember(this.registerForm.getRawValue()).subscribe(
      (data:any) => {
        alert(data.message)
        this.router.navigate(['/home'])
      }, (error:any) => {
        alert(error)
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
