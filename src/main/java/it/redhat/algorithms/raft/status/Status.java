package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.domain.messages.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.messages.HeartbeatRequest;
import it.redhat.algorithms.raft.domain.messages.VoteResponse;

public interface Status<V> {

  void appendEntries(AppendLogEntriesRequest<V> request);

  void heartbeat(HeartbeatRequest<V> request);

  void voteResponse(VoteResponse response);
}
