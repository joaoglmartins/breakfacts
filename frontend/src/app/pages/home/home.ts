import { Component } from '@angular/core';
import { TopicApiService } from '../../services/topic-api-service';
import { AiFacts } from "../../components/ai-facts/ai-facts";
import { AsyncPipe, NgIf, NgForOf } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [AiFacts, AsyncPipe, NgIf, NgForOf],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {
  selectedTopicId: string = '';
  topics$;

  constructor(private api: TopicApiService) {
    this.topics$ = this.api.getTopics();
  }

  generateFact(topicId: string) {
    this.selectedTopicId = '';
    queueMicrotask(() => (this.selectedTopicId = topicId));
  }
}
