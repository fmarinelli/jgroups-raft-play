package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.domain.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.VoteResponse;

public interface Status<V> {

  void appendEntries(AppendLogEntriesRequest<V> request);

  void heartbeat(HeartbeatRequest<V> request);

  void voteResponse(VoteResponse response);
}
