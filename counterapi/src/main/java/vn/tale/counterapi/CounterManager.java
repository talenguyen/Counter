package vn.tale.counterapi;

import android.support.annotation.NonNull;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Author giangnguyen. Created on 5/12/16.
 */
public class CounterManager {

  private final List<? extends Countable> countableList;
  private final int interval;
  private int repeatCount;
  private int stepCount;
  private Subscription counterSubscription;
  private ThreadScheduler threadScheduler;

  public CounterManager(@NonNull List<? extends Countable> countableList, int interval, int repeatCount, ThreadScheduler threadScheduler) {
    this.countableList = countableList;
    this.interval = interval;
    this.repeatCount = repeatCount;
    this.threadScheduler = threadScheduler;
  }

  /**
   * Start to count
   * @param countAction called every <i>interval</i> milliseconds
   * @param countableCompletedAction called on every time completed count the {@link Countable}.
   * @param stepCountAction called on every time completed count the {@link List} of {@link Countable}
   */
  public void start(final Action1<Integer> countAction, final Action0 countableCompletedAction,
      final Action1<Integer> stepCountAction) {
    stepCount = 0;
    final Observable<Countable> countableStream =
        Observable.from(countableList).doOnCompleted(new Action0() {
          @Override public void call() {
            stepCount++;
            stepCountAction.call(stepCount);
          }
        });
    final Observable<Countable> repeatCountableStream;
    if (repeatCount == 0) {
      repeatCountableStream = countableStream.repeat();
    } else {
      repeatCountableStream = countableStream.repeat(repeatCount);
    }
    counterSubscription =
        repeatCountableStream.concatMap(new Func1<Countable, Observable<Integer>>() {
          @Override public Observable<Integer> call(Countable countable) {
            return new Counter(threadScheduler, interval, Counter.TYPE_COUNT_UP).counterStream(countable)
                .doOnCompleted(countableCompletedAction);
          }
        }).subscribe(new Action1<Integer>() {
          @Override public void call(Integer integer) {
            countAction.call(integer);
          }
        });
  }

  public void stop() {
    if (counterSubscription != null) {
      counterSubscription.unsubscribe();
    }
  }

  public static class Builder {
    private List<? extends Countable> countableList;
    private int interval;
    private int repeatCount;

    public Builder countableList(List<? extends Countable> countableList) {
      this.countableList = countableList;
      return this;
    }

    /**
     * @param interval interval each count
     */
    public Builder interval(int interval) {
      this.interval = interval;
      return this;
    }

    /**
     * @param count number of repeat. <b>0</b> for infinite
     */
    public Builder repeat(int count) {
      this.repeatCount = count;
      return this;
    }

    public CounterManager build() {
      return new CounterManager(countableList, interval, repeatCount, new ComputationThreadScheduler());
    }
  }
}
