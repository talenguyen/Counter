package vn.tale.counterapi;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

/**
 * Created by Giang Nguyen at Tiki on 5/3/16.
 */
public class CounterTest {

  @Test
  public void testCounterStreamCountDown() throws Exception {
    final TestSubscriber<Integer> testSubject = TestSubscriber.create();
    final Counter counter = new Counter(1, Counter.TYPE_COUNT_DOWN);
    counter.counterStream(new Countable() {
          @Override public int value() {
            return 5;
          }
        })
        .subscribe(testSubject);
    testSubject.assertValues(5, 4, 3, 2, 1);
    testSubject.assertCompleted();
  }
  @Test
  public void testCounterStreamCountUp() throws Exception {
    final TestSubscriber<Integer> testSubject = TestSubscriber.create();
    final Counter counter = new Counter(1, Counter.TYPE_COUNT_UP);
    counter.counterStream(new Countable() {
          @Override public int value() {
            return 5;
          }
        })
        .subscribe(testSubject);
    testSubject.assertValues(1, 2, 3, 4, 5);
    testSubject.assertCompleted();
  }
}