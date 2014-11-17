package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.Raft;
import it.redhat.algorithms.raft.domain.messages.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.messages.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.messages.VoteResponse;
import it.redhat.algorithms.raft.domain.timer.Timeout;
import it.redhat.algorithms.raft.services.Timer;
import it.redhat.algorithms.raft.support.Callback;
import it.redhat.algorithms.raft.support.Logger;

public class Follower<V> implements Status<V> {

  private final static Logger log = Logger.getLogger(Follower.class);

  private final Raft<V> raft;
  private final Timeout timeout;

  public Follower(Raft<V> _raft, final Timer timer) {
    raft = _raft;
    timeout = timer.timeout(new Callback<Void>() {

      @Override
      public void apply(Void aVoid) {
        raft.changeStatus(new Candidate<V>(raft, timer));
      }
    });
  }

  @Override
  public void appendEntries(AppendLogEntriesRequest<V> request) {
    timeout.reset();
    raft.persist(request);
  }

  @Override
  public void heartbeat(HeartbeatRequest<V> request) {
    timeout.reset();
    raft.heartbeat(request);
  }

  @Override
  public void voteResponse(VoteResponse response) {
    log.warning("Received vote response in state Follower [" + response + "]");
  }
}
