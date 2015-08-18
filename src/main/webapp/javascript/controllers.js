angular.module('gameApp.controllers',[]).controller('GameListController',function($scope,$state,popupService,$window,Game){

    $scope.games=Game.query();

    $scope.deleteGame=function(game){
        if(popupService.showPopup('Really delete this?')){
            game.$delete(function(){
                $window.location.href='';
            });
        }
    }

}).controller('GameViewController',function($scope,$stateParams,Game){

    $scope.game=Game.get({id:$stateParams.id});

}).controller('GameCreateController',function($scope,$state,$stateParams,Game){

    $scope.game=new Game();

    $scope.addGame=function(){
        $scope.game.$save(function(){
            $state.go('games');
        });
    }

}).controller('GameEditController',function($scope,$state,$stateParams,Game){

    $scope.updateGame=function(){
        $scope.game.$update(function(){
            $state.go('games');
        });
    };

    $scope.loadGame=function(){
        $scope.game=Game.get({id:$stateParams.id});
    };

    $scope.loadGame();
});