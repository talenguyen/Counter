package vn.tale.counterapi;

import android.support.annotation.NonNull;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import rx.functions.Action0;
import rx.functions.Action1;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

/**
 * Author giangnguyen. Created on 5/12/16.
 */
public class CounterManagerTest {

  @Test public void testStart() throws Exception {
    final List<Countable> countableList =
        Arrays.asList(newCountable(2), newCountable(3), newCountable(4));
    final CounterManager counterManager =
        new CounterManager.Builder().countableList(countableList).repeat(2).interval(1).build();
    final Action1<Integer> count = Mockito.mock(Action1.class);
    final Action0 countableCompletedCount = Mockito.mock(Action0.class);
    final Action1<Integer> stepCount = Mockito.mock(Action1.class);
    counterManager.start(count, countableCompletedCount, stepCount);
    Mockito.verify(count, times(6)).call(1);
    Mockito.verify(count, times(6)).call(2);
    Mockito.verify(count, times(4)).call(3);
    Mockito.verify(count, times(2)).call(4);
    Mockito.verify(countableCompletedCount, times(6)).call();
    Mockito.verify(stepCount, times(1)).call(1);
    Mockito.verify(stepCount, times(1)).call(2);
    Mockito.verify(stepCount, never()).call(3);
  }

  @NonNull private Countable newCountable(final int value) {
    return new Countable() {
        @Override public int value() {
          return value;
        }
      };
  }
}