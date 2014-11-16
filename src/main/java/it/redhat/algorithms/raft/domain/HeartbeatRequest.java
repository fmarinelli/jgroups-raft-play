package it.redhat.algorithms.raft.domain;

public class HeartbeatRequest<V> {

  //so follower can redirect clients
  private long leaderId;

  //leader?s term
  private long term;

  //leader?s commitIndex
  private long leaderCommit;

  //term of prevLogIndex entry
  private long prevLogTerm;

  //index of log entry immediately preceding new ones
  private long prevLogIndex;

  public HeartbeatRequest(long leaderId, long term, long leaderCommit, long prevLogTerm, long prevLogIndex) {
    this.leaderId = leaderId;
    this.term = term;
    this.leaderCommit = leaderCommit;
    this.prevLogTerm = prevLogTerm;
    this.prevLogIndex = prevLogIndex;
  }

  public long getLeaderId() {
    return leaderId;
  }

  public long getTerm() {
    return term;
  }

  public long getLeaderCommit() {
    return leaderCommit;
  }

  public long getPrevLogTerm() {
    return prevLogTerm;
  }

  public long getPrevLogIndex() {
    return prevLogIndex;
  }

}
