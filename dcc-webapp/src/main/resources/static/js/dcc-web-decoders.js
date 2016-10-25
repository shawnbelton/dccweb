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
        this.getDecoder = function(callback, id) {
            $http.post('/decoders/byId', id).then(function (resp) {
                callback(resp.data);
            });
        };
        this.addFunction = function(callback, decoderFunction) {
            $http.post('/decoders/function/add', decoderFunction).then(function (resp) {
                callback(resp.data);
            });
        };
    }]).controller('decoders', ['decoderService', function(decoderService) {
        var self = this;

        self.decoderFunction = {};

        self.getDecoders = function () {
            decoderService.getDecoders(function (response) {
                self.decoders = response;
            });
        };

        self.readDecoder = function() {
            decoderService.readDecoder(function(response) {
                self.setDecoder(response);
                self.getDecoders();
            });
        };

        self.editDecoder = function(decoderId) {
            decoderService.getDecoder(function(response) {
                self.setDecoder(response);
            }, decoderId);
        };


        self.setDecoder = function(decoder) {
            self.decoder = decoder;
            self.functionNumbers = [];
            for(var index = 1; index<=29; index++) {
                if (self.isAvailable(index, decoder.decoderFunctions)) {
                    self.functionNumbers.push(index);
                }
            }
            self.decoderFunction.number = self.functionNumbers[0];
        };

        self.isAvailable = function(value, functions) {
            var available = true;
            if (functions != null) {
                for(var index = 0; index < functions.length; index++) {
                    if (functions[index].number==value) {
                        available = false;
                    }
                }
            }
            return available;
        };

        self.addFunction = function() {
            self.decoderFunction.decoderId = self.decoder.decoderId;
            decoderService.addFunction(function(response) {
                self.decoderFunction = {};
                self.setDecoder(response);
            }, self.decoderFunction);
        };

        self.getDecoders();

}]);