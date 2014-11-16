package it.redhat.algorithms.raft.services;

import it.redhat.algorithms.raft.domain.Entry;

public interface Persistence<V> {

  void save(Entry<V> entry);

  void batch(Entry<V>[] entries);

  Entry<V> last();

  boolean checkIndex(long index, long term);

  boolean containIndex(long index);

  void deleteFromIndex(long index);
}
