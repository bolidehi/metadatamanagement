'use strict';

angular.module('metadatamanagementApp')
  .config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when('/de/publications/', '/de/error');
    $urlRouterProvider.when('/en/publications/', '/en/error');
    $stateProvider
      .state('relatedPublicationDetail', {
        parent: 'site',
        url: '/publications/{id}?{version}{query}{page}' +
          '{size}',
        reloadOnSearch: false,
        data: {
          authorities: []
        },
        params: {
          'search-result-index': null
        },
        views: {
          'content@': {
            templateUrl: 'scripts/relatedpublicationmanagement/views/' +
              'relatedPublicationDetail.html.tmpl',
            controller: 'RelatedPublicationDetailController',
            controllerAs: 'ctrl'
          }
        },
        resolve: {
          entity: ['$stateParams', 'RelatedPublicationSearchService',
            function($stateParams, RelatedPublicationSearchService) {
              var excludedAttributes = ['nested*','variables', 'dataSets',
                'surveys','studies','questions', 'instruments'];
              return RelatedPublicationSearchService.findOneById(
                $stateParams.id, null, excludedAttributes);
            }
          ]
        }
      });

    $stateProvider
      .state('publicationAssignment', {
        parent: 'site',
        url: '/publications/assign',
        data: {
          authorities: ['ROLE_PUBLISHER', 'ROLE_DATA_PROVIDER']
        },
        views: {
          'content@': {
            templateUrl: 'scripts/relatedpublicationmanagement/views/' +
              'publicationAssignment.html.tmpl',
            controller: 'PublicationAssignmentController',
            controllerAs: 'ctrl'
          }
        }
      });
  });
