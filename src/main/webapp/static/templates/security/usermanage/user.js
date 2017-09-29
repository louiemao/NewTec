/**
 * Created by Louie on 2015/7/15.
 */
var appUserManage = angular.module("newapp.usermanage", ['angularShiro', 'ngResource']);

/**
 * 创建一个新的服务
 */
appUserManage.factory('UserService', ['$resource', '$http', '$location', function ($resource, $http, $location) {
    return new UserService($resource, $http, $location);
}]);

function UserService(resource, http, location) {

    var actions = {
        'get': {
            method: 'GET',
        },
        'query': {
            method: 'GET',
            isArray: true
        },
        'save': {
            method: 'POST',
            isArray: true,
        },
        'update': {
            method: 'PUT',
            isArray: true,
        },
        'remove': {
            method: 'DELETE',
        }
    };

    this.queryUsers = function (scope) {
        scope.resetError();

        var UserResource = resource('/restUser/queryUsers', {}, actions);
        UserResource.query({}, function (data) {
            scope.users = data;
        }, function (error) {
            scope.setError('无法查询用户列表');
        });
    };

    this.getUserById = function (id, scope) {
        scope.resetError();

        var TrainResource = resource('/restUser/getUserById/:id', {}, actions);
        TrainResource.get({id: id}, function (data) {
            scope.user = data;
        }, function (error) {
            scope.setError('无法查询用户编号：' + id);
        });
    };

    this.createUser = function (user, scope) {
        scope.resetError();

        var UserResource = resource('/restUser/createUser', {}, actions);
        UserResource.save(user, function (data) {
            scope.gotoUsersView();
        }, function (error) {
            scope.setError('无法创建新用户');
        });
    };

    this.updateUser = function (user, scope) {
        scope.resetError();

        var UserResource = resource('/restUser/updateUser', {}, actions);
        UserResource.save(user, function (data) {
            scope.gotoUsersView();
        }, function (error) {
            scope.setError('无法修改用户信息');
        });
    };

    this.removeUserById = function (id, scope) {
        scope.resetError();

        var UserResource = resource('/restUser/removeUserById/:id', {}, actions);
        UserResource.remove({id: id}, function (data) {
            scope.gotoUsersView();
        }, function (error) {
            scope.setError('无法删除用户编号： ' + id);
        });
    };

    this.logout = function () {
        var TrainResource = resource('/api/logout', {}, actions);
        TrainResource.get({}, function (data) {
            location.path('/');
        }, function (error) {
            console.log(error);
        });
    };
};

/**
 * UserManageController
 */

appUserManage.controller('UserManageController',
    ['UserService', '$scope', '$location', 'subject', 'usernamePasswordToken',
        function (UserService, $scope, $location, subject, usernamePasswordToken) {

            $scope.queryUsers = function () {
                UserService.queryUsers($scope);
            };

            $scope.removeUserById = function (id) {
                UserService.removeUserById(id, $scope);
            };

            $scope.gotoUserAdd = function () {
                $location.path('/useradd');
            };

            $scope.gotoUserUpdate = function (id) {
                $location.path('/userupdate/' + id);
            };

            $scope.gotoUsersView = function () {
                $location.path('/usermanage');
            };

            $scope.resetError = function () {
                $scope.error = false;
                $scope.errorMessage = '';
            };

            $scope.setError = function (message) {
                $scope.error = true;
                $scope.errorMessage = message;
            };

            $scope.token = usernamePasswordToken;
            $scope.queryUsers();
            $scope.resetError();
        }]);

/**
 *    UserEditController
 */
appUserManage.controller('UserEditController', ['UserService', '$scope', '$location', '$routeParams', function (UserService, $scope, $location, $routeParams) {

    $scope.createUser = function (user) {
        UserService.createUser(user, $scope);
    };

    $scope.updateUser = function (user) {
        UserService.updateUser(user, $scope);
    };

    $scope.gotoUsersView = function () {
        $location.path('/usermanage');
    };

    $scope.resetUserForm = function () {
        $scope.user = {};
        $scope.resetError();
    };

    $scope.resetError = function () {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function (message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    var userID = $routeParams.id;
    if (userID == undefined) {
        $scope.editMode = false;
        $scope.brand = "创建";
        $scope.user = {};
    }
    else {
        $scope.editMode = true;
        $scope.brand = "编辑";
        $scope.user = UserService.getUserById(userID, $scope);
    }
    $scope.resetError();
}]);
