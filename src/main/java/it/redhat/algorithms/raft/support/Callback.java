package it.redhat.algorithms.raft.support;

public interface Callback<T> {

  void apply(T t);
}
