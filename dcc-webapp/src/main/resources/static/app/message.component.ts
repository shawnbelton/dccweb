/**
 * Created by shawn on 16/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Message} from "./message";
import {MessageService} from "./message.service";

@Component({
    moduleId: module.id,
    selector: 'dcc-messages',
    templateUrl: '/messages/messages.html'
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
