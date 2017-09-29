'use strict';
app.factory("ServerService", ["$resource", function ($resource) {
    return $resource("/mongo/servers/:id");
}]);
app.controller('ShowServersController', ["$scope", 'ServerService',
    function ($scope, ServerService) {
        $scope.loadData = function () {
            $scope.servers = ServerService.query();
        };
        $scope.loadData();
    }]);
app.controller('AddServerController', ['$scope', 'ServerService', '$location', '$stateParams',
    function ($scope, ServerService, $location, $stateParams) {
        var server_id = $stateParams.server_id;

        if (server_id == undefined) {
            $scope.server = {
                id: "",
                code: "001",
                desc: "服务用途",
                configuration: [{
                    key: "key1",
                    value: "value1",
                }],
                username: "username",
                password: "password",
                owner: "george",
                remark: ""
            };

        }
        else {
            $scope.server = ServerService.get({
                id: server_id
            });
        }

        $scope.saveServer = function () {

            console.log($scope.server);


            ServerService.save($scope.server);
            $location.path('/servers');

        };
        $scope.removeconf = function (index) {
            $scope.server.configuration.splice(index, 1);
        }
        $scope.addConfig = function () {
            $scope.server.configuration = $scope.server.configuration || [];

            $scope.server.configuration.push({
                key: "配置项",
                value: "配置描述",

            });
            //$scope.$apply();
        };
        $scope.owners = [
            {
                value: "george001",
                label: "george001"
            },
            {
                value: "george002",
                label: "george002"
            },
            {
                value: "george003",
                label: "george003"
            },
            {
                value: "george004",
                label: "george004"
            }
        ];
        $scope.server.owner = "george001";
    }]);
