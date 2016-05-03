package vn.tale.counter.widget;

import org.junit.Before;
import org.junit.Test;

import vn.tale.counter.widget.numberkeyboardlayout.NumberKeyboardLayout;
import vn.tale.counter.widget.numberkeyboardlayout.TextBuilder;

import static junit.framework.Assert.assertEquals;

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

}