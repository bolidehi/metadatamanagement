'use strict';

angular.module('metadatamanagementApp').config(
  function($translateProvider) {
    var translations = {
      //jscs:disable
      'notepad-management': {
        'home': {
          'title': 'Merkzettel',
          'empty': 'Leeren',
          'send': 'Senden',
          'user-name': 'Name',
          'user-name-required': 'Geben Sie einen Namen (erforderlich)',
          'user-name-too-short': 'Name is zu kurz.',
          'user-name-too-long': 'Name ist zu lang.',
          'user-email-required': 'Email ist Erforderlich.',
          'user-email-invalid': 'Email ist nicht valid.'
        }
      }
      //jscs:enable
    };
    $translateProvider.translations('de', translations);
  });
