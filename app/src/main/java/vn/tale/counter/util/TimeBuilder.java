package vn.tale.counter.util;

/**
 * Author giangnguyen. Created on 5/24/16.
 */
public class TimeBuilder {
  private int seconds = 0;

  public TimeBuilder addMinutes(int minutes) {
    this.seconds += minutes * 60;
    return this;
  }

  public TimeBuilder addSeconds(int seconds) {
    this.seconds += seconds;
    return this;
  }

  public int getSeconds() {
    return seconds;
  }
}
