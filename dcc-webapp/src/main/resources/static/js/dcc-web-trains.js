/**
 * Created by shawn on 20/06/16.
 */
angular.module('dccweb')
    .service('trainsService', ['$http', function ($http) {
        this.createTrain = function(callback, train) {
            $http.post('/trains/create', train).then(function (resp) {
                callback(resp.data);
            });
        };
        this.getTrains = function(callback) {
            $http.get('/trains').then(function (resp) {
                callback(resp.data);
            });
        };
        this.getTrain = function (callback, id) {
            $http.post('/trains/byId', id).then(function (resp) {
                callback(resp.data);
            });
        };
    }]).controller('trains', ['trainsService', function (trainsService) {

        var self = this;

        self.getTrains = function () {
            trainsService.getTrains(function(response) {
                self.trains = response;
                self.list = true;
            });  
        };

        self.newTrain = function () {
            self.list = false;
            self.train = {};
        };

        self.cancel = function () {
            self.getTrains();
        };

        self.createTrain = function() {
            trainsService.createTrain(function(response) {
                self.trains = response;
                self.train = {};
                self.list = true;
            }, self.train);
        };
    
        self.editTrain = function(trainId) {
            trainsService.getTrain(function(response) {
                self.train = response;
                self.list = false;
            }, trainId);
        };
    
        self.configure = function(train) {
            if (train.showConfig) {
                train.showConfig = false;
            } else {
                train.showConfig = true;
            }
        };

        self.train = {};
        self.getTrains();

    }]);
