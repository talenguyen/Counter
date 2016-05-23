package vn.tale.counter.ui.component.radio;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import vn.tale.counter.R;

public class TextRadioItem implements RadioItem {

    private TextView textView;
    private final int defaultColor;
    private final int selectedColor;

    public TextRadioItem(TextView textView) {
      this.textView = textView;
      final Context context = textView.getContext();
      defaultColor = ContextCompat.getColor(context, R.color.black);
      selectedColor = ContextCompat.getColor(context, R.color.colorAccent);
    }

    public TextView getTextView() {
      return textView;
    }

    @Override public void setSelect(boolean select) {
      final int color;
      if (select) {
        color = selectedColor;
      } else {
        color = defaultColor;
      }
      textView.setTextColor(color);
    }
  }