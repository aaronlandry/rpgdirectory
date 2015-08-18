angular.module('gameApp.services',[]).factory('Game',function($resource){
    return $resource('http://localhost:7001/rha/Games/:id',{id:'@_id'},{
        update: {
            method: 'PUT'
        }
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    };
});