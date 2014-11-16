package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.Raft;
import it.redhat.algorithms.raft.domain.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.AppendLogEntriesResponse;
import it.redhat.algorithms.raft.domain.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.VoteResponse;
import it.redhat.algorithms.raft.services.Persistence;
import it.redhat.algorithms.raft.services.Timer;
import it.redhat.algorithms.raft.support.Callback;

public class Leader<V> implements Status<V> {

  private final Raft<V> raft;
  private final Persistence<V> persistence;
  private final Timer timer;

  public Leader(Raft<V> raft, Persistence<V> persistence, Timer timer) {
    this.raft = raft;
    this.persistence = persistence;
    this.timer = timer;
  }

  @Override
  public void appendEntries(AppendLogEntriesRequest<V> request, Callback<AppendLogEntriesResponse<V>> response) {
    //Log Warning
  }

  @Override
  public void heartbeat(HeartbeatRequest<V> request, Callback<AppendLogEntriesResponse<V>> response) {
    //Log Warning
  }

  @Override
  public void voteResponse(VoteResponse response) {
    //Log Warning
  }
}
