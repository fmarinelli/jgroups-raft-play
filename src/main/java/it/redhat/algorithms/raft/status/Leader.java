package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.Raft;
import it.redhat.algorithms.raft.domain.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.VoteResponse;
import it.redhat.algorithms.raft.services.Persistence;
import it.redhat.algorithms.raft.services.Timer;
import it.redhat.algorithms.raft.support.Logger;

public class Leader<V> implements Status<V> {

  private final static Logger log = Logger.getLogger(Leader.class);

  private final Raft<V> raft;
  private final Persistence<V> persistence;
  private final Timer timer;

  public Leader(Raft<V> raft, Persistence<V> persistence, Timer timer) {
    this.raft = raft;
    this.persistence = persistence;
    this.timer = timer;
  }

  @Override
  public void appendEntries(AppendLogEntriesRequest<V> request) {
    log.warning("Received append entries in a Leader status [" + request + "]");
    if (raft.currentTerm() < request.getTerm()) {
      raft.persist(request);
      raft.changeStatus(new Follower<V>(raft, persistence, timer));
    }
  }

  @Override
  public void heartbeat(HeartbeatRequest<V> request) {
    log.warning("Received heartbeat in a Leader status [" + request + "]");
    if (raft.currentTerm() < request.getTerm()) {
      raft.heartbeat(request);
      raft.changeStatus(new Follower<V>(raft, persistence, timer));
    }
  }

  @Override
  public void voteResponse(VoteResponse response) {
    log.warning("Received vote response in a Leader status [" + response + "]");
  }
}
