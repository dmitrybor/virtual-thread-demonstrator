package net.pydog.delayservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DelayServiceTest {

  private DelayService delayService;

  @BeforeEach
  void setUp() {
    delayService = new DelayService();
  }

  @ParameterizedTest
  @ValueSource(ints = {500, 1000, 1500, 2000, 2500, 3000, 3500})
  void testDelay(Integer requestedDelayValueMs) {
    final double delayComparisonRelativeErrorTolerance = 0.05;
    final Duration timeout = Duration.ofMillis(requestedDelayValueMs * 2);

    long startTime = System.currentTimeMillis();
    Integer serviceResponseValue = delayService.delay(requestedDelayValueMs).block(timeout);
    long endTime = System.currentTimeMillis();

    assertEquals(requestedDelayValueMs, serviceResponseValue);

    Integer actualDelayValueMs = (int) (endTime - startTime);
    double delta = Math.max(100.0, delayComparisonRelativeErrorTolerance * requestedDelayValueMs);
    assertEquals(requestedDelayValueMs.doubleValue(), actualDelayValueMs.doubleValue(), delta);
  }

}
