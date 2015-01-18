// https://oj.leetcode.com/problems/lru-cache/
//

import java.util.HashMap;

public class LRUCache {

  private Entry first;
  private Entry last;
  private final int MAX_CACHE_SIZE;
  private HashMap<Integer, Entry> hashMap;

  public LRUCache(int capacity) {
    MAX_CACHE_SIZE = capacity;
    hashMap = new HashMap<Integer, Entry>();
  }

  public int get(int key) {
    Entry entry = getEntry(key);
    if (entry == null) return -1;
    moveToFirst(entry);
    return entry.value;
  }

  public void set(int key, int value) {
    Entry entry = getEntry(key);
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


  private void moveToFirst(Entry entry) {
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

  private Entry getEntry(int key) {
    return hashMap.get(key);
  }

  class Entry {
    Entry next;
    Entry pre;
    int key;
    int value;
  }


  /*-----*/
  /*-- Test Code ---*/
  /*-----*/

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Entry entry = first;
    while (entry != null) {
      sb.append(String.format("%d:%d ", entry.key, entry.value));
      entry = entry.next;
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println();
    System.out.println("===========================LRU 链表实现===========================");
    LRUCache lru = new LRUCache(5);
    lru.set(1, 11);
    lru.set(2, 11);
    lru.set(3, 11);
    lru.set(4, 11);
    lru.set(5, 11);
    System.out.println(lru.toString());
    lru.set(6, 66);
    lru.get(2);
    lru.set(7, 7);
    lru.get(4);
    System.out.println(lru.toString());
    System.out.println();

    System.out.println();
    System.out.println("===========================LRU 链表实现===========================");
    lru = new LRUCache(1);
    lru.set(2, 1);
    lru.get(2);
    System.out.println(lru.toString());
    lru.set(3, 2);
    lru.get(2);
    lru.get(3);
    System.out.println(lru.toString());
    System.out.println();



  }
  /*-----*/
  /*-- Test Code ---*/
  /*-----*/
}
