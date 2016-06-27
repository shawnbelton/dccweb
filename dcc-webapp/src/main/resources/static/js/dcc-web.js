/**
 * Created by shawn on 20/06/16.
 */
angular.module('dccweb', [])
    .service('infoService', ['$http',function ($http) {
        this.getInfo = function (callback) {
            $http.get('/interface/status').then(function (resp) {
                callback(resp.data);
            });
        };
    }]).service('trainsService', ['$http', function ($http) {
        this.createTrain = function(callback, train) {
            $http.post('/trains/create', train).then(function (resp) {
                callback(resp.data);
            });
        };
        this.getTrains = function(callback) {
            $http.get('/trains').then(function (resp) {
                callback(resp.data);
            });
        }
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
    }]).controller('trains', ['trainsService', function (trainsService) {

        var self = this;

        self.list = true;
    
        self.getTrains = function () {
            trainsService.getTrains(function(response) {
               self.trains = response; 
            });  
        };

        self.newTrain = function () {
            self.list = false;
            self.train = {};
        };

        self.cancel = function () {
            self.list = true;
            self.getTrains();
        };

        self.train = {};
        self.getTrains();
        self.createTrain = function() {
            trainsService.createTrain(function(response) {
                if (response) {
                    self.train = {};
                    self.list = true;
                    self.getTrains();
                }
            }, self.train);
        };
    }]);
