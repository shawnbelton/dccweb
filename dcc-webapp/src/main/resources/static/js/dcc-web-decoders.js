/**
 * Created by shawn on 07/07/16.
 */
angular.module('dccweb')
    .service('decoderService', ['$http', function($http) {
        this.readDecoder = function(callback) {
            $http.get('/decoders/read').then(function(resp) {
                callback(resp.data);
            });
        };
        this.getDecoders = function(callback) {
            $http.get('/decoders/all').then(function (resp) {
                callback(resp.data);
            });
        };
    }]).controller('decoder', ['decoderService', function(decoderService) {
        var self = this;

        self.getDecoders = function () {
            decoderService.getDecoders(function (response) {
                self.decoders = response;
            });
        };

        self.readDecoder = function() {
            decoderService.readDecoder(function(response) {
                self.decoder = response;
                self.getDecoders();
            });
        };

        self.getDecoders();
}]);