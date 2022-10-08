import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Member } from './member';

const URL = "CLAIMPORTAL/"
const URL1 = "http://localhost:8084/"

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(public http: HttpClient) { }

  registerMember(member: any): Observable<object> {
    return this.http.post(URL1 + "member/register", member);
  }

  updateMember(member: any): Observable<object> {
    return this.http.put(URL1 + "member/update", member);
  }

  getMemberById(memberId:any) {
    return this.http.get(URL1 +"member/retrive/memberId/"+memberId)
  }

}
