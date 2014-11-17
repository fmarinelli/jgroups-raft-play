package it.redhat.algorithms.raft.domain.timer;

public interface Timeout {

  /**
   * Cancel the previous timer and restart the timeout counter.
   */
  void reset();

  /**
   * Cancel the previous timer.
   */
  void stop();
}
