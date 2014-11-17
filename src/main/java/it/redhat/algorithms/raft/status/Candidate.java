package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.Raft;
import it.redhat.algorithms.raft.domain.messages.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.messages.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.messages.VoteResponse;
import it.redhat.algorithms.raft.domain.timer.Timeout;
import it.redhat.algorithms.raft.services.Timer;
import it.redhat.algorithms.raft.support.Callback;

import java.util.HashSet;
import java.util.Set;

public class Candidate<V> implements Status<V> {

  private final Raft<V> raft;
  private final Timeout timeout;

  private final Set<Long> responses;
  private final Timer timer;

  public Candidate(Raft<V> _raft, Timer _timer) {
    responses = new HashSet<>();
    timer = _timer;
    raft = _raft;

    timeout = timer.timeout(new Callback<Void>() {

      @Override
      public void apply(Void aVoid) {
        raft.changeStatus(new Candidate<V>(raft, timer));
      }
    });

    raft.startElection();
  }

  @Override
  public void appendEntries(AppendLogEntriesRequest<V> request) {
    timeout.stop();

    Follower<V> follower = new Follower<V>(raft, timer);
    raft.changeStatus(follower);
    raft.persist(request);
  }

  @Override
  public void heartbeat(HeartbeatRequest<V> request) {
    timeout.stop();

    Follower<V> follower = new Follower<V>(raft, timer);
    raft.changeStatus(follower);
    raft.heartbeat(request);
  }

  @Override
  public void voteResponse(VoteResponse response) {
    if (response.isVoteGranted()) {
      responses.add(response.getId());
      if(raft.checkQuorum(responses)) {
        timeout.stop();

        raft.changeStatus(new Leader<V>(raft, timer));
      }
    }
  }
}
