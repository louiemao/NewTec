/**
 * Created by george on 15-7-1.
 */
console.log("index.js");
//创建App
var newapp = angular.module('newapp', [ 'angularShiro','ui.bootstrap', 'ngRoute', "newapp.customer", "newapp.server", "newapp.user", "newapp.usermanage","newapp.train"]);

//配置路由
//newapp.config(['$routeProvider', '$locationProvider', 'angularShiroConfigProvider','$http',
newapp.config(
    function ($routeProvider, $locationProvider,angularShiroConfigProvider) {
        //config.setFilter('/usermanage','perms["user:manage"]');
        angularShiroConfigProvider.setFilter('/**','authc');

        $routeProvider.
            when('/', {
                templateUrl: '/static/templates/module1/welcome.html',
                controller: 'WelcomeController'
            }).
            when('/servers', {
                templateUrl: '/static/templates/module1/server/show-servers.html',
                controller: 'ShowServersController'
            }).
            when('/servers/add', {
                templateUrl: '/static/templates/module1/server/add-server.html',
                controller: 'AddServerController'
            }).
            when('/servers/:server_id', {
                templateUrl: '/static/templates/module1/server/add-server.html',
                controller: 'AddServerController'
            }).
            when('/customers', {
                templateUrl: '/static/templates/module1/customer/show-customers.html',
                controller: 'ShowCustomersController'
            }).
            when('/customers/add', {
                templateUrl: '/static/templates/module1/customer/add-customer.html',
                controller: 'AddCustomerController'
            }).
            when('/customers/:customer_id', {
                templateUrl: '/static/templates/module1/customer/add-customer.html',
                controller: 'AddCustomerController'
            }).
            when('/trainsview', {
                templateUrl: '/static/templates/module1/train/trainsview.html',
                controller: 'TrainController'
            }).
            when('/trainupdate/:id', {
                templateUrl: '/static/templates/module1/train/trainedit.html',
                controller: 'TrainUpdateController'
            }).
            when('/trainadd', {
                templateUrl: '/static/templates/module1/train/trainedit.html',
                controller: 'TrainAddController'
            }).
            when('/usermanage', {
                templateUrl: '/static/templates/security/usermanage/userManage.html',
                controller: 'UserManageController'
            }).
            when('/userupdate/:id', {
                templateUrl: '/static/templates/security/usermanage/userEdit.html',
                controller: 'UserEditController'
            }).
            when('/useradd', {
                templateUrl: '/static/templates/security/usermanage/userEdit.html',
                controller: 'UserEditController'
            }).
            otherwise({
                redirectTo: '/'
            });
        $locationProvider.html5Mode(true);

    //}]);
    });
newapp.run(function($rootScope,subject){
   $rootScope.subject=subject;
});
//定义一个fitler
newapp.filter('slice', function () {
    return function (arr, start, end) {
        return (arr || []).slice(start, end);
    };
});

//newapp.$on();
newapp.factory('UserService', ['$resource', '$http','$location', function ($resource, $http,$location) {
    return new UserService($resource, $http,$location);
}]);
//创建控制器
newapp.controller('newappctl', function ($scope, $modal, $log,$location,subject,UserService,$http) {
    // var myModal = $modal({title: 'My Title', content: 'My Content', show: true});
   // $scope.islogin = false;

    $scope.items = ['item1', 'item2', 'item3'];
    $scope.animationsEnabled = true;

    //$http.get("/static/templates/module1/nav.json").success(function (json) {
    $scope.getMenuInfos=function(username){
      //var username=  subject.getPrincipal().usernamae==null?"guest":subject.getPrincipal().username;
        $http.get("/restUser/getMenuInfosByUsername/"+username).success(function (json) {
            $scope.navbars = json;
        }).error(function (error) {
            console.log(error);
        });
    };

    $scope.logout = function () {
       // $scope.islogin = false;
        subject.logout();
        UserService.logout();
        $location.path('/');
    };
    $scope.open = function (size) {
        var modalInstance = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: '/static/templates/module1/user/login.html',
            controller: "LoginController",
            windowClass: 'center-modal',
            size: size,
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });
        modalInstance.result.then(function (result) {
            //$scope.islogin = result;
            if(result!=null){
                $scope.getMenuInfos(result);
                $location.path('/');
            }
        }, function () {
            $log.info("modal dismissed at:" + new Date());
        });
        $scope.toggleAnimation = function () {
            $scope.animationsEnabled = !$scope.animationsEnabled;
        }
    }

    var username=  subject.getPrincipal().usernamae==null?"guest":subject.getPrincipal().username;
    $scope.getMenuInfos(username);
});
newapp.controller('WelcomeController', ['$scope', "ServerService", function ($scope, ServerService) {
    $scope.servers = ServerService.query();
}]);
