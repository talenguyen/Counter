package vn.tale.counterapi;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Giang Nguyen at Tiki on 5/3/16.
 */
public class Counter {

  private final int interval;

  public Counter(int interval) {
    this.interval = interval;
  }

  public Observable<Integer> counterStream(final Countable countable) {

    final long maxValue = countable.value();
    return Observable.interval(interval, TimeUnit.MILLISECONDS, Schedulers.immediate())
        .map(new Func1<Long, Integer>() {
          @Override public Integer call(Long value) {
            return (int) (value / interval) + 1;
          }
        })
        .takeUntil(new Func1<Integer, Boolean>() {
          @Override public Boolean call(Integer integer) {
            return integer >= maxValue;
          }
        });
  }
}
