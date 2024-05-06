var app = angular.module('myApp', []);
app.controller('panelCtrl', function($scope) {
 $scope.panels = [
    { name: 'AAP', available: true },
    { name: 'BJP', available: true },
    { name: 'CONGRESS', available: true }
 ];

 $scope.addPanel = function() {
    $scope.panels.push({ name: $scope.panelName, available: true });
    $scope.panelName = '';
 };

 $scope.deletePanel = function(index) {
    $scope.panels.splice(index, 1);
 };
});