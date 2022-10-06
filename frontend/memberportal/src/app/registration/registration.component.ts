import { Component, OnInit } from '@angular/core';
import { Member } from '../member';
import { MemberService } from '../memberService.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  member :Member = new Member();

  constructor(private memberService:MemberService) { }

  ngOnInit(): void {
  }

  registerMember()
  {
    console.log(this.member);
     this.memberService.registerMember(this.member).subscribe(
       data=>{
         alert("registration sucessfull")
       },
     )
  }

}
