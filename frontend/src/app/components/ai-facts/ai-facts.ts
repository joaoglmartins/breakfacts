import { Component, Input, OnChanges, ChangeDetectorRef } from '@angular/core';
import { AiApiService } from '../../services/ai-api-service';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-ai-facts',
  imports: [],
  templateUrl: './ai-facts.html',
  styleUrl: './ai-facts.css',
})
export class AiFacts implements OnChanges {
  @Input() topicId!: string;
  fact: string = '';
  loading = false;

  
  constructor(private api: AiApiService, private cdr: ChangeDetectorRef) {}
  
  ngOnChanges(): void {
    if (!this.topicId) return;

    this.loading = true;
    this.fact = '';

    this.api.getFact(this.topicId)
      .pipe(finalize(() => {
        this.loading = false;
        console.log(this.fact);
        console.log('Loading ' + this.loading);
        this.cdr.detectChanges();
      }))
      .subscribe({
        next: (response) => {
          this.fact = response.text;
        },
        error: (err) => console.error('Error generating fact:', err)
      });
  }
}
