package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.Raft;
import it.redhat.algorithms.raft.domain.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.VoteResponse;
import it.redhat.algorithms.raft.services.Persistence;
import it.redhat.algorithms.raft.services.Timer;
import it.redhat.algorithms.raft.support.Callback;

import java.util.HashSet;
import java.util.Set;

public class Candidate<V> implements Status<V> {

  private final Raft<V> raft;
  private final Persistence<V> persistence;
  private final Timer timer;

  private final Set<Long> responses;

  public Candidate(Raft<V> _raft, Persistence<V> _persistence, Timer _timer) {
    responses = new HashSet<>();
    raft = _raft;
    persistence = _persistence;
    timer = _timer;

    timer.timeout(new Callback<Void>() {

      @Override
      public void apply(Void aVoid) {
        raft.changeStatus(new Candidate<V>(raft, persistence, timer));
      }
    });

    raft.startElection();
  }

  @Override
  public void appendEntries(AppendLogEntriesRequest<V> request) {
    timer.reset();

    Follower<V> follower = new Follower<V>(raft, persistence, timer);
    raft.changeStatus(follower);
    raft.persist(request);
  }

  @Override
  public void heartbeat(HeartbeatRequest<V> request) {
    timer.reset();

    Follower<V> follower = new Follower<V>(raft, persistence, timer);
    raft.changeStatus(follower);
    raft.heartbeat(request);
  }

  @Override
  public void voteResponse(VoteResponse response) {
    if (response.isVoteGranted()) {
      responses.add(response.getId());
      if(raft.checkQuorum(responses)) {
        timer.reset();

        raft.changeStatus(new Leader(raft, persistence, timer));
      }
    }
  }
}
