/**
 * 
 */
package eu.dzhw.fdz.metadatamanagement.data.variablemanagement.repositories;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Locale;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;

import eu.dzhw.fdz.metadatamanagement.MetaDataManagementApplicationSmokeTest;
import eu.dzhw.fdz.metadatamanagement.data.variablemanagement.repositories.VariableRepository;

/**
 * @author Daniel Katzberg
 *
 */
public class VariablesRepositoryTest extends MetaDataManagementApplicationSmokeTest {

  @Autowired
  private VariableRepository variablesRepository;

  @Test
  public void testSearchAllFields() {
    LocaleContextHolder.setLocale(Locale.ENGLISH);
    assertThat(this.variablesRepository.searchAllFields("A name", new PageRequest(0, 10))
        .getNumberOfElements(), is(10));
  }


}
