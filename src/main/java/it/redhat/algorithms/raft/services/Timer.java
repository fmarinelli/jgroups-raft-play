package it.redhat.algorithms.raft.services;

import it.redhat.algorithms.raft.support.Callback;
import it.redhat.algorithms.raft.domain.timer.HeartbeatTimer;
import it.redhat.algorithms.raft.domain.timer.Timeout;

public interface Timer {

  /**
   * If the timer was already started, cancel the previous timer and set a new timeout
   *
   * @param callback Callback when timeout happens.
   */
  Timeout timeout(Callback<Void> callback);

  /**
   * If the timer was already started, cancel the previous timer and set a new timeout
   *
   * @param callback Callback when timeout happens.
   */
  HeartbeatTimer heartbeat(Callback<Void> callback);

}
