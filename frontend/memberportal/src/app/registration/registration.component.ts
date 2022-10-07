import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Member } from '../member';
import { MemberService } from '../memberService.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  member :Member = new Member();
  registerForm = new FormGroup({
    name: new FormControl("",[Validators.required]),
    address:new FormControl(""),
    state:new FormControl(""),
    country: new FormControl(""),
    emailAddress:new FormControl(""),
    pan: new FormControl(""),
    contactNo: new FormControl(""),
    dob: new FormControl(""),
  })

  constructor(private memberService:MemberService) { }

  ngOnInit(): void {
    
  }

  registerMember()
  {
    console.log(this.registerForm.get("name"));
    //  this.memberService.registerMember(this.member).subscribe(
    //    data=>{
    //      alert("registration sucessfull")
    //    }
    //  )
  }

  get Name():any{
    return this.registerForm.get("name")?.invalid &&this.registerForm.get("name")?.pristine;
  }

}
