package it.redhat.algorithms.raft;

import it.redhat.algorithms.raft.domain.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.AppendLogEntriesResponse;
import it.redhat.algorithms.raft.status.Status;
import it.redhat.algorithms.raft.support.Callback;
import it.redhat.algorithms.raft.support.Handler;

public class AppendEntriesHandler<V> implements Handler<AppendLogEntriesRequest<V>, AppendLogEntriesResponse<V>> {

  private Status<V> status;

  public AppendEntriesHandler(Status<V> status) {
    this.status = status;
  }

  @Override
  public void apply(AppendLogEntriesRequest<V> request, Callback<AppendLogEntriesResponse<V>> response) {
    status.appendEntries(request, response);
  }

  @Override
  public boolean support(Class clazz) {
    return AppendLogEntriesRequest.class.isAssignableFrom(clazz);
  }
}
