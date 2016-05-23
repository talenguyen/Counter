package vn.tale.counter.util;

import vn.tale.counterapi.Countable;

public class SimpleCountable implements Countable {
  private final int value;

  public SimpleCountable(int value) {
    this.value = value;
  }

  @Override public int value() {
    return value;
  }
}