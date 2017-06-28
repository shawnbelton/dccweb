/**
 * Created by shawn on 16/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Message} from "../models/message";
import {MessageService} from "../services/message.service";

@Component({
    moduleId: module.id,
    selector: 'dcc-messages',
    templateUrl: './../html/messages/messages.html'
})
export class MessageComponent implements OnInit {

    messages: Message[];

    constructor(private messageService: MessageService) { }

    getMessages(): void {
        this.messageService
            .getMessages().subscribe(messages => this.messages = messages);
    }

    ngOnInit(): void {
        this.getMessages();
    }
}
