/**
 * Created by shawn on 30/06/16.
 */
angular.module('dccweb', ["ngRoute"])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'trains/home.html',
            controller: 'trains as trains'
        }).when('/decoders', {
            templateUrl: 'decoder/home.html',
            controller: 'decoder as decoder'
        }).otherwise({redirectTo: '/'});
    }]);