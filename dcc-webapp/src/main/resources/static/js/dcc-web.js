/**
 * Created by shawn on 20/06/16.
 */
angular.module('dccweb', [])
    .service('infoService', ['$http',function ($http) {
        this.getInfo = function (callback) {
            $http.get('/interface/status').then(function (resp) {
                callback(resp.data);
            }, function(error) {
                alert(error);
            });
        };
    }]).service('trainsService', ['$http', function ($http) {
        this.createTrain = function(callback, train) {
            $http.post('/trains/create', train).then(function (resp) {
                callback(resp.data);
            }, function(error) {
               alert(error);
            });
        };
    }]).controller('info', ['$scope', '$interval', 'infoService',function ($scope, $interval, infoService) {
        $scope.info = {
            status: ''
        };

        console.log('dccweb info created.');

        $interval(function () {
            infoService.getInfo(function (info) {
                $scope.info = info;
            });
        }, 1000);
    }]).controller('trains', ['$scope', 'trainsService', function ($scope, trainsService) {
        $scope.train = {};
        $scope.createTrain = function() {
            trainsService.createTrain(function(response) {
                if (response) {
                    $scope.train = {};
                }
            }, $scope.train);
        };
    }]);
