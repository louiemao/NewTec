/**
 * Created by george on 15-7-3.
 */
console.log("customer.js");

var customerapp = angular.module("newapp.customer", ['ngResource']);
/**
 * 创建一个新的服务
 */
customerapp.factory('CustomerService', ['$resource', function ($resource) {

    return $resource('/mongo/customers/:id');
}]);


customerapp.controller('ShowCustomersController', ["$scope", "CustomerService", function ($scope, CustomerService) {


    $scope.loadData = function () {
        /**$http.get("/mongo/customers").success(function (response) {
            $scope.customers = response;
        });*/
        $scope.customers = CustomerService.query();


    };
    $scope.loadData();
    $scope.deleteCustomer = function (id) {
        /**$http.delete("/mongo/customer/"+id).success(function(response){
            $scope.alert={
                "title": "Holy guacamole!",
                "content": "Best check yo self, you're not looking too good.",
                "type": "info"

            };*/
        CustomerService.delete({"id": id});
        $scope.loadData();

    };
}]);
customerapp.controller('AddCustomerController', function ($scope, $location, $routeParams,CustomerService) {

    var customer_id = $routeParams.customer_id;
    if (customer_id == undefined) {
        $scope.incomplete=true;
        $scope.customer={
            firstName:"aaa",
            lastName:"bbb"

        };
        $scope.brand = "NEW";
    }
    else {
       /** $http.get("/mongo/customer/" + customer_id).success(function (response) {
            $scope.id = response.id;
            $scope.firstName = response.firstName;
            $scope.lastName = response.lastName;
            $scope.brand = "编辑";
        });*/
        $scope.customer=CustomerService.get({id:customer_id});
        $scope.brand = "编辑";
    }

    $scope.saveCustomer = function () {
        console.log("saveCustomer");
       /** $http.post('/mongo/customer', {
            id: $scope.id,
            firstName: $scope.firstName,
            lastName: $scope.lastName
        }).success(function (response) {
            if (response) {
                $location.path("/customers");
            }
        });*/
       CustomerService.save($scope.customer,function(response){

           if (response) {
               $location.path("/customers");
           }

       });
    };
    $scope.$watch('customer.firstName', function () {
        $scope.test();
    });
    $scope.$watch('customer.lastName', function () {
        $scope.test();
    });
    $scope.test = function () {
        if ($scope.customer.firstName == undefined || $scope.customer.lastName == undefined) {
            $scope.incomplete = true;
        }
        else if ($scope.customer.firstName.length > 0 && $scope.customer.lastName.length > 0) {
            $scope.incomplete = false;
        }
        else {
            $scope.incomplete = true;
        }
    };


});
