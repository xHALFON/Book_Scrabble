package test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOSearcher {
    public static boolean search(String s, String... files) {
        Stream<String> st;
        for (String file : files) {
            int []flag = {0};
            try {
                st = Files.lines(Paths.get(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            st.forEach(line -> {
                    if(line.contains(s)){
                        flag[0] = 1;
                    }
                });
                if(flag[0] == 1){
                    return true;
                }
                st.close();
        }
        return false;
    }
}
