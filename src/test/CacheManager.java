package test;

import java.util.HashSet;
import java.util.Set;

public class CacheManager {
    Set<String> cache;
    int size;
    CacheReplacementPolicy method;
    public CacheManager(int size, CacheReplacementPolicy crp){
        cache = new HashSet<>();
        this.size = size;
        this.method = crp;
    }
    public boolean query(String word){
        return cache.contains(word);
    }
    public void add(String word){
        if(cache.size() == size){
            cache.remove(method.remove());

        }
        method.add(word);
        if(cache.contains(word)){
            cache.remove(word);
        }
        cache.add(word);
    }
}
