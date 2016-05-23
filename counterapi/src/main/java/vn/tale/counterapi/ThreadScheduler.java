package vn.tale.counterapi;

import rx.Scheduler;

/**
 * Author giangnguyen. Created on 5/23/16.
 */
public interface ThreadScheduler {

  Scheduler subscribeOn();

  Scheduler observeOn();
}
