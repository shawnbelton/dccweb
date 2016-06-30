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
    }]).controller('info', ['$interval', 'infoService',function ($interval, infoService) {

    var self = this;

    self.info = {
        status: ''
    };

    console.log('dccweb info created.');

    $interval(function () {
        infoService.getInfo(function (info) {
            self.info = info;
        });
    }, 1000);
}]);