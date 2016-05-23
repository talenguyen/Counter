package vn.tale.counter.ui.settime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import vn.tale.counter.R;
import vn.tale.counter.ui.component.radio.RadioGroupController;
import vn.tale.counter.ui.component.radio.RadioItem;
import vn.tale.counter.ui.component.radio.TextRadioItem;
import vn.tale.counter.util.SimpleCountable;
import vn.tale.counter.util.TimeBuilder;
import vn.tale.counter.widget.numberkeyboardlayout.NumberKeyboardLayout;
import vn.tale.counterapi.Countable;

public class SetTimeActivity extends AppCompatActivity {

  private static final String TAG = "SetTimeActivity";

  private RadioGroupController radioGroupController;

  private List<Countable> countableList = new ArrayList<>();

  @BindView(R.id.tvMinutes) TextView tvMinutes;
  @BindView(R.id.tvSeconds) TextView tvSeconds;
  @BindView(R.id.vNumberKeyboardLayout) NumberKeyboardLayout vNumberKeyboardLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setupRadioGroupController();
    onTapSecondView();
  }

  private void addTime(String minutes, String seconds) {
    Log.d(TAG,
        "addTime() called with: " + "minutes = [" + minutes + "], seconds = [" + seconds + "]");
    final int mins = Integer.parseInt(minutes);
    final int secs = Integer.parseInt(seconds);
    final int resultSeconds = new TimeBuilder().addMinutes(mins).addSeconds(secs).getSeconds();
    final Countable countable = new SimpleCountable(resultSeconds);
    countableList.add(countable);
  }

  private void setupRadioGroupController() {
    radioGroupController = new RadioGroupController();
    radioGroupController.addItem(new TextRadioItem(tvMinutes));
    radioGroupController.addItem(new TextRadioItem(tvSeconds));
    radioGroupController.setOnItemSelectedListener(
        new RadioGroupController.OnItemSelectedListener() {
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
