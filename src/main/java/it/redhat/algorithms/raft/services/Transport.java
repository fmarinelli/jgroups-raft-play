package it.redhat.algorithms.raft.services;

import it.redhat.algorithms.raft.domain.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.AppendLogEntriesResponse;
import it.redhat.algorithms.raft.domain.VoteRequest;
import it.redhat.algorithms.raft.domain.VoteResponse;
import it.redhat.algorithms.raft.support.Handler;

public interface Transport<V> {

  <T, R> void onMessage(Handler<T, R> message);

  void appendEntries(AppendLogEntriesRequest<V> entries);
  void appendEntriesResponse(long leaderId, AppendLogEntriesResponse<V> response);

  void askForVotes(VoteRequest request);
  void askForVotesResponse(long candidateId, VoteResponse response);
}
