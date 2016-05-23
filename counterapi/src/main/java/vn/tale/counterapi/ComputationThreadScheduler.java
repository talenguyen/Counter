package vn.tale.counterapi;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author giangnguyen. Created on 5/23/16.
 */
public class ComputationThreadScheduler implements ThreadScheduler {
  @Override public Scheduler subscribeOn() {
    return Schedulers.computation();
  }

  @Override public Scheduler observeOn() {
    return AndroidSchedulers.mainThread();
  }
}
