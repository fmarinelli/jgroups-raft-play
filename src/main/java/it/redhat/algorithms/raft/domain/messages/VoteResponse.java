package it.redhat.algorithms.raft.domain.messages;

public class VoteResponse {

  //follower/candidate id
  private final long id;

  //currentTerm, for candidate to update itself
  private final long term;

  //true means candidate received vote
  private final boolean voteGranted;

  public VoteResponse(long id, long term, boolean voteGranted) {
    this.id = id;
    this.term = term;
    this.voteGranted = voteGranted;
  }

  public long getId() {
    return id;
  }

  public long getTerm() {
    return term;
  }

  public boolean isVoteGranted() {
    return voteGranted;
  }
}
