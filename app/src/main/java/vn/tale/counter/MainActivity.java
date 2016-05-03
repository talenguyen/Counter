package vn.tale.counter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tale.counter.ui.component.radio.RadioGroupController;
import vn.tale.counter.ui.component.radio.RadioItem;
import vn.tale.counter.widget.numberkeyboardlayout.NumberKeyboardLayout;

public class MainActivity extends AppCompatActivity {

  private RadioGroupController radioGroupController;

  static class TextRadioItem implements RadioItem {

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

  @BindView(R.id.tvMinutes) TextView tvMinutes;
  @BindView(R.id.tvSeconds) TextView tvSeconds;
  @BindView(R.id.vNumberKeyboardLayout) NumberKeyboardLayout vNumberKeyboardLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setupRadioGroupController();
  }

  private void setupRadioGroupController() {
    radioGroupController = new RadioGroupController();
    radioGroupController.addItem(new TextRadioItem(tvMinutes));
    radioGroupController.addItem(new TextRadioItem(tvSeconds));
    radioGroupController.setOnItemSelectedListener(new RadioGroupController.OnItemSelectedListener() {
      @Override public void onItemSelected(RadioItem radioItem) {
        final TextView textView = ((TextRadioItem) radioItem).getTextView();
        vNumberKeyboardLayout.setTarget(textView);
      }
    });
  }

  @OnClick({R.id.tvMinutes, R.id.tvSeconds}) public void onTextViewPressed(View view) {
    final int id = view.getId();
    int position;
    switch (id) {
      case R.id.tvSeconds:
        position = 1;
        break;
      default:
        position = 0;
    }
    radioGroupController.setSelection(position);
  }

  @Override protected void onResume() {
    super.onResume();
  }
}
