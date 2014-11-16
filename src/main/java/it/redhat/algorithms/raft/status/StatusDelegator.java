package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.domain.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.AppendLogEntriesResponse;
import it.redhat.algorithms.raft.domain.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.VoteResponse;
import it.redhat.algorithms.raft.support.Callback;

public class StatusDelegator<V> implements Status<V> {

  private Status<V> delegate;

  public StatusDelegator(Status<V> delegate) {
    this.delegate = delegate;
  }

  @Override
  public void appendEntries(AppendLogEntriesRequest<V> request, Callback<AppendLogEntriesResponse<V>> response) {
    delegate.appendEntries(request, response);
  }

  @Override
  public void heartbeat(HeartbeatRequest<V> request, Callback<AppendLogEntriesResponse<V>> response) {
    delegate.heartbeat(request, response);
  }

  @Override
  public void voteResponse(VoteResponse response) {
    delegate.voteResponse(response);
  }

  public void changeStatus(Status<V> status) {
    this.delegate = status;
  }
}
