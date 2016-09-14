/**
 * Created by shawn on 30/06/16.
 */
angular.module('dccweb')
    .service('infoService', ['$http',function ($http) {
        this.getInfo = function (callback) {
            $http.get('/interface/status').then(function (resp) {
                callback(resp.data);
            });
        };
        this.getMessages = function (callback) {
            $http.get('/messages').then(function (resp) {
                callback(resp.data);
            });
        };
    }]).controller('info', ['$interval', 'infoService',function ($interval, infoService) {

    var self = this;

    self.info = {
        status: ''
    };

    self.messages = null;

    console.log('dccweb info created.');

    $interval(function () {
        infoService.getInfo(function (info) {
            self.info = info;
        });
    }, 1000);
    $interval(function () {
        infoService.getMessages(function (messages) {
            self.messages = messages;
        });
    }, 1000);
}]);