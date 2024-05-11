package test;


import java.util.*;

public class LRU implements CacheReplacementPolicy{
    List<String> cache;
    public LRU(){
        cache = new ArrayList<>();
    }
    @Override
    public void add(String word) {
        if(cache.contains(word)){
            cache.remove(word);
        }
        cache.add(word);
    }

    @Override
    public String remove() {
        return cache.remove(0);
    }
}
