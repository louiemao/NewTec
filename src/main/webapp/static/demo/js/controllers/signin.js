'use strict';

/* Controllers */
// signin controller
app.controller('SigninFormController', ['$scope', '$http', '$state', 'subject', 'usernamePasswordToken','$localStorage',
    function ($scope, $http, $state, subject, usernamePasswordToken,$localStorage) {
        $scope.user = {};
        $scope.authError = null;
        $scope.token = usernamePasswordToken;

        if(subject.authenticated){
            $state.go('app.dashboard-v1');
        }

        $scope.login = function () {
            $scope.authError = null;
            subject.login($scope.token).then(function (data) {
                console.log(data);
                if(!data.info.authc.principal){
                    $scope.authError = 'Username or Password not right';
                }else{
                    $scope.user=data.info.authc.principal;
                    // save settings to local storage
                    if (angular.isDefined($localStorage.settings)) {
                        $scope.app.settings = $localStorage.settings;
                    }
                    $state.go('app.dashboard-v1');
                }
            }, function (error) {
                console.log(error);
                $scope.authError = 'Server Error';
            });
        };

        $scope.setStyle=function(){
            var height_window=document.documentElement.clientHeight;
            var height_div=document.getElementById("page_signin").clientHeight;
            $scope.myStyle={"min-height": height_window,
                "height":height_div<height_window?height_window:height_div,
                "max-height":height_div<height_window?height_window:height_div};
        };

        $scope.setStyle();
    }])
;