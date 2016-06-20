/**
 * Created by shawn on 20/06/16.
 */
angular.module('dccweb',[])
    .service('infoService', function($http) {
        this.getInfo = function(callback) {
            $http({
                method: 'GET',
                url: '/interface/status',
                headers: {
                    'Content-type': 'application/json'
                }
            }).success(function(resp) {
                callback(resp);
            }).error(function(resp) {
                callback(resp);
            });
        }
    }).controller('info', function($scope, $interval, infoService) {
    $scope.info = {
        status: 'Disconnected'
    };

    console.log('dccweb info created.');

    $interval(function() {
        infoService.getInfo(function (info) {
            $scope.info = info;
        });
    }, 1000);
});
