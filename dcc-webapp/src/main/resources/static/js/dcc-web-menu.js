/**
 * Created by shawn on 28/07/16.
 */
angular.module('dccweb')
    .directive('activeLink', function() {
        return {
            link: function(scope, element, attrs) {
                element.find('.nav a').on('click', function() {
                    angular.element(this)
                        .parent().siblings('.active').removeClass('active');
                    angular.element(this)
                        .parent().addClass('active');
                });
            }
        };
    });
