'use strict';

angular.module('metadatamanagementApp')
  .controller('ProjectStatusOverviewController', function($stateParams, $state,
      DataAcquisitionProjectResource) {
    var ctrl = this;
    var sort = $stateParams.sort ? $stateParams.sort : 'id';
    var dir = $stateParams.dir ? $stateParams.dir : 'asc';
    var limit = $stateParams.limit ? $stateParams.limit : 20;

    ctrl.pagination = {
      selectedPageNumber: $stateParams.page ? $stateParams.page : 1,
      totalItems: null,
      itemsPerPage: 20
    };

    var fetchData = function(page) {
      ctrl.overview = DataAcquisitionProjectResource.statusOverview({
        page: page,
        sort: sort,
        dir: dir,
        limit: limit
      });

      ctrl.overview.$promise.then(function(data) {
        ctrl.pagination.totalItems = data.page.totalElements;
        ctrl.pagination.itemsPerPage = data.page.size;
        ctrl.pagination.selectedPageNumber = data.page.number + 1;
        ctrl.pagination.itemsPerPage = data.page.size;
      });
    };

    var init = function() {
      var page = $stateParams.page ? $stateParams.page - 1 : 0;
      fetchData(page);
    };

    ctrl.onPageChange = function() {
      fetchData(ctrl.pagination.selectedPageNumber - 1);
    };

    ctrl.openProjectCockpit = function(projectId) {
      $state.go('project-cockpit', {id: projectId});
    };

    init();
  });
