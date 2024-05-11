package test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class DictionaryManager {
    Map<String,Dictionary> mp;
    public DictionaryManager(){
        mp = new HashMap<>();
    }
    public boolean query(String... files){
        String w = files[files.length - 1];
        for(int i = 0; i < files.length-1; i++){
            if(!mp.containsKey(files[i])){
                mp.put(files[i], new Dictionary(files[i]));
            }
            if(mp.get(files[i]).query(w)){
                return true;
            }
        }
        return false;
    }
    public boolean challenge(String... files){
        String w = files[files.length - 1];
        int []flag = {0};
        for(int i = 0; i < files.length-1; i++) {
            try {
                Stream<String> st = Files.lines(Paths.get(files[i]));
                st.forEach(line -> {
                    String[] words = line.split(" ");
                    for(String s : words){
                        if (s.equals(w)) {
                            flag[0] = 1;
                            break;
                        }
                    }
                });
                if(flag[0] == 1){
                    return true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    public static DictionaryManager get(){
        return new DictionaryManager();
    }
    public int getSize(){
        return mp.size();
    }
}
