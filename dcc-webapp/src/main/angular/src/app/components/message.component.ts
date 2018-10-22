/**
 * Created by shawn on 16/11/16.
 */
import {Component, OnInit} from '@angular/core';
import {LogMessage} from '../models/log.message';
import {MessageService} from '../services/message.service';

@Component({
    moduleId: module.id,
    selector: 'app-dcc-messages',
    templateUrl: './../html/messages/messages.html'
})
export class MessageComponent implements OnInit {

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
