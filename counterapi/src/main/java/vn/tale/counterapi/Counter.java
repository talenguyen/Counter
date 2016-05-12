package vn.tale.counterapi;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Giang Nguyen at Tiki on 5/3/16.
 */
public class Counter {

  public static final int TYPE_COUNT_UP = 1;
  public static final int TYPE_COUNT_DOWN = 2;
  private final int interval;
  private final int type;

  public Counter(int interval, int type) {
    this.interval = interval;
    this.type = type;
  }

  public Observable<Integer> counterStream(final Countable countable) {

    final int maxValue = countable.value();
    return Observable.interval(interval, TimeUnit.MILLISECONDS, Schedulers.immediate())
        .map(new Func1<Long, Integer>() {
          @Override public Integer call(Long value) {
            if (type == TYPE_COUNT_DOWN) {
              return maxValue - value.intValue();
            } else {
              return value.intValue() + 1;
            }
          }
        })
        .takeUntil(new Func1<Integer, Boolean>() {
          @Override public Boolean call(Integer integer) {
            if (type == TYPE_COUNT_DOWN) {
              return integer == 1;
            } else {
              return integer >= maxValue;
            }
          }
        });
  }
}
