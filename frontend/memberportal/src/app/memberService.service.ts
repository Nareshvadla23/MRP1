import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Member } from './member';

const URL3 = "http://localhost:8090/member/"
 const URL1 = "http://localhost:8084/member/"

//const URL3 ="https://4aqo00e16i.execute-api.us-west-2.amazonaws.com/prod/"
//const URL1 ="https://zkiwdg2nu9.execute-api.us-west-2.amazonaws.com/prod/"

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(public http: HttpClient) { }

  registerMember(member: any): Observable<object> {
    return this.http.post(URL1+"register", member);
  }

  billClaim(claim: any): Observable<object> {
    return this.http.post(URL3, claim);
  }

  updateMember(member: any): Observable<object> {
    return this.http.put(URL1+"update" , member);
  }

  getMemberById(memberId:any) {
    return this.http.get(URL3+"retrive/memberId/"+memberId)
  }

}
