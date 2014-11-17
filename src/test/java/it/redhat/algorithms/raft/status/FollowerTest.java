package it.redhat.algorithms.raft.status;

import it.redhat.algorithms.raft.Raft;
import it.redhat.algorithms.raft.services.Timer;
import it.redhat.algorithms.raft.support.Callback;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class FollowerTest {

  @Test
  public void testFollowerTimeout() {
    Raft<Long> raft = mock(Raft.class);
    Timer timer= mock(Timer.class);
    ArgumentCaptor<Callback> argument = ArgumentCaptor.<Callback>forClass(Callback.class);

    Follower<Long> follower = new Follower<Long>(raft, timer);

    verify(timer, times(1)).timeout(argument.capture());
    reset(timer);
    //Timeout happens
    argument.getValue().apply(null);
    verify(raft, times(1)).changeStatus(isA(Candidate.class));
    verify(raft, times(1)).startElection();
    verify(timer, times(1)).timeout(any(Callback.class));
  }
}
