package vn.tale.counterapi;

import java.util.List;

public class CounterManagerBuilder {
  private List<? extends Countable> countableList;
  private int interval;

  public CounterManagerBuilder countableList(List<? extends Countable> countableList) {
    this.countableList = countableList;
    return this;
  }

  public CounterManagerBuilder interval(int interval) {
    this.interval = interval;
    return this;
  }

  public CounterManager createCounterManager() {
    return new CounterManager(countableList, interval);
  }
}