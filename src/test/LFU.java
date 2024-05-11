package test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LFU implements CacheReplacementPolicy{
    Map<String, Integer> cache;
    List<String> out;
    Integer min;
    public LFU(){
        cache = new HashMap<>();
        out = new ArrayList<>();
        min = 1;
    }

    @Override
    public void add(String word) {
        if(!cache.containsKey(word)){
            cache.put(word,0);
        }
        cache.put(word, cache.get(word) + 1);
        if(out.contains(word)){
            out.remove(word);
        }
        out.add(word);
        cache.values().forEach(num -> {
            if(min > num){
                min = num;
            }
        });
    }

    @Override
    public String remove() {
        for(int i = 0; i < out.size(); i++){
            if(cache.get(out.get(i)).equals(min)){
                cache.remove(out.get(i));
                return out.remove(i);
            }
        }
        return null;
    }
}
