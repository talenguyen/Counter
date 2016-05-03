package vn.tale.counter.widget.numberkeyboardlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.percent.PercentFrameLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;

import vn.tale.counter.R;

/**
 * Created by Giang Nguyen at Tiki on 4/29/16.
 */
public class NumberKeyboardLayout extends PercentFrameLayout implements View.OnClickListener {

  public static final int KEY_CODE_0 = R.id.btNum0;
  public static final int KEY_CODE_1 = R.id.btNum1;
  public static final int KEY_CODE_2 = R.id.btNum2;
  public static final int KEY_CODE_3 = R.id.btNum3;
  public static final int KEY_CODE_4 = R.id.btNum4;
  public static final int KEY_CODE_5 = R.id.btNum5;
  public static final int KEY_CODE_6 = R.id.btNum6;
  public static final int KEY_CODE_7 = R.id.btNum7;
  public static final int KEY_CODE_8 = R.id.btNum8;
  public static final int KEY_CODE_9 = R.id.btNum9;
  public static final int KEY_CODE_DEL = R.id.btDel;

  public static final Key KEY_0 = new Key(KEY_CODE_0, "0");
  public static final Key KEY_1 = new Key(KEY_CODE_1, "1");
  public static final Key KEY_2 = new Key(KEY_CODE_2, "2");
  public static final Key KEY_3 = new Key(KEY_CODE_3, "3");
  public static final Key KEY_4 = new Key(KEY_CODE_4, "4");
  public static final Key KEY_5 = new Key(KEY_CODE_5, "5");
  public static final Key KEY_6 = new Key(KEY_CODE_6, "6");
  public static final Key KEY_7 = new Key(KEY_CODE_7, "7");
  public static final Key KEY_8 = new Key(KEY_CODE_8, "8");
  public static final Key KEY_9 = new Key(KEY_CODE_9, "9");
  public static final Key KEY_DEL = new Key(KEY_CODE_DEL, "DEL");

  private TextBuilder textBuilder;
  private TextView target;

  public NumberKeyboardLayout(Context context) {
    this(context, null, 0);
  }

  public NumberKeyboardLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public NumberKeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.view_keyboard, this);
    setupOnClick();
    TypedArray typedArray = null;
    final int size;
    try {
      typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberKeyboardLayout, defStyleAttr, 0);
      size = typedArray.getInt(R.styleable.NumberKeyboardLayout_size, 2);
      setSize(size);
    } finally {
      if (typedArray != null) {
        typedArray.recycle();
      }
    }
  }

  public void setSize(int size) {
    textBuilder = new TextBuilder(size);
  }

  private void setupOnClick() {
    findViewById(R.id.btNum0).setOnClickListener(this);
    findViewById(R.id.btNum1).setOnClickListener(this);
    findViewById(R.id.btNum2).setOnClickListener(this);
    findViewById(R.id.btNum3).setOnClickListener(this);
    findViewById(R.id.btNum4).setOnClickListener(this);
    findViewById(R.id.btNum5).setOnClickListener(this);
    findViewById(R.id.btNum6).setOnClickListener(this);
    findViewById(R.id.btNum7).setOnClickListener(this);
    findViewById(R.id.btNum8).setOnClickListener(this);
    findViewById(R.id.btNum9).setOnClickListener(this);
    findViewById(R.id.btDel).setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    final int id = v.getId();
    Key key;
    switch (id) {
      case R.id.btNum0:
        key = KEY_0;
        break;
      case R.id.btNum1:
        key = KEY_1;
        break;
      case R.id.btNum2:
        key = KEY_2;
        break;
      case R.id.btNum3:
        key = KEY_3;
        break;
      case R.id.btNum4:
        key = KEY_4;
        break;
      case R.id.btNum5:
        key = KEY_5;
        break;
      case R.id.btNum6:
        key = KEY_6;
        break;
      case R.id.btNum7:
        key = KEY_7;
        break;
      case R.id.btNum8:
        key = KEY_8;
        break;
      case R.id.btNum9:
        key = KEY_9;
        break;
      case R.id.btDel:
        key = KEY_DEL;
        break;
      default:
        key = null;
    }
    if (key != null) {
      onKeyPressed(key);
    }
  }

  public void setTarget(TextView textView) {
    if (textView.equals(target)) {
      return;
    }
    final String value = textView.getText().toString();
    if (TextUtils.isDigitsOnly(value)) {
      textBuilder.setValue(value);
    }
    target = textView;
    target.setText(textBuilder.getText());
  }

  private void onKeyPressed(Key key) {
    textBuilder.onKeyPressed(key);
    if (target != null) {
      target.setText(textBuilder.getText());
    }
    logKeyPressedEvent(key);
  }

  private void logKeyPressedEvent(Key key) {
    final AppEventsLogger logger = AppEventsLogger.newLogger(getContext());
    final Bundle parameters = new Bundle();
    parameters.putString("KeyValue", key.value);
    logger.logEvent("KeyBoard Pressed", parameters);
  }
}
