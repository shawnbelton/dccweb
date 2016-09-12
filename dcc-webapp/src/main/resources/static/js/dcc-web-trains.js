/**
 * Created by shawn on 20/06/16.
 */
angular.module('dccweb')
    .service('trainsService', ['$http', function ($http) {
        this.saveTrain = function(callback, train) {
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
        this.getDecoders = function (callback) {
            $http.get('/decoders/all').then(function (resp) {
                callback(resp.data);
            });
        };
        this.getCab = function (callback, train) {
            $http.post('/trains/cab', train).then(function (resp) {
                callback(resp.data);
            });
        };
        this.updateCab = function(callback, cab) {
            $http.post('trains/cab/update', cab).then(function (resp) {
                callback(resp.data);
            });
        };
    }]).controller('trains', ['trainsService', function (trainsService) {

        var self = this;

        self.update = true;

        self.cab = {
            train: null,
            speed: 0,
            direction: 'UP'
        };

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
            trainsService.saveTrain(function(response) {
                self.trains = response;
                self.train = {};
                self.list = true;
            }, self.train);
        };

        self.assignDecoder = function(train) {
            trainsService.saveTrain(function (response) {
                self.trains = response;
                self.train = {};
                self.list = true;
            }, train);
        };
    
        self.editTrain = function(trainId) {
            trainsService.getTrain(function(response) {
                self.train = response;
                self.list = false;
            }, trainId);
        };

        self.getDecoders = function () {
            trainsService.getDecoders(function (response) {
                self.decoders = response;
            })
        }
    
        self.configure = function(train) {
            self.train = train;
            if (train.showConfig) {
                train.showConfig = false;
            } else {
                train.showConfig = true;
            }
        };

        self.drive = function(train) {
            trainsService.getCab(function(response) {
                self.cab = response;
            }, train);
        };

        self.updateCab = function() {
            trainsService.updateCab(function(response) {
                self.update = response;
            }, self.cab);
        };

        self.stop = function() {
            self.cab.speed = 0;
            self.cab.direction = 'STOP';
            self.updateCab();
        };

        self.up = function() {
            self.cab.direction = 'UP';
            self.updateCab();
        };

        self.down = function() {
            self.cab.direction = 'DOWN';
            self.updateCab();
        };

        self.speedChanged = function() {
            if (self.update) {
                self.update = false;
                self.updateCab();
            }
        };

        self.decoders = {};
        self.train = {};
        self.getTrains();
        self.getDecoders();

    }]);
