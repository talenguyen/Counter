package vn.tale.counter.ui.counter;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.Arrays;
import java.util.List;
import rx.functions.Action0;
import rx.functions.Action1;
import vn.tale.counter.R;
import vn.tale.counter.util.SimpleCountable;
import vn.tale.counterapi.Countable;
import vn.tale.counterapi.CounterManager;

/**
 * Created by Giang Nguyen at Tiki on 5/3/16.
 */
public class CounterActivity extends AppCompatActivity {
  private static final String TAG = "CounterActivity";
  @BindView(R.id.tvCountStep) TextView tvCountStep;
  @BindView(R.id.tvCounter) TextView tvCounter;
  private ToneGenerator counterToneGenerator;
  private ToneGenerator endToneGenerator;
  private CounterManager counterManager;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_counter);
    ButterKnife.bind(this);

    counterToneGenerator = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
    endToneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, 100);

    final List<SimpleCountable> countableList =
        Arrays.asList(new SimpleCountable(4), new SimpleCountable(7), new SimpleCountable(8));
    counterManager =
        new CounterManager.Builder().countableList(countableList).interval(1000).repeat(5).build();
  }

  public void start(View view) {
    counterManager.start(new Action1<Integer>() {
      @Override public void call(Integer integer) {
        beep();
        tvCounter.setText(String.valueOf(integer));
        Log.d(TAG, "call: count" + integer);
      }
    }, new Action0() {
      @Override public void call() {
        Log.d(TAG, "call: completed");
        doubleBeep();
      }
    }, new Action1<Integer>() {
      @Override public void call(Integer integer) {
        Log.d(TAG, "call: countStep " + integer);
        tvCountStep.setText(String.valueOf(integer));
        tripleBeep();
      }
    });
  }

  @Override protected void onStop() {
    super.onStop();
    counterManager.stop();
  }

  private void tripleBeep() {
    endToneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 1000);
  }

  private void doubleBeep() {
    endToneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 500);
  }

  private void beep() {
    counterToneGenerator.startTone(ToneGenerator.TONE_CDMA_PIP, 100);
  }
}
