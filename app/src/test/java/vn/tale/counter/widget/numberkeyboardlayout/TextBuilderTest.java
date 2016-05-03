package vn.tale.counter.widget.numberkeyboardlayout;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Giang Nguyen at Tiki on 4/29/16.
 */
public class TextBuilderTest {

  private TextBuilder textBuilder;

  @Before
  public void setUp() throws Exception {
    textBuilder = new TextBuilder(2);
  }

  @Test
  public void testGetText_default_shouldBe00() throws Exception {
    assertEquals("00", textBuilder.getText());
  }

  @Test
  public void testGetText_shouldAppendValueAndMoveToLeft() throws Exception {
    textBuilder.onKeyPressed(NumberKeyboardLayout.KEY_1);
    assertEquals("01", textBuilder.getText());
    textBuilder.onKeyPressed(NumberKeyboardLayout.KEY_9);
    assertEquals("19", textBuilder.getText());
    textBuilder.onKeyPressed(NumberKeyboardLayout.KEY_8);
    assertEquals("98", textBuilder.getText());

  }

  @Test
  public void testGetText_pressDel_shouldMoveToRight() throws Exception {
    textBuilder.onKeyPressed(NumberKeyboardLayout.KEY_1);
    textBuilder.onKeyPressed(NumberKeyboardLayout.KEY_2);
    assertEquals("12", textBuilder.getText());
    textBuilder.onKeyPressed(NumberKeyboardLayout.KEY_DEL);
    assertEquals("01", textBuilder.getText());
    textBuilder.onKeyPressed(NumberKeyboardLayout.KEY_DEL);
    assertEquals("00", textBuilder.getText());
  }

  @Test
  public void testSetValue() throws Exception {
    textBuilder.setValue("123");
    assertEquals("123", textBuilder.getText());
  }

  @Test
  public void testSetValue_shouldBeZeroIfEmpty() throws Exception {
    textBuilder.setValue("");
    assertEquals("00", textBuilder.getText());
  }

  @Test
  public void testSetValue_shouldBeZeroIfNotDigits() throws Exception {
    textBuilder.setValue("abc");
    assertEquals("00", textBuilder.getText());
  }

  @Test
  public void testIsNumber() throws Exception {
    assertTrue(textBuilder.isNumber("1234567890"));
    assertFalse(textBuilder.isNumber("ab12345"));
    assertFalse(textBuilder.isNumber("12345ab"));
    assertFalse(textBuilder.isNumber("123z45"));
  }
}