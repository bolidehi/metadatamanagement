'use strict';

angular.module('metadatamanagementApp')
  .config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when('/de/instruments/', '/de/error');
    $urlRouterProvider.when('/en/instruments/', '/en/error');
    $stateProvider
      .state('instrumentDetail', {
        parent: 'site',
        url: '/instruments/{id}',
        data: {
          authorities: []
        },
        views: {
          'content@': {
            templateUrl: 'scripts/instrumentmanagement/views/' +
              'instrument-detail.html.tmpl',
            controller: 'InstrumentDetailController',
            controllerAs: 'ctrl'
          }
        },
        resolve: {
          entity: ['$stateParams', 'InstrumentResource',
            function($stateParams, InstrumentResource) {
              return InstrumentResource.get({
                id: $stateParams.id
              });
            }
          ]
        },
      });
  });
