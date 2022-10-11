import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Member } from './member';

const URL = "http://localhost:8090/"
const URL1 = "http://localhost:8084/"
const URL3 ="https://zkiwdg2nu9.execute-api.us-west-2.amazonaws.com/prod/"

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(public http: HttpClient) { }

  registerMember(member: any): Observable<object> {
    return this.http.post(URL3, member);
  }

  billClaim(claim: any): Observable<object> {
    return this.http.post(URL3 , claim);
  }

  updateMember(member: any): Observable<object> {
    return this.http.put(URL3 , member);
  }

  getMemberById(memberId:any) {
    return this.http.get(URL3 +memberId)
  }

}
