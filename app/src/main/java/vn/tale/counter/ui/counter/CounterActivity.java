package vn.tale.counter.ui.counter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;
import vn.tale.counter.R;
import vn.tale.counterapi.Countable;
import vn.tale.counterapi.Counter;
import vn.tale.counterapi.CounterManagerBuilder;

/**
 * Created by Giang Nguyen at Tiki on 5/3/16.
 */
public class CounterActivity extends AppCompatActivity {
  private static final String TAG = "CounterActivity";

  static class CountableImpl implements Countable {
    private final int value;

    CountableImpl(int value) {
      this.value = value;
    }

    @Override public int value() {
      return value;
    }
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_counter);

    final Counter counter = new Counter(200, Counter.TYPE_COUNT_UP);
    counter.counterStream(new CountableImpl(7));
    final List<CountableImpl> countableList =
        Arrays.asList(new CountableImpl(4), new CountableImpl(7), new CountableImpl(8));
    //new CounterManagerBuilder().countableList(countableList).interval(200).createCounterManager()
    //    .setRepeat();
  }
}
