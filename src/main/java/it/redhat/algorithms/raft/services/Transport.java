package it.redhat.algorithms.raft.services;

import it.redhat.algorithms.raft.domain.messages.AppendLogEntriesRequest;
import it.redhat.algorithms.raft.domain.messages.AppendLogEntriesResponse;
import it.redhat.algorithms.raft.domain.messages.VoteRequest;
import it.redhat.algorithms.raft.domain.messages.VoteResponse;
import it.redhat.algorithms.raft.support.Handler;

public interface Transport<V> {

  <T> void onMessage(Handler<T> message);

  void appendEntries(AppendLogEntriesRequest<V> entries);
  void appendEntriesResponse(long leaderId, AppendLogEntriesResponse<V> response);

  void askForVotes(VoteRequest request);
  void askForVotesResponse(long candidateId, VoteResponse response);
}
