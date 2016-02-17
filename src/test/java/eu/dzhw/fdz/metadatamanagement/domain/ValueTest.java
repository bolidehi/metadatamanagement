/**
 * 
 */
package eu.dzhw.fdz.metadatamanagement.domain;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import eu.dzhw.fdz.metadatamanagement.domain.builders.I18nStringBuilder;
import eu.dzhw.fdz.metadatamanagement.domain.builders.ValueBuilder;

/**
 * @author Daniel Katzberg
 *
 */
public class ValueTest {

  @Test
  public void testGetter() {
    // Arrange
    Value value = new ValueBuilder().withCode("A Code")
      .withAbsoluteFrequency(123)
      .withIsAMissing(false)
      .withLabel(new I18nStringBuilder().withDe("german")
        .withEn("english")
        .build())
      .withRelativeFrequency(new BigDecimal("25.15"))
      .build();

    // Act

    // Assert
    assertThat(value.getCode(), is("A Code"));
    assertThat(value.getAbsoluteFrequency(), is(123));
    assertThat(value.getIsAMissing(), is(false));
    assertThat(value.getLabel()
      .getDe(), is("german"));
    assertThat(value.getLabel()
      .getEn(), is("english"));
    assertThat(value.getRelativeFrequency()
      .toPlainString(), is("25.15"));
  }

  @Test
  public void testToString() {
    // Arrange
    Value value = new ValueBuilder().withCode("A Code")
      .withAbsoluteFrequency(123)
      .withIsAMissing(false)
      .withLabel(new I18nStringBuilder().withDe("german")
        .withEn("english")
        .build())
      .withRelativeFrequency(new BigDecimal("25.15"))
      .build();

    // Act
    String toString = value.toString();

    // Assert
    assertThat(toString, is(
        "Value{code=A Code, label=I18nString{de='german', en='english'}, absoluteFrequency=123, relativeFrequency=25.15, isAMissing=false}"));

  }

}
