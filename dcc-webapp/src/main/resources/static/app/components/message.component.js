"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
/**
 * Created by shawn on 16/11/16.
 */
var core_1 = require("@angular/core");
var message_service_1 = require("../services/message.service");
var MessageComponent = (function () {
    function MessageComponent(messageService) {
        this.messageService = messageService;
    }
    MessageComponent.prototype.getMessages = function () {
        var _this = this;
        this.messageService
            .getMessages().subscribe(function (messages) { return _this.messages = messages; });
    };
    MessageComponent.prototype.ngOnInit = function () {
        this.getMessages();
    };
    MessageComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'dcc-messages',
            templateUrl: '../../messages/messages.html'
        }), 
        __metadata('design:paramtypes', [message_service_1.MessageService])
    ], MessageComponent);
    return MessageComponent;
}());
exports.MessageComponent = MessageComponent;
//# sourceMappingURL=message.component.js.map