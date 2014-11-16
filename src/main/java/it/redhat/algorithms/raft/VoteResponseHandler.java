package it.redhat.algorithms.raft;

import it.redhat.algorithms.raft.domain.VoteResponse;
import it.redhat.algorithms.raft.status.Status;
import it.redhat.algorithms.raft.status.StatusDelegator;
import it.redhat.algorithms.raft.support.Callback;
import it.redhat.algorithms.raft.support.Handler;

public class VoteResponseHandler implements Handler<VoteResponse, Void> {

  private final Status status;

  public VoteResponseHandler(Status status) {
    this.status = status;
  }

  @Override
  public void apply(VoteResponse message, Callback<Void> response) {
    status.voteResponse(message);
  }

  @Override
  public boolean support(Class clazz) {
    return VoteResponse.class.isAssignableFrom(clazz);
  }
}
