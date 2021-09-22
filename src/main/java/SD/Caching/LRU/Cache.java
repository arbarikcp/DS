package SD.Caching.LRU;

import java.util.HashMap;
import java.util.Map;
//https://www.interviewcake.com/concept/java/lru-cache
public class Cache<K, V >{

  Map<K, CacheEntry<V>> _cache;
  Rank<K> rankList;
  int maxSize;

  public Cache(int maxSize) {
    this.maxSize = maxSize;
    this._cache = new HashMap<>();
    rankList = new Rank<>();
  }

  public void put(K key, V value){
    if(_cache.size() >= maxSize){
      K keyToRemove  = rankList.removeTail();
      _cache.remove(keyToRemove);
    }
    _cache.put(key, new CacheEntry(value, rankList.moveToHead(key, null)));
  }

  public void remove(K key){
    if(_cache.containsKey(key)){
      CacheEntry entry = _cache.get(key);
      rankList.removeNode(entry.rank);
      _cache.remove(key);
    }
  }

  public V get( K key){
    if(_cache.containsKey(key)){
      CacheEntry<V> entry = _cache.get(key);
      rankList.moveToHead(key,entry.rank);
      return entry.value;
    }
    return null;
  }

}

class CacheEntry<V> {
  V value;
  RankNode rank;

  public CacheEntry(V value, RankNode rank) {
    this.value = value;
    this.rank = rank;
  }
}


class RankNode<K>{
  K key;
  RankNode next;
  RankNode prev;

  public RankNode(K key) {
    this.key = key;
    this.next = null;
    this.prev = null;
  }
}

class Rank <K>{
  RankNode<K> head;
  RankNode<K> tail;

  public RankNode moveToHead( K key, RankNode node){
    if(node != null){
      removeNode(node);
      addToHead(node);
    }else{
      RankNode newNode = new RankNode(key);
      addToHead(newNode);
      return newNode;
    }
    return node;
  }

  public K removeTail(){
    K key = tail.key;
    if (tail != null) {
      RankNode prevNode = tail.prev;
      tail = prevNode;
      tail.next = null;
    }
    return key;
  }

  private void addToHead(RankNode node)  {

    if(head == null){
      head = node;
      tail = node;
    } else{
      node.next = head;
      head.prev = node;
      head = node;
    }

  }

  public void removeNode(RankNode node){

    RankNode prevNode = node.prev;
    RankNode nextNode = node.next;

    if(prevNode != null && nextNode != null){ // remove from middle
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
    } else if(prevNode == null){ // remove from head
      nextNode.prev =null;
      head = nextNode;
    }else{ // remove from tail
      prevNode.next = null;
      tail = prevNode;
    }

    if(prevNode == null && nextNode == null){ // single node
      head = null;
      tail = null;
    }
  }

  public static void main(String[] args) {
    Cache<String, String> stringCache = new Cache<>(5);
    stringCache.put("0", "abc");
    stringCache.put("1", "def");
    stringCache.put("2", "abc");
    stringCache.put("3", "abc");
    stringCache.put("4", "abc");
    stringCache.put("5", "abc");

    stringCache.get("3");
    stringCache.get("2");

    System.out.println("completed");
  }
}