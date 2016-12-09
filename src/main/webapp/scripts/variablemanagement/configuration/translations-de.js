'use strict';

angular.module('metadatamanagementApp').config(
  function($translateProvider) {
    var translations = {
      //jscs:disable
      'variable-management': {
        'log-messages': {
          'variable': {
            'saved': 'Variable mit FDZ-ID {{ id }} erfolgreich gespeichert!',
            'not-saved': 'Variable mit FDZ-ID {{ id }} wurde nicht gespeichert:',
            'missing-id': 'Die {{ index }}. Variable enthält keine FDZ-ID und wurde nicht gespeichert!',
            'upload-terminated': 'Upload von {{ total }} Variablen mit {{ errors }} Fehlern beendet!',
            'unable-to-delete': 'Die Variablen konnten nicht gelöscht werden!',
            'cancelled': 'Upload von Variablen Abgebrochen!',
            'missing-json-file': 'Keine JSON Datei zu Variable {{ id }} gefunden!',
            'generation-details-rule-success-copy-to-clipboard': 'Die Generierungsregel wurde erfolgreich in die Zwischenablage kopiert.',
            'filter-details-success-copy-to-clipboard': 'Der Filterausdruck wurde erfolgreich in die Zwischenablage kopiert.'
          }
        },
        'detail': {
          'title': '{{ label }} ({{ name }})',
          'variable': 'Variable',
          'variables': 'Variablen',
          'variable-informations': 'Informationen',
          'same-variables-in-panel': 'Panelvariablen',
          'related-objects': 'Zugehörige Objekte',
          'frequencies': 'Häufigkeiten',
          'generation-details': 'Generierungsdetails',
          'name': 'Name',
          'data-type': 'Datentyp',
          'scale-level': 'Skalenniveau',
          'question-text': 'Fragentext',
          'access-ways': 'Zugangswege',
          'annotations': 'Anmerkungen',
          'filter-description': 'Beschreibung des Filters',
          'filter-details-language': 'Filterausdruckssprache',
          'filter-details-expression': 'Filterausdruck',
          'input-filter': 'Eingangsfilter',
          'generation-details-description': 'Generierungsbeschreibung',
          'generation-details-expression-language': 'Generierungsausdruckssprache',
          'generation-details-rule': 'Generierungsregel',
          'show-complete-input-filter': {
            'true': 'Zeige gesamte Inhalt des Eingangsfilters',
            'false': 'Minimiere Eingangsfilter'
          },
          'show-complete-filter-details': {
            'true': 'Zeige gesamte Generierungsregel',
            'false': 'Minimiere Generierungsregel'
          },
          'button': {
            'aria-label': {
              'copy-to-clipboard': 'Kopiere in die Zwischenablage'
            }
          },
          'label': 'Label',
          'not-found': 'Die id {{id}} referenziert auf eine unbekannte Variable.',
          'not-found-references': 'Die id {{id}} hat keine Referenzen auf Variablen.',
          'same-in-panel': 'Ähnliche Variablen',
          'show-complete-distribution': {
            'true': 'Zeige gesamte Häufigkeitstabelle',
            'false': 'Minimiere Häufigkeitstabelle'
          },
          'statistics': {
            'graphics': 'Abbildung Häufigkeiten/Verteilung',
            'graphic-is-loading': 'wird geladen...',
            'graphic-is-not-available': 'Keine Grafische Darstellung Der Statistiken',
            'statistics': 'Deskriptive Maßzahlen',
            'all': 'Alle',
            'page': 'Seite',
            'rowsPerPage': 'Zeilen Pro Seite',
            'of': 'von',
            'value': 'Wert',
            'label': 'Label',
            'frequency': 'Häufigkeit',
            'valid-percent': 'Prozent (gültig)',
            'percent': 'Prozent',
            'firstQuartile': 'Unteres Quartil',
            'highWhisker': 'Oberer Whisker',
            'lowWhisker': 'Unterer Whisker',
            'maximum': 'Maximum',
            'median': 'Median',
            'minimum': 'Minimum',
            'thirdQuartile': 'Oberes Quartil',
            'validResponses': 'Anzahl unterschiedlicher Beobachtungen',
            'total-absolute-frequency': 'Anzahl unterschiedlicher Beobachtungen',
            'totalValidAbsoluteFrequency': 'Totale Valide Absolute Häufigkeit',
            'totalValidRelativeFrequency': 'Totale Valide Relative Häufigkeit',
            'mean-value': 'Arithmetisches Mittel',
            'skewness': 'Schiefe',
            'kurtosis': 'Wölbung',
            'standardDeviation': 'Standardabweichung',
            'mode': 'Modus',
            'deviance': 'Devianz',
            'mean-deviation': 'Durchschnittliche Abweichung'
          },
          'no-related-variables': 'Keine zugehörige Variablen.',
          'related-variables': 'Zugehörige Variablen',
          'central-tendency': 'Zentrale Tendenz',
          'dispersion': 'Streuung',
          'distribution': 'Verteilung'
        },
        'labels': {
          'part-of-data-set': 'Enthalten in Datensatz:',
          'surveyed-in': 'Erhoben in:'
        },
        'error': {
          'distribution': {
            'valid-responses': {
              'unique-value': 'Der Wert muss innerhalb der gültigen Antworten eindeutig sein.'
            },
            'total-absolute-frequency': {
              'not-null': 'Die totale, absolute Häufigkeit darf nicht leer sein!'
            },
            'total-valid-absolute-frequency': {
              'not-null': 'Die totale, valide, absolute Häufigkeit darf nicht leer sein!'
            },
            'total-valid-relative-frequency': {
              'not-null': 'Die totale, valide, relative Häufigkeit darf nicht leer sein!'
            },
            'missings': {
              'uniqueCode': 'Der Code muss innerhalb der Missings eindeutig sein.'
            }
          },
          'filter-details': {
            'expression': {
              'not-empty': 'Der Filterausdruck darf nicht leer sein!',
              'size': 'Die Maximallänge des Filterausdrucks ist 128 Zeichen.'
            },
            'description': {
              'i18n-string-size': 'Die Maximallänge der Filterbeschreibung ist 2048 Zeichen.'
            },
            'expression-language': {
              'not-empty': 'Die Filterausdruckssprache darf nicht leer sein!',
              'valid-filter-expression-language': 'Die angegebene Filterausdruckssprache ist nicht gültig.Es sind nur SpEL und Stata erlaubt.'
            }
          },
          'generation-details': {
            'not-empty-generation-details-description-or-rule': 'Es muss bei der Regelgenerierung mind. die Regel oder die Beschreibung gesetzt sein.',
            'rule-expression-language-and-rule-filled-or-empty': 'Die Generierungsregel und die dazugehörige Sprachen müssen beide gefüllt sein oder leer sein.',
            'description': {
              'i18n-string-size': 'Die Maximallänge der Beschreibung der Regelgenerierung der Variable ist 2048 Zeichen.'
            },
            'rule': {
              'size': 'Die Maximallänge der Generierungsregel ist 1048576 Zeichen.'
            },
            'rule-expression-language': {
              'valid-rule-expression-language': 'Die Sprache der Generierungsregel ist nicht gültig.Nur R und Stata sind erlaubt.'
            }
          },
          'missing': {
            'code': {
              'not-null': 'Der Code eines Missings darf nicht leer sein!'
            },
            'label': {
              'i18n-string-size': 'Die Maximallänge des Labels eines Missings ist 128 Zeichen.'
            },
            'absolute-frequency': {
              'not-null': 'Die absolute Häufigkeit eines Missings darf nicht leer sein.'
            },
            'relative-frequency': {
              'not-null': 'Die relative Häufigkeit eines Missings darf nicht leer sein.'
            }
          },
          'valid-response': {
            'label': {
              'i18n-string-size': 'Die Maximallänge des Labels eines gültigen Wertes ist 2048 Zeichen.'
            },
            'absolute-frequency': {
              'not-null': 'Die absolute Häufigkeit eines gültigen Wertes darf nicht leer sein.'
            },
            'relative-frequency': {
              'not-null': 'Die relative Häufigkeit eines gültigen Wertes darf nicht leer sein.'
            },
            'value': {
              'size': 'Die Maximallänge der Werteklasse ist 32 Zeichen.',
              'not-null': 'Der Wert eines gültigen Wertes darf nicht leer sein!'
            },
            'valid-relative-frequency': {
              'not-null': 'Die gültige, relative Häufigkeit eines gültigen Wertes darf nicht leer sein.'
            }
          },
          'variable': {
            'valid-variable-name': 'Die FDZ-ID der Variable entspricht nicht dem Muster: FDZID-Variablenname.',
            'unique-variable-name-in-project': 'Der Name der Variable ist innerhalb des Projektes schon vergeben.',
            'mandatory-scale-level-for-numeric-data-type': 'Das Skalenniveau einer numerischen Variable darf nicht leer sein, bzw.es muss leer sein bei einem String Datentyp!',
            'valid-response-value-must-be-a-number-on-numeric-data-type': 'Wenn der Datentyp einer Variable numerisch ist, müssen die Werte von gültigen Antworten numerisch sein.',
            'valid-response-value-must-be-an-iso-date-on-date-data-type': 'Wenn der Datentyp einer Variable ein Datum ist, müssen die Werte von gültigen Antworten dem ISO Standard 8601 entsprechen.',
            'id': {
              'not-empty': 'Die FDZ - ID der Variable darf nicht leer sein!',
              'size': 'Die Maximallänge der FDZ - ID ist 128 Zeichen.',
              'pattern': 'Es dürfen für die FDZ - ID nur alphanumerische Zeichen, deutsche Umlaute, ß, Minus und der Unterstrich verwendet werden.'
            },
            'data-type': {
              'not-null': 'Der Datentyp der Variable darf nicht leer sein!',
              'valid-data-type': 'Die Werte für Datentyp sind nicht gültig. Erlaubt in deutsch: string, numerisch. Erlaubt in englisch: string, numeric.'
            },
            'scaleLevel': {
              'valid-scale-level': 'Die Werte für das Skalenniveau sind nicht gültig. Erlaubt in deutsch: nominal, ordinal, kontinuierlich.Erlaubt in englisch: nominal, ordinal, continous.'
            },
            'name': {
              'not-empty': 'Der Name der Variable darf nicht leer sein!',
              'size': 'Die Maximallänge des Namens ist 32 Zeichen.',
              'pattern': 'Für den Namen dürfen nur alphanumerische Zeichen und das Minus verwendet werden.'
            },
            'label': {
              'not-null': 'Das Label der Variable darf nicht leer sein!',
              'i18n-string-size': 'Die die Maximallänge für die Label ist 128 Zeichen.',
              'i18n-string-not-empty': 'Mindestens ein deutsches oder ein englisches Label muss angegeben werden!'
            },
            'annotations': {
              'i18n-string-size': 'Die Maximallänge der Annotationen ist 2048 Zeichen.'
            },
            'access-ways': {
              'not-empty': 'Die Liste der Zugangswege einer Variable benötigt mindest ein Element und darf nicht leer sein!',
              'valid-access-ways': 'Die Liste der Zugangswege enthält ungültige Werte. Erlaubt sind nur: download-cuf, download-suf, remote-desktop-suf, onsite-suf, not-accessible.'
            },
            'survey': {
              'ids': {
                'not-empty': 'Die Variable muss mindestens einer Erhebung zugewiesen sein!'
              }
            },
            'related-question-strings': {
              'i18n-string-size': 'Die Maximallänge der zugehörigen Frage-Strings ist 2048 Zeichen.'
            },
            'data-acquisition-project': {
              'id': {
                'not-empty': 'Die FDZ - ID des Projektes darf bei der Variable nicht leer sein!'
              }
            }
          },
          'post-validation': {
            'variable-has-invalid-survey-id': 'Die Variable {{id}} referenziert auf eine unbekannte Erhebung ({{toBereferenzedId}}).',
            'variable-id-is-not-in-invalid-variables-panel': 'Die Variable {{id}} referenziert auf eine unbekannte Panelvariable ({{toBereferenzedId}}).',
            'variable-id-is-not-valid-in-related-variables': 'Die Variable {{id}} referenziert auf eine unbekannte verwandte Variable ({{toBereferenzedId}}).',
            'variable-has-invalid-question-id': 'Die Variable {{id}} referenziert auf eine unbekannte Frage ({{toBereferenzedId}}).',
            'variable-unknown': 'Die Variable {{id}}, die bei der Publikation ({{toBereferenzedId}}) verlinkt ist, konnte nicht gefunden werden.',
            'variable-has-not-a-referenced-study': 'Die Variable {{id}} referenziert auf eine Studie ({{additionalId}}), die nicht mit der Publikation ({{toBereferenzedId}}) verknüpft ist.'
          }
        }
      }
      //jscs:enable
    };
    $translateProvider.translations('de', translations);
  });
