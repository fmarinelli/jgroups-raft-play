package it.redhat.algorithms.raft.domain;

public class Entry<V> {

  //index of log entry
  private long index;

  //term of log entry
  private long term;

  private V value;

  public Entry(long index, long term, V value) {
    this.index = index;
    this.term = term;
    this.value = value;
  }

  public long getIndex() {
    return index;
  }

  public long getTerm() {
    return term;
  }

  public V getValue() {
    return value;
  }
}
