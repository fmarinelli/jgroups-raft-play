package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.domain.messages.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.messages.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.messages.VoteResponse;

public class StatusDelegator<V> implements Status<V> {

  private Status<V> delegate;

  public StatusDelegator(Status<V> delegate) {
    this.delegate = delegate;
  }

  @Override
  public void appendEntries(AppendLogEntriesRequest<V> request) {
    delegate.appendEntries(request);
  }

  @Override
  public void heartbeat(HeartbeatRequest<V> request) {
    delegate.heartbeat(request);
  }

  @Override
  public void voteResponse(VoteResponse response) {
    delegate.voteResponse(response);
  }

  public void changeStatus(Status<V> status) {
    this.delegate = status;
  }
}
