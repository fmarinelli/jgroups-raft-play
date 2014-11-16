package it.redhat.algorithms.raft.support;

public interface Handler<T, R> {


  void apply(T request, Callback<R> response);

  boolean support(Class value);
}
