var quironApp = angular.module('quironMonitor', []);

quironApp.controller('MonitorCtrl', function ($scope, $http) {
  
  $scope.interfaces = [];

  function init(){
  	loadInterfaces();
  }

  function loadInterfaces(){
  	$http.get("interfaces").success( function(data, status, headers, config) {
    	console.log('found:');
    	$scope.interfaces = data;
  	}).
  	error(function(data, status, headers, config) {
    	console.log('error:');
    	console.log(status);
  	});
  }

  init();

});