package it.redhat.algorithms.raft.transport;

import it.redhat.algorithms.raft.domain.HeartbeatRequest;
import it.redhat.algorithms.raft.status.Status;
import it.redhat.algorithms.raft.support.Handler;

public class HearbeatMessageHandler<V> implements Handler<HeartbeatRequest<V>> {

  private final Status<V> status;

  public HearbeatMessageHandler(Status<V> status) {
    this.status = status;
  }

  @Override
  public void apply(HeartbeatRequest<V> request) {
    status.heartbeat(request);
  }

  @Override
  public boolean support(Class clazz) {
    return HeartbeatRequest.class.isAssignableFrom(clazz);
  }
}
