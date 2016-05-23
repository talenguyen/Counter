package vn.tale.counter.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author giangnguyen. Created on 5/24/16.
 */
public class TimeBuilderTest {

  TimeBuilder timeBuilder = new TimeBuilder();

  @Test public void testAddMinutes() throws Exception {
    int seconds = timeBuilder.addMinutes(10).getSeconds();
    assertEquals(10 * 60, seconds);
  }

  @Test public void testAddMinutes2() throws Exception {
    int seconds = timeBuilder.addMinutes(1).addMinutes(2).getSeconds();
    assertEquals(3 * 60, seconds);
  }

  @Test public void testAddSeconds() throws Exception {
    final int seconds = timeBuilder.addSeconds(60).getSeconds();
    assertEquals(60, seconds);
  }

  @Test public void testAddSeconds2() throws Exception {
    final int seconds = timeBuilder.addSeconds(5).addSeconds(10).getSeconds();
    assertEquals(15, seconds);
  }

  @Test public void testAddMinutesAndSeconds() throws Exception {
    final int seconds = timeBuilder.addMinutes(12).addSeconds(30).getSeconds();
    assertEquals(12 * 60 + 30, seconds);
  }
}