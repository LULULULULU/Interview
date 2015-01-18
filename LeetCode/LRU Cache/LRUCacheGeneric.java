//
// LRU泛型支持的Cache
// *非线程安全 http://www.cnblogs.com/lzrabbit/p/3734850.html#f2
//
// 其他实现: http://www.lvcy.net/?post=146
//

import java.util.HashMap;

public class LRUCacheGeneric<K, V> {

  private Entry<K, V> first;
  private Entry<K, V> last;
  private final int MAX_CACHE_SIZE;
  private HashMap<K, Entry<K, V>> hashMap;

  public LRUCacheGeneric(int capacity) {
    MAX_CACHE_SIZE = capacity;
    hashMap = new HashMap<K, Entry<K,V>>();
  }

  public V get(K key) {
    Entry<K, V> entry = getEntry(key);
    if (entry == null) return null;
    moveToFirst(entry);
    return entry.value;
  }

  public void put(K key, V value) {
    Entry<K, V> entry = getEntry(key);
    if (entry == null) {
      if (hashMap.size() >= MAX_CACHE_SIZE) {
        hashMap.remove(last.key);
        removeLast();
      }
      entry = new Entry();
      entry.key = key;
    }
    entry.value = value;
    moveToFirst(entry);
    hashMap.put(key, entry);
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
    if (last != null) {
      last = last.pre;
      if (last == null)
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Entry entry = first;
    while (entry != null) {
      sb.append(String.format("%s:%s ", entry.key, entry.value));
      entry = entry.next;
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println();
    System.out.println("===========================LRU 链表实现===========================");
    LRUCacheGeneric<Integer, String> lru = new LRUCacheGeneric(5);
    lru.put(1, "11");
    lru.put(2, "11");
    lru.put(3, "11");
    lru.put(4, "11");
    lru.put(5, "11");
    System.out.println(lru.toString());
    lru.put(6, "66");
    lru.get(2);
    lru.put(7, "77");
    lru.get(4);
    System.out.println(lru.toString());
    System.out.println();
  }
}
