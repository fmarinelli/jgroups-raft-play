package it.redhat.algorithms.raft.domain;

public class VoteRequest {

  //candidate's term
  private final long term;

  //candidate requesting vote
  private final long candidateId;

  //index of candidate's last log entry
  private final long lastLogIndex;

  //term of candidate's last log entry
  private final long lastLogTerm;

  public VoteRequest(long term, long candidateId, Entry entry) {
    this.term = term;
    this.candidateId = candidateId;
    this.lastLogIndex = entry.getIndex();
    this.lastLogTerm = entry.getTerm();
  }

  public long getTerm() {
    return term;
  }

  public long getCandidateId() {
    return candidateId;
  }

  public long getLastLogIndex() {
    return lastLogIndex;
  }

  public long getLastLogTerm() {
    return lastLogTerm;
  }
}
