package vn.tale.counter.widget.numberkeyboardlayout;

import android.support.annotation.VisibleForTesting;

/**
 * <p>
 * Build a TextBuilder for build text base on key pressed.
 * <ul>
 * <li><b>default</b> must be 0</li>
 * <li>There is <b>no</b> 0 at first</li>
 * <li>Press del will delete the last character until 0</li>
 * </ul>
 * </p>
 */
public final class TextBuilder {

  private final StringBuffer textBuilder;
  private final String defaultValue;

  public TextBuilder(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("size must > 0");
    }
    this.textBuilder = new StringBuffer();
    for (int i = 0; i < size; i++) {
      textBuilder.append("0");
    }
    defaultValue = textBuilder.toString();
  }

  public void onKeyPressed(Key key) {
    if (key.code == NumberKeyboardLayout.KEY_CODE_DEL) {
      if (textBuilder.toString().equals(defaultValue)) {
        return;
      }
      textBuilder.deleteCharAt(textBuilder.length() - 1);
      textBuilder.insert(0, "0");
    } else {
      textBuilder.append(key.value);
      textBuilder.deleteCharAt(0);
    }
  }

  public String getText() {
    return textBuilder.toString();
  }

  public void setValue(String value) {
    if (value == null || value.isEmpty() || !isNumber(value)) {
      textBuilder.setLength(0);
      textBuilder.append(defaultValue);
    } else {
      textBuilder.setLength(0);
      textBuilder.append(value);
    }
  }

  @VisibleForTesting boolean isNumber(String text) {
    return text.matches("[0-9]+");
  }
}