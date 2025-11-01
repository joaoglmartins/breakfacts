import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AiFacts } from './ai-facts';

describe('AiFacts', () => {
  let component: AiFacts;
  let fixture: ComponentFixture<AiFacts>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AiFacts]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AiFacts);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
