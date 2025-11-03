import { Component, Input, OnChanges, ChangeDetectorRef } from '@angular/core';
import { AiApiService } from '../../services/ai-api-service';
import { finalize } from 'rxjs';
import { marked } from 'marked';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { raw } from 'express';

@Component({
  selector: 'app-ai-facts',
  imports: [],
  templateUrl: './ai-facts.html',
  styleUrl: './ai-facts.css',
})
export class AiFacts implements OnChanges {
  @Input() topicId!: string;
  fact: SafeHtml | null = null;
  loading = false;

  
  constructor(
    private api: AiApiService, 
    private cdr: ChangeDetectorRef, 
    private sanitizer: DomSanitizer) {}
  
  ngOnChanges(): void {
    if (!this.topicId) return;

    this.loading = true;
    this.fact = '';

    this.api.getFact(this.topicId)
      .pipe(finalize(() => {
        this.loading = false;
        this.cdr.detectChanges();
      }))
      .subscribe({
        next: (response) => {
          const rawFact = marked.parse(response.text) as string;
          this.fact = this.sanitizer.bypassSecurityTrustHtml(rawFact);
        },
        error: (err) => console.error('Error generating fact:', err)
      });
  }
}
