package it.redhat.algorithms.raft;

import it.redhat.algorithms.raft.domain.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.status.Status;
import it.redhat.algorithms.raft.support.Handler;

public class AppendEntriesHandler<V> implements Handler<AppendLogEntriesRequest<V>> {

  private Status<V> status;

  public AppendEntriesHandler(Status<V> status) {
    this.status = status;
  }

  @Override
  public void apply(AppendLogEntriesRequest<V> request) {
    status.appendEntries(request);
  }

  @Override
  public boolean support(Class clazz) {
    return AppendLogEntriesRequest.class.isAssignableFrom(clazz);
  }
}
