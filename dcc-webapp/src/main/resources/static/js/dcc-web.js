/**
 * Created by shawn on 30/06/16.
 */
angular.module('dccweb', ["ngRoute"])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'home/home.html',
            controller: 'home as home'
        }).when('/engine', {
            templateUrl: 'trains/home.html',
            controller: 'trains as trains'
        }).when('/decoders', {
            templateUrl: 'decoder/home.html',
            controller: 'decoders as decoders'
        }).otherwise({redirectTo: '/'});
    }]);