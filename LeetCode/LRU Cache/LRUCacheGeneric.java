//
// LRU泛型支持的Cache
// *非线程安全
//

import java.util.HashMap;

public class LRUCacheGeneric<K, V> {

  private Entry<K, V> firstEntry;
  private Entry<K, V> lastEntry;
  private final int MAX_CACHE_SIZE;
  private HashMap<K, Entry<K, V>> hashMap;

  public LRUCacheGeneric<K, V>(int capacity) {
    MAX_CACHE_SIZE = capacity;
    hashMap = new HashMap<K, Entry<K,V>>();
  }

  public V get(K key) {
  }

  public void put(K key, V value) {
  }


  private void moveToFirst(Entry<K, V> entry) {
    if (first == entry) return;
    if (entry.pre != null) entry.pre.next = entry.next;
    if (entry.next != null) entry.next.pre = entry.pre;
    if (entry == last) last = last.pre;

    if (first == null || last == null) {
      first = last = entry;
      return;
    }

    entry.next = first;
    first.pre = entry;
    first = entry;
    entry.pre = null;
  }

  private void removeLast() {
    if (lastEntry != null) {
      last = last.pre;
      if (last.pre == null)
        first = null;
      else
        last.next = null;
    }
  }

  private Entry<K, V> getEntry(K key) {
    return hashMap.get(key);
  }

  class Entry<K, V> {
    Entry<K, V> next;
    Entry<K, V> pre;
    K key;
    V value;
  }
}
