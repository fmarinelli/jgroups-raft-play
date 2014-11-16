package it.redhat.algorithms.raft.support;

public interface Handler<T> {

  void apply(T request);

  boolean support(Class value);
}
