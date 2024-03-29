package it.redhat.algorithms.raft;

import it.redhat.algorithms.raft.domain.messages.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.messages.AppendLogEntriesResponse;
import it.redhat.algorithms.raft.domain.messages.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.messages.VoteRequest;
import it.redhat.algorithms.raft.services.Persistence;
import it.redhat.algorithms.raft.services.Timer;
import it.redhat.algorithms.raft.services.Transport;
import it.redhat.algorithms.raft.status.Follower;
import it.redhat.algorithms.raft.status.Status;
import it.redhat.algorithms.raft.status.StatusDelegator;
import it.redhat.algorithms.raft.domain.transport.AppendEntriesHandler;
import it.redhat.algorithms.raft.domain.transport.HearbeatMessageHandler;
import it.redhat.algorithms.raft.domain.transport.VoteResponseHandler;

import java.util.Set;

public class Raft<V> {

  private long id;
  private long term = 1;
  private long quorum;

  private Persistence<V> persistence;
  private Transport<V> transport;
  private Timer timer;

  private StatusDelegator<V> status;

  public Raft(long quorum, Persistence<V> persistence, Transport<V> transport, Timer timer) {
    this.quorum = quorum;

    this.persistence = persistence;
    this.transport = transport;
    this.timer = timer;

    this.status = new StatusDelegator<V>(new Follower<V>(this, timer));

    this.transport.onMessage(new AppendEntriesHandler<V>(status));
    this.transport.onMessage(new HearbeatMessageHandler<V>(status));
    this.transport.onMessage(new VoteResponseHandler(status));
  }

  public void changeStatus(Status status) {
    this.status.changeStatus(status);
  }

  public long currentTerm() {
    return term;
  }

  public void startElection() {
    term++;
    transport.askForVotes(new VoteRequest(term, id, persistence.last()));
  }

  public boolean checkQuorum(Set<Long> ids) {
    return ids.size() >= quorum;
  }

  public void persist(AppendLogEntriesRequest<V> request) {
    if ((request.getTerm() < term) || (!persistence.containIndex(request.getPrevLogIndex()))) {
      transport.appendEntriesResponse(request.getLeaderId(), new AppendLogEntriesResponse<V>(term, false));
      return;
    }

    if (!persistence.checkIndex(request.getPrevLogIndex(), request.getPrevLogTerm())) {
      persistence.deleteFromIndex(request.getPrevLogIndex());
    }
    persistence.batch(request.getEntries());
  }

  public void heartbeat(HeartbeatRequest<V> request) {
    term = request.getTerm();
  }
}
