package it.redhat.algorithms.raft.domain.messages;

import it.redhat.algorithms.raft.domain.Entry;

public class AppendLogEntriesRequest<V> {

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

  //log entries to store (empty for heartbeat; may send more than one for efficiency)
  private Entry<V>[] entries;


  public AppendLogEntriesRequest(long leaderId, long term, long leaderCommit, long prevLogTerm, long prevLogIndex, Entry<V>[] entries) {
    this.leaderId = leaderId;
    this.term = term;
    this.leaderCommit = leaderCommit;
    this.prevLogTerm = prevLogTerm;
    this.prevLogIndex = prevLogIndex;
    this.entries = entries;
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

  public Entry<V>[] getEntries() {
    return entries;
  }
}
