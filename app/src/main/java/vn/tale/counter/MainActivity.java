package vn.tale.counter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tale.counter.ui.component.radio.RadioGroupController;
import vn.tale.counter.ui.component.radio.RadioItem;
import vn.tale.counter.widget.numberkeyboardlayout.NumberKeyboardLayout;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

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
    onTapSecondView();
  }

  private void addTime(String minutes, String seconds) {
    Log.d(TAG, "addTime() called with: " + "minutes = [" + minutes + "], seconds = [" + seconds + "]");
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

  @OnClick(R.id.tvMinutes) public void onTapMinuteView() {
    radioGroupController.setSelection(0);
    vNumberKeyboardLayout.setupCta(getString(R.string.next), new View.OnClickListener() {
      @Override public void onClick(View v) {
        onTapSecondView();
      }
    });
  }

  @OnClick(R.id.tvSeconds) public void onTapSecondView() {
    radioGroupController.setSelection(1);
    vNumberKeyboardLayout.setupCta(getString(R.string.ok), new View.OnClickListener() {
      @Override public void onClick(View v) {
        final String minutes = tvMinutes.getText().toString();
        final String seconds = tvSeconds.getText().toString();
        addTime(minutes, seconds);
      }
    });
  }

}
