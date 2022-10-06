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

  registerMember(member: Member): Observable<object> {
    return this.http.post(URL1 + "member/register", member);
  }
}
