'use strict';

angular.module('metadatamanagementApp')
  .controller('ReleaseProjectDialogController', function($scope, $mdDialog,
    project, SimpleMessageToastService, DataAcquisitionProjectResource,
    DaraReleaseResource, $rootScope, CurrentProjectService,
    DataAcquisitionProjectLastReleaseResource, $state, $translate,
    DataAcquisitionProjectPostValidationService) {
    $scope.bowser = $rootScope.bowser;
    $scope.project = project;

    var i18nPrefix = 'data-acquisition-project-management.log-messages.' +
      'data-acquisition-project.';
    $scope.cancel = function() {
      $mdDialog.cancel();
    };

    DataAcquisitionProjectLastReleaseResource.get({id: project.id})
    .$promise.then(function(lastRelease) {
      if (lastRelease.version) {
        $scope.lastVersion = lastRelease.version;
        $scope.release = {};
        $scope.release.version = lastRelease.version;
      }
    });

    $scope.ok = function(release) {
      DataAcquisitionProjectPostValidationService
        .postValidate(project.id, release.version).then(function() {
          var compareForBeta = $scope.bowser
          .compareVersions(['1.0.0', release.version]);
          release.lastDate = new Date().toISOString();
          project.release = release;
          project.hasBeenReleasedBefore = true;

          if (compareForBeta === 1) {
            //BETA RELEASE
            DataAcquisitionProjectResource.save(project).$promise
            .then(function() {
                SimpleMessageToastService.openSimpleMessageToast(
                  i18nPrefix + 'released-beta-successfully', {
                    id: project.id
                  });
                CurrentProjectService.setCurrentProject(project);
                $mdDialog.hide();
                $state.forceReload();
              }).catch(function() {
                  delete project.release;
                  SimpleMessageToastService.openAlertMessageToast(
                    i18nPrefix + 'dara-released-not-successfully', {
                      id: project.id
                    });
                });
          } else {
            //REGULAR RELEASE
            DaraReleaseResource.release(project)
            .$promise.then(function() {
                DataAcquisitionProjectResource.save(project).$promise
                .then(function() {
                    SimpleMessageToastService.openSimpleMessageToast(
                      i18nPrefix + 'released-successfully', {
                        id: project.id
                      });
                    CurrentProjectService.setCurrentProject(project);
                    $mdDialog.hide();
                    $state.forceReload();
                  });
              }).catch(function() {
                  delete project.release;
                  SimpleMessageToastService.openAlertMessageToast(
                    i18nPrefix + 'dara-released-not-successfully', {
                      id: project.id
                    });
                });
          }
        }).catch(function() {
          $mdDialog.show($mdDialog.alert()
          .title($translate.instant(
            i18nPrefix + 'release-not-possible-title', {
              id: project.id
            }))
          .textContent($translate.instant(
            i18nPrefix + 'release-not-possible', {
              id: project.id
            }))
          .ariaLabel($translate.instant(
            i18nPrefix + 'release-not-possible-title', {
              id: project.id
            }))
          .ok($translate.instant('global.buttons.ok')));
        });
    };
  });
