package test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

public class Dictionary {
    CacheManager lru;
    CacheManager lfu;
    BloomFilter bm;
    String[] files;
    public Dictionary(String... fileNames){
        lru = new CacheManager(400, new LRU());
        lfu = new CacheManager(100, new LFU());
        bm = new BloomFilter(256, "MD5", "SHA1");
        files = fileNames;
        for(String file : fileNames) {
            try {
                Stream<String> st = Files.lines(Paths.get(file));
                st.forEach(line -> {
                    String[] words = line.split(" ");
                    for(String word : words){
                        bm.add(word);
                    }
                });
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
    public boolean query(String w) {
        if(lru.cache.contains(w)){
            return true;
        }
        if(lfu.cache.contains(w)){
            return false;
        }
        if(bm.contains(w)){
            lru.add(w);
            return true;
        }
        return false;
    }
    public boolean challenge(String w) {
        if(IOSearcher.search(w, files)){
            lru.add(w);
            return true;
        }
        return false;
    }
}
