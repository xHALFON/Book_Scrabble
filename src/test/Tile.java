package test;


import java.util.Objects;
import java.util.Random;

public class Tile {
    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    char letter;
    int score;

    static class Bag{
        int[] countarr = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        int[] countmax= {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        char[] chararr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int[] scores = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        Tile[] tilesarr = new Tile[26];
        private Bag() {
            for (int i = 0; i < 26; i++) {
                tilesarr[i] = new Tile(chararr[i], scores[i]);

            }
        }
        public Tile getRand(){
            if(this.size() == 0){
                return null;
            }
            int rand = 0;
            while(true) {
                Random x = new Random();
                rand = x.nextInt(26);
                if (countarr[rand] > 0) {
                    countarr[rand] -= 1;
                    break;
                }
            }
            return tilesarr[rand];
        }
        public Tile getTile(char c){
            if(this.size() == 0){
                return null;
            }
            int x = 0;
            boolean flag = false;
            for(int i = 0; i < 26; i++){
                if(chararr[i] == c){
                    x = i;
                    flag = true;
                }
            }
            if(!flag){
                return null;
            }
            if(countarr[x] == 0){
                return null;
            }else{
                countarr[x] -= 1;
            }
            return tilesarr[x];
        }
        public void put(Tile c){
            int x = 0;
            for(int i = 0; i < 26; i++){
                if (chararr[i] == c.letter) {
                    x = i;
                }
            }
            if(countarr[x] < countmax[x]) {
                countarr[x] += 1;
            }
        }
        public int size(){
            int res = 0;
            for(int i = 0; i < 26; i++){
                res += countarr[i];
            }
            return res;
        }
        public int[] getQuantities(){
            int []arr = new int[26];
            System.arraycopy(countarr, 0, arr, 0, 26);
            return arr;
        }
        private static Bag b = null;
        static public Bag getBag(){
            if(b == null){
                b = new Bag();
            }
            return b;
        }
    }
}
