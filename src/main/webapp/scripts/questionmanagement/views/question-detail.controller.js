/* global html_beautify */
/* global _ */
/* @Author Daniel Katzberg */
'use strict';

angular.module('metadatamanagementApp')
  .controller('QuestionDetailController',
    function(entity, $state, ToolbarHeaderService, MessageBus,
      SimpleMessageToastService, QuestionSearchService, CleanJSObjectService,
      PageTitleService, $rootScope, Principal, SearchResultNavigatorService,
      QuestionImageMetadataResource, $mdMenu, $timeout, $stateParams,
      OutdatedVersionNotifier, blockUI) {
      blockUI.start();

      SearchResultNavigatorService
        .setSearchIndex($stateParams['search-result-index']);

      SearchResultNavigatorService.registerCurrentSearchResult();
      var ctrl = this;
      ctrl.isAuthenticated = Principal.isAuthenticated;
      ctrl.hasAuthority = Principal.hasAuthority;
      ctrl.searchResultIndex = SearchResultNavigatorService.getSearchIndex();
      this.representationCodeToggleFlag = true;
      ctrl.predecessors = [];
      ctrl.successors = [];
      ctrl.counts = {};
      ctrl.currentImageIndex = 0;
      ctrl.currentImageLanguage = '';
      ctrl.imageLanguages = [];
      ctrl.imagesGroupedByLanguage = {};

      ctrl.enableJsonView = Principal
        .hasAnyAuthority(['ROLE_PUBLISHER', 'ROLE_ADMIN']);

      ctrl.jsonExcludes = [
        'nestedInstrument',
        'nestedStudy',
        'nestedSurveys',
        'nestedVariables',
        'nestedDataSets',
        'nestedRelatedPublications',
        'nestedConcepts'
      ];

      entity.promise.then(function(result) {
        var fetchFn = QuestionSearchService.findShadowByIdAndVersion
          .bind(null, result.masterId);
        OutdatedVersionNotifier.checkVersionAndNotify(result, fetchFn);

        var title = {
          questionNumber: result.number,
          questionId: result.id
        };
        if (_.isObject(result.instrument)) {
          title.instrumentDescription = result.instrument.
          description[$rootScope.currentLanguage];
        }
        if (!Principal.isAuthenticated()) {
          MessageBus.set('onDataPackageChange',
            {
              masterId: result.study.masterId,
              version: result.release.version
            });
        }
        PageTitleService.
          setPageTitle('question-management.detail.title', title);
        ToolbarHeaderService.updateToolbarHeader({
          'stateName': $state.current.name,
          'id': result.id,
          'questionNumber': result.number,
          'instrumentNumber': result.instrumentNumber,
          'instrumentId': result.instrumentId,
          'instrumentIsPresent': !CleanJSObjectService
                                    .isNullOrEmpty(result.instrument),
          'surveys': result.surveys,
          'studyId': result.studyId,
          'studyIsPresent': !CleanJSObjectService.isNullOrEmpty(result.study),
          'projectId': result.dataAcquisitionProjectId,
          'version': result.shadow ? _.get(result, 'release.version') : null
        });
        if (result.dataSets) {
          ctrl.accessWays = [];
          result.dataSets.forEach(function(dataSet) {
            ctrl.accessWays = _.union(dataSet.accessWays, ctrl.accessWays);
          });
        }
        if (result.release || Principal
            .hasAnyAuthority(['ROLE_PUBLISHER', 'ROLE_DATA_PROVIDER'])) {
          ctrl.question = result;
          QuestionSearchService.findAllPredeccessors(ctrl.question.id, ['id',
            'instrumentNumber', 'questionText', 'type', 'masterId', 'shadow',
            'number', 'dataAcquisitionProjectId', 'instrument.description',
            'release'],
            0, 100)
          .then(function(predecessors) {
            if (!CleanJSObjectService.isNullOrEmpty(predecessors)) {
              ctrl.predecessors = predecessors.hits.hits;
            }
          });
          if (ctrl.question.successors) {
            QuestionSearchService.findAllSuccessors(ctrl.question.successors,
              ['id', 'instrumentNumber', 'questionText', 'type',
              'number', 'dataAcquisitionProjectId', 'masterId', 'shadow',
              'instrument.description', 'release'], 0, 100)
            .then(function(successors) {
              ctrl.successors = successors.hits.hits;
            });
          }
          QuestionImageMetadataResource.findByQuestionId({
            id: ctrl.question.id
          }).$promise.then(
            function(images) {
              if (images.length > 0) {
                images.forEach(function(metadata) {
                  if (ctrl.imageLanguages.indexOf(metadata.language) === -1) {
                    ctrl.imageLanguages.push(metadata.language);
                    ctrl.imagesGroupedByLanguage[metadata.language] = [];
                  }
                  ctrl.imagesGroupedByLanguage[metadata.language]
                    .push(metadata);
                });
                if (ctrl.imageLanguages
                  .indexOf($rootScope.currentLanguage) !== -1) {
                  var index =
                    ctrl.imageLanguages.indexOf($rootScope.currentLanguage);
                  ctrl.currentImageLanguage =
                    ctrl.imageLanguages[index];
                } else {
                  ctrl.currentImageLanguage = ctrl.imageLanguages[0];
                }
              }
            });
          if (ctrl.question.technicalRepresentation) {
            //default value is no beautify
            ctrl.technicalRepresentationBeauty =
            ctrl.question.technicalRepresentation.source;

            //beautify xml, html, xhtml files.
            if (ctrl.question.technicalRepresentation.language === 'xml' ||
            ctrl.question.technicalRepresentation.language === 'xhtml' ||
            ctrl.question.technicalRepresentation.language === 'html') {
              html_beautify(ctrl.technicalRepresentationBeauty); //jscs:ignore
            }
          }
          ctrl.study = result.study;
          ctrl.counts.surveysCount = result.surveys.length;
          if (ctrl.counts.surveysCount === 1) {
            ctrl.survey = result.surveys[0];
          }
          ctrl.counts.variablesCount = result.variables.length;
          if (ctrl.counts.variablesCount === 1) {
            ctrl.variable = result.variables[0];
          }
          ctrl.instrument = result.instrument;
          ctrl.counts.instrumentsCount = result.instrument.length;

          ctrl.counts.publicationsCount = result.relatedPublications.length;
          if (ctrl.counts.publicationsCount === 1) {
            ctrl.relatedPublication = result.relatedPublications[0];
          }
          ctrl.counts.conceptsCount = result.concepts.length;
          if (ctrl.counts.conceptsCount === 1) {
            ctrl.concept = result.concepts[0];
          }
        } else {
          SimpleMessageToastService.openAlertMessageToast(
          'question-management.detail.not-released-toast', {id: result.id}
          );
        }
      }).finally(blockUI.stop);

      ctrl.changeCurrentImageLanguage = function(language) {
        if (ctrl.imageLanguages.indexOf(language) !== -1) {
          ctrl.currentImageLanguage = language;
          ctrl.currentImageIndex = 0;
          $timeout($mdMenu.hide);
        }
      };

      ctrl.openSuccessCopyToClipboardToast = function(message) {
        SimpleMessageToastService.openSimpleMessageToast(message, []);
      };

      ctrl.toggleRepresentationCode = function() {
        ctrl.representationCodeToggleFlag = !ctrl.representationCodeToggleFlag;
      };

    });
