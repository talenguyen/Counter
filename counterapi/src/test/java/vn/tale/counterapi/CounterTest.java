package vn.tale.counterapi;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

/**
 * Created by Giang Nguyen at Tiki on 5/3/16.
 */
public class CounterTest {

  private Counter counter;

  @Before
  public void setUp() throws Exception {
    counter = new Counter(1);
  }

  @Test
  public void testCounterStream() throws Exception {
    final TestSubscriber<Integer> testSubject = TestSubscriber.create();
    counter.counterStream(new Countable() {
          @Override public long value() {
            return 5;
          }
        })
        .subscribe(testSubject);
    testSubject.assertValues(5, 4, 3, 2, 1);
    testSubject.assertCompleted();
  }
}