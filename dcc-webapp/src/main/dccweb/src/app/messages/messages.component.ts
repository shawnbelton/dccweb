import {Component, OnInit} from '@angular/core';
import {LogMessage} from '../models/log-message';
import {MessageService} from '../message.service';


@Component({
  selector: 'app-dcc-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  messages: LogMessage[];

  constructor(private messageService: MessageService) { }

  getMessages(): void {
    this.messageService
      .getMessages().subscribe(messages => this.messages = messages);
  }

  ngOnInit(): void {
    this.getMessages();
  }

}
