/**
 * Created by Louie on 2015/8/5.
 */
'use strict';

/* Controllers */

app.controller('NavbarCtrl', ['$scope', '$http','subject', function ($scope, $http,subject) {
    $http.get('/cp/getMenuInfos/'+subject.getPrincipal().userCd).success(function (data) {
        console.log(data);
        $scope.navbars = data;
    }).error(function (data) {
        console.log(data);
        $scope.navbars = null;
    });
}]);
