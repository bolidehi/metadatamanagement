'use strict';

angular.module('metadatamanagementApp').config(
  function($translateProvider) {
    var translations = {
      //jscs:disable
      'shopping-cart': {
        'title': 'Einkaufswagen',
        'detail': {
          'table-title': 'Ihre ausgewählten Datenpakete',
          'personal-details': 'Persönliche Angaben',
          'label': {
            'title': 'Studientitel',
            'access-way': 'Zugangsweg',
            'version': 'Version',
            'annotations': 'Bemerkungen',
            'product-options': 'Optionen des zugehörigen Datenpakets',
            'access-way-of-data-sets': 'Zugangsweg zu den Datensätzen',
            'version-of-data-sets': 'Version der Datensätze',
            'available-versions': 'Verfügbare Versionen',
            'available-access-ways': 'Verfügbare Zugangswege',
            'number-data-sets': 'Datensätze',
            'number-variables': 'Variablen',
            'current': 'aktuell',
            'not-current': 'nicht aktuell',
            'this-data-product': 'Dieses Datenpaket',
            'study-series': 'aus der Studienreihe "{{series}}"',
            'contains': 'enthält',
            'variables': '{variables, plural, =0{unbekannt viele Variablen} =1{eine Variable} other{{formattedVariables} Variablen}}',
            'in': 'in',
            'data-sets': '{dataSets, plural, =0{unbekannt vielen Datensätze.} =1{einem Datensatz.} other{{formattedDataSets} Datensätzen.}}',
            'study': 'Studie',
            'customer-name': 'Ihr Name',
            'customer-email': 'Ihre E-Mailadresse'
          },
          'hints': {
            'name': 'Bitte teilen Sie uns Ihren vollständigen Namen mit.',
            'email': 'Bitte teilen Sie uns Ihre E-Mailadresse mit, damit wir Sie kontaktieren können.',
            'accessWay': 'Benötigen Sie einen CUF oder SUF und wie möchten Sie mit den Daten arbeiten?',
            'version': 'Welche Version der Datensätze benötigen Sie?'
          },
          'thank-you': 'Vielen Dank für Ihr Interesse an unseren Datenpakete!',
          'order-placed-text': 'Wir haben Ihnen eine Bestätigungsmail gesendet und werden Sie in Kürze kontaktieren.',
          'empty-cart-text': 'Ihr Einkaufswagen ist aktuell leer. Sie können <a href="#!/de/search?type=studies"><strong>hier</strong></a> nach Datenpaketen suchen und diese Ihrem Einkaufswagen hinzufügen.',
          'warn-not-current-versions': 'Da Sie sich nicht für die aktuelle Version dieses Datenpakets entschieden haben, kann Ihnen dieses System keine genaue Auskunft über die Anzahl an Variablen und Datensätzen des Produktes anzeigen.',
          'explain-data-product': 'Ein Datenpaket beinhaltet immer alle Datensätze einer Studie, die für den gewählten Zugangsweg (download, on-site, remote,...) aufbereitet wurden. Sie können mehrere Datenpakete der selben Studie mit unterschiedlichen Zugangswegen beantragen.',
          'no-final-release': 'Dieses Datenpaket wurde noch nicht fertiggestellt. Sobald es fertig ist können Sie es an dieser Stelle in den Einkaufswagen legen.',
          'data-not-available': 'Dieses Datenpaket ist aktuell nicht verfügbar.',
          'study-tooltip': 'Klicken, um die Studie anzuzeigen.',
          'study-series-tooltip': 'Klicken, um alle Studien der Studienreihe anzuzeigen.',
          'data-sets-tooltip': 'Klicken, um alle Datensätze dieses Datenpaketes anzuzeigen.',
          'variables-tooltip': 'Klicken, um alle Variablen dieses Datenpaketes anzuzeigen.'
        },
        'error': {
          'customer': {
            'name': {
              'empty': 'Ihr Name darf nicht leer sein.',
              'string-size': 'Ihr Name darf nicht länger als 128 Zeichen sein.'
            },
            'email': {
              'empty': 'Ihre E-Mailadresse darf nicht leer sein.',
              'string-size': 'Ihre E-Mailadresse darf nicht länger als 128 Zeichen sein.',
              'invalid': 'Ihre E-Mailadresse ist ungültig.'
            }
          }
        },
        'toasts': {
          'study-added': 'Das Datenpaket wurde in den Einkaufswagen gelegt.',
          'study-already-in-cart': 'Das Datenpaket ist bereits im Einkaufswagen.',
          'customer-has-validation-errors-toast': 'Sie haben noch nicht alle benötigten Angaben zu Ihrer Person gemacht.',
          'error-on-saving-order': 'Beim Senden Ihrer Bestellung trat ein Fehler auf.'
        },
        'buttons': {
          'checkout': 'Beantragen',
          'add-study': 'In den Einkaufswagen',
          'add-study-tooltip': 'Klicken, um das Datenpaket mit den ausgewählten Optionen in den Einkaufswagen zu legen.',
          'open-cart': 'Zum Einkaufswagen',
          'checkout-tooltip': 'Klicken, um die Datenpakete zu bestellen.',
          'remove-all': 'Einkaufswagen leeren',
          'remove-all-tooltip': 'Klicken, um alle Datenpakete aus dem Einkaufswagen zu entfernen.',
          'delete-product-tooltip': 'Klicken, um das Datenpaket aus dem Einkaufswagen zu entfernen.',
          'open-cart-tooltip': 'Klicken, um die Inhalte des Einkaufswagens anzuzeigen.',
          'close-tooltip': 'Klicken, um die Produktauswahl zu verlassen.'
        }
      }
      //jscs:enable
    };
    $translateProvider.translations('de', translations);
  });
