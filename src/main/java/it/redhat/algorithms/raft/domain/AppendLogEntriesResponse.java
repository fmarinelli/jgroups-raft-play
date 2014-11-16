package it.redhat.algorithms.raft.domain;

public class AppendLogEntriesResponse<V> {

  //currentTerm, for leader to update itself
  private long term;

  //true if follower contained entry matching prevLogIndex and prevLogTerm
  private boolean success;

  public AppendLogEntriesResponse(long term, boolean success) {
    this.term = term;
    this.success = success;
  }
}
