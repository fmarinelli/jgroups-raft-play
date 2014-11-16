package it.redhat.algorithms.raft.services;

import it.redhat.algorithms.raft.support.Callback;

public interface Timer {

  /**
   * If the timer was already started, cancel the previous timer and set a new timeout
   *
   * @param callback Callback when timeout happens.
   */
  void timeout(Callback<Void> callback);

  /**
   * If the timer was already started, cancel the previous timer and set a new timeout. Keep the previous callback.
   */
  void reset();
}
