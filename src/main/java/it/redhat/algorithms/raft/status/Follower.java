package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.Raft;
import it.redhat.algorithms.raft.domain.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.AppendLogEntriesResponse;
import it.redhat.algorithms.raft.domain.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.VoteResponse;
import it.redhat.algorithms.raft.services.Persistence;
import it.redhat.algorithms.raft.services.Timer;
import it.redhat.algorithms.raft.support.Callback;
import it.redhat.algorithms.raft.support.Logger;

public class Follower<V> implements Status<V> {

  private final static Logger log = Logger.getLogger(Follower.class);

  private final Raft<V> raft;
  private final Persistence<V> persistence;
  private final Timer timer;

  public Follower(Raft<V> _raft, Persistence<V> _persistence, Timer _timer) {
    raft = _raft;
    persistence = _persistence;
    timer = _timer;

    timer.timeout(new Callback<Void>() {

      @Override
      public void apply(Void aVoid) {
        raft.changeStatus(new Candidate<V>(raft, persistence, timer));
      }
    });
  }

  @Override
  public void appendEntries(AppendLogEntriesRequest<V> request, final Callback<AppendLogEntriesResponse<V>> response) {
    timer.reset();
    raft.persist(request);
  }

  @Override
  public void heartbeat(HeartbeatRequest<V> request, Callback<AppendLogEntriesResponse<V>> response) {
    timer.reset();
  }

  @Override
  public void voteResponse(VoteResponse response) {
    log.warning("Received vote response in state Follower [" + response + "]");
  }
}
