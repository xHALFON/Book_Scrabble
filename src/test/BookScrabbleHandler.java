package test;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookScrabbleHandler implements ClientHandler{
    BufferedReader bf;
    PrintWriter bftoc;
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        bf = new BufferedReader(new InputStreamReader(inFromclient));
        String clientmsg = null;
        try {
            clientmsg = bf.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        char c = clientmsg.charAt(0);
        String[] words = clientmsg.split(",");
        String[] res = new String[words.length - 1];
        for(int i = 1; i < words.length; i++){
            res[i-1] = words[i];
        }
        DictionaryManager dm = DictionaryManager.get();
        bftoc = new PrintWriter(outToClient,true);
        if(c == 'Q'){
            bftoc.println(dm.query(res));
        }else{
            bftoc.println(dm.challenge(res));
        }
    }

    @Override
    public void close() {
        try {
            bf.close();
            bftoc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
