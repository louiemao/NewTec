/**
 * Created by george on 15-7-8.
 */
angular.module("newapp.user", ["ui.bootstrap"])
    .controller("LoginController", ['$scope', '$modalInstance', 'subject', 'usernamePasswordToken','$rootScope',
        function ($scope, $modalInstance, subject, usernamePasswordToken,$rootScope) {
           // $scope.islogin = false;
            $scope.token = usernamePasswordToken;
            //document.getElementById('loading').style.display="none";
            //$scope.showLoading(false);
            $scope.isLoading=false;

            $scope.ok = function () {
                //todo do login check
                //if($scope.username==$scope.password){
                //    $scope.islogin=true;
                //}
                subject.login($scope.token).then(function (data) {
                    //rootscope=$scope.$parent;
                    //$rootScope.getMenuInfos();
                    console.log(data);
                    //$scope.showLoading(false);
                    $scope.isLoading=false;
                    $modalInstance.close(data.info.authc.principal.username);
                   // $scope.islogin = true;
                }, function (error) {
                    console.log(error);
                    //$scope.showLoading(false);
                    $scope.isLoading=false;
                    //$scope.islogin = false;
                   // $modalInstance.dismiss('cancel');
                });

                //$scope.showLoading(true);
                $scope.isLoading=true;
               // $modalInstance.close($scope.islogin);
               // $modalInstance.close('ok');
            };
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };

            //$scope.showLoading =function(isLoading){
                //if(isLoading){
                //    document.getElementById('loading').style.display="";
                //}else{
                //    document.getElementById('loading').style.display="none";
                //}
            //};

        }]);