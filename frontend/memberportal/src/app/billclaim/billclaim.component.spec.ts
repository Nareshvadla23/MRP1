import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillclaimComponent } from './billclaim.component';

describe('BillclaimComponent', () => {
  let component: BillclaimComponent;
  let fixture: ComponentFixture<BillclaimComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BillclaimComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BillclaimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
