package test;


import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    static Board staticboard = new Board();
    Tile[][] board = new Tile[15][15];
    char[][] boardcolor = { //RED CYAN YELLOW BLUE STAR NULL
            {'R', 'N', 'N', 'C', 'N', 'N', 'N', 'R', 'N', 'N', 'N', 'C', 'N', 'N', 'R'},
            {'N', 'Y', 'N', 'N', 'N', 'B', 'N', 'N', 'N', 'B', 'N', 'N', 'N', 'Y', 'N'},
            {'N', 'N', 'Y', 'N', 'N', 'N', 'C', 'N', 'C', 'N', 'N', 'N', 'Y', 'N', 'N'},
            {'C', 'N', 'N', 'Y', 'N', 'N', 'N', 'C', 'N', 'N', 'N', 'Y', 'N', 'N', 'C'},
            {'N', 'N', 'N', 'N', 'Y', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', 'N', 'N'},
            {'N', 'B', 'N', 'N', 'N', 'B', 'N', 'N', 'N', 'B', 'N', 'N', 'N', 'B', 'N'},
            {'N', 'N', 'C', 'N', 'N', 'N', 'C', 'N', 'C', 'N', 'N', 'N', 'C', 'N', 'N'},
            {'R', 'N', 'N', 'C', 'N', 'N', 'N', 'S', 'N', 'N', 'N', 'C', 'N', 'N', 'R'},
            {'N', 'N', 'C', 'N', 'N', 'N', 'C', 'N', 'C', 'N', 'N', 'N', 'C', 'N', 'N'},
            {'N', 'B', 'N', 'N', 'N', 'B', 'N', 'N', 'N', 'B', 'N', 'N', 'N', 'B', 'N'},
            {'N', 'N', 'N', 'N', 'Y', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', 'N', 'N'},
            {'C', 'N', 'N', 'Y', 'N', 'N', 'N', 'C', 'N', 'N', 'N', 'Y', 'N', 'N', 'C'},
            {'N', 'N', 'Y', 'N', 'N', 'N', 'C', 'N', 'C', 'N', 'N', 'N', 'Y', 'N', 'N'},
            {'N', 'Y', 'N', 'N', 'N', 'B', 'N', 'N', 'N', 'B', 'N', 'N', 'N', 'Y', 'N'},
            {'R', 'N', 'N', 'C', 'N', 'N', 'N', 'R', 'N', 'N', 'N', 'C', 'N', 'N', 'R'},

    };

    static public Board getBoard() {
        return staticboard;
    }

    public Board() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                this.board[i][j] = null;
            }
        }
    }

    public Tile[][] getTiles() {
        return board.clone();
    }

    public boolean boardLegal(Word w) {
        if (!isInBoard(w)) {
            return false;
        }
        if (isFirstTime()) { // checks if this is the first word to place
            if (w.vertical) {
                int iter = 0;
                for (int i = w.row; iter < w.tiles.length; i++) {
                    if (boardcolor[i][w.col] == 'S' && w.tiles[iter] != null) {
                        if(w.tiles[iter] != null) {
                            return true;
                        }
                    }
                    iter++;
                }
                return false;
            } else {
                int iter = 0;
                for (int i = w.col; iter < w.tiles.length; i++) {
                    if (boardcolor[w.row][i] == 'S' && w.tiles[iter] != null) {
                        return true;
                    }
                    iter++;
                }
                return false;
            }
        } else {
            if (w.vertical) {
                int iter = 0;
                for (int i = w.row; iter < w.tiles.length; i++) {
                    if (board[i][w.col] != null) { //FA_M
                        if(w.tiles[iter] != null) {
                            return false;
                        }
                    }
                    iter++;
                }
                iter = 0;
                for (int i = w.row; iter < w.tiles.length; i++) {
                    if(w.row == 0){
                        if(w.col == 0){
                            if(board[i][w.col + 1] != null){
                                return true;
                            }
                        }else{
                            if(w.col != 14 && (board[i][w.col-1] != null || board[i][w.col+1] != null)){
                                return true;
                            }else if(w.col == 14 && board[i][w.col-1] != null){
                                return true;
                            }
                        }
                    }else if(w.col == 0){
                        if(i == w.row && (board[i-1][w.col] != null || board[i][w.col+1] != null)){
                            return true;
                        }else if(board[i][w.col+1] != null){
                            return true;
                        }
                    }else if(w.col == 14){
                        if(i == w.row && (board[i-1][w.col] != null || board[i][w.col - 1] != null)){
                            return true;
                        }else if(board[i][w.col - 1] != null){
                            return true;
                        }
                    }else{
                        if(i == w.row && (board[i-1][w.col] != null || board[i][w.col - 1] != null)){
                            return true;
                        }else if(board[i][w.col - 1] != null || board[i][w.col + 1] != null){
                            return true;
                        }
                    }
                    iter++;
                }
            } else {
                int iter = 0;
                for (int i = w.col; iter < w.tiles.length; i++) {
                    if (board[w.row][i] != null) {
                        if(w.tiles[iter] != null) {
                            return false;
                        }
                    }
                    iter++;
                }
                iter =0;
                for (int i = w.col; iter < w.tiles.length; i++) {
                    if(w.col == 0){
                        if(w.row == 0){
                            if(board[w.row+1][i] != null){
                                return true;
                            }
                        }else if(w.row == 14 && board[w.row-1][i] != null){
                            return true;
                        }else if(w.row != 14 && (board[w.row-1][i] != null || board[w.row+1][i] != null)){
                            return true;
                        }
                    }else if(w.row == 0){
                        if(i == w.col && (board[w.row][i-1] != null || board[w.row+1][i] != null)){
                            return true;
                        }else if(board[w.row+1][i] != null){
                            return true;
                        }
                    }else{
                        if(w.row == 14 && (board[w.row][i-1] != null || board[w.row - 1][i] != null)){
                            return true;
                        }else if(i == w.col && (board[w.row][i - 1] != null || board[w.row+1][i] != null || board[w.row-1][i] != null)){
                            return true;
                        }else if(board[w.row+1][i] != null || board[w.row-1][i] != null){
                            return true;
                        }
                    }
                    iter++;
                }
            }
        }
        return false;
    }

    public boolean isInBoard(Word w) {
        if (w.vertical) {
            if (w.col < 0 || w.col > 14 || w.row < 0 || w.row + w.tiles.length > 14) {
                return false;
            }
            return true;
        } else {
            if (w.col < 0 || w.col + w.tiles.length > 14 || w.row < 0 || w.row > 14) {
                return false;
            }
            return true;
        }
    }

    public boolean isFirstTime() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dictionaryLegal(Word w) {
        return true;
    }


    public ArrayList<Word> getWord(Word w) {
        ArrayList<Word> res = new ArrayList<Word>(1);
        res.add(w);
        if (w.vertical) {
            int iter = 0;
            for (int i = w.row; iter < w.tiles.length; i++) {
                if(w.tiles[iter] == null){
                    w.tiles[iter] = board[i][w.col];
                    iter++;
                    continue;
                }
                if (i == w.row) {
                    if (board[i - 1][w.col] != null) {
                        int temp = i - 1;
                        while (board[temp][w.col] != null) {
                            temp--;
                        }
                        temp++;
                        int t = temp;
                        Tile[] x = new Tile[15];
                        int q = 0;
                        while (board[temp][w.col] != null) {
                            x[q] = board[temp][w.col];
                            q++;
                            temp++;
                        }
                        res.add(new Word(x, t, w.col, true));
                    }
                    if (board[i][w.col - 1] != null || board[i][w.col + 1] != null) {
                        int temp = w.col;
                        while (board[i][temp] != null) {
                            temp--;
                        }
                        temp++;
                        int t = temp;
                        Tile[] x = new Tile[15];
                        int q = 0;
                        while (board[i][temp] != null) {
                            x[q] = board[i][temp];
                            q++;
                            temp++;
                        }
                        res.add(new Word(x, i, t, false));
                    }

                } else {
                    if (board[i][w.col - 1] != null || board[i][w.col + 1] != null) {
                        int temp = w.col;
                        while (board[i][temp] != null) {
                            temp--;
                        }
                        temp++;
                        int t = temp;
                        Tile[] x = new Tile[15];
                        int q = 0;
                        while (board[i][temp] != null) {
                            x[q] = board[i][temp];
                            q++;
                            temp++;
                        }
                        res.add(new Word(x, i, t, false));
                    }
                }
                iter++;
            }
        } else {
            int iter = 0;
            for (int i = w.col; iter < w.tiles.length; i++) {
                if(w.tiles[iter] == null){
                    w.tiles[iter] = board[w.row][i];
                    iter++;
                    continue;
                }
                if (i == w.col) {
                    if (board[w.row][i - 1] != null) {
                        int temp = i - 1;
                        while (board[w.row][temp] != null) {
                            temp--;
                        }
                        temp++;
                        int t = temp;
                        Tile[] x = new Tile[15];
                        int q = 0;
                        while (board[w.row][temp] != null) {
                            x[q] = board[w.row][temp];
                            q++;
                            temp++;
                        }
                        res.add(new Word(x, w.row, t, false));
                    }
                    if (board[w.row - 1][i] != null || board[w.row + 1][i] != null) {
                        int temp = w.row;
                        while (board[temp][i] != null) {
                            temp--;
                        }
                        temp++;
                        int t = temp;
                        Tile[] x = new Tile[15];
                        int q = 0;
                        while (board[temp][i] != null) {
                            x[q] = board[temp][i];
                            q++;
                            temp++;
                        }
                        res.add(new Word(x, t, i, true));
                    }
                    iter++;
                } else {
                    if (board[w.row - 1][i] != null || board[w.row + 1][i] != null) {
                        int temp = w.row;
                        while (board[temp][i] != null) {
                            temp--;
                        }
                        temp++;
                        int t = temp;
                        Tile[] x = new Tile[15];
                        int q = 0;
                        while (board[temp][i] != null) {
                            x[q] = board[temp][i];
                            q++;
                            temp++;
                        }
                        res.add(new Word(x, t, i, true));
                    }
                    iter++;
                }
            }
        }
        return res;
    }
    public int getScore(Word w){
        int res = 0;
        int red = 0;
        int yellow = 0;
        boolean star = false;
        int x = 0;
        if(w.vertical){ // R C B S N Y
            int iter = 0;
            for(int i = w.row; iter < w.tiles.length; i++) {
                if(w.tiles[iter] == null){
                    break;
                }
                switch (boardcolor[i][w.col]) {
                    case 'R':
                        res = res + w.tiles[x].score;
                        red++;
                        break;
                    case 'C':
                        res = res + w.tiles[x].score * 2;
                        break;
                    case 'Y':
                        res = res + w.tiles[x].score;
                        yellow++;
                        break;
                    case 'B':
                        res = res + w.tiles[x].score * 3;
                        break;
                    case 'S':
                        if(isFirstTime()) {
                            star = true;
                        }
                        res = res + w.tiles[x].score;
                        break;
                    case 'N':
                        res = res + w.tiles[x].score;
                        break;
                }
                x++;
                iter++;
            }
        }else{
            int iter = 0;
            for(int i = w.col; iter < w.tiles.length; i++){
                if(w.tiles[iter] == null){
                    break;
                }
                switch (boardcolor[w.row][i]){
                    case 'R':
                        res = res + w.tiles[x].score;
                        red++;
                        break;
                    case 'C':
                        res = res + w.tiles[x].score*2;
                        break;
                    case 'Y':
                        res = res + w.tiles[x].score;
                        yellow++;
                        break;
                    case 'B':
                        res = res + w.tiles[x].score*3;
                        break;
                    case 'S':
                        if(isFirstTime()) {
                            star = true;
                        }
                        res = res + w.tiles[x].score;
                        break;
                    case 'N':
                        res = res + w.tiles[x].score;
                        break;
                }
                x++;
                iter++;
            }
        }
        for(int i = 0; i < red; i++){
            res = res*3;
        }
        for(int i = 0; i < yellow; i++){
            res = res*2;
        }
        if(star){
            res = res*2;
        }
        return res;
    }
    public int tryPlaceWord(Word w){
        int res = 0;
        int flag = 0;
        if(!boardLegal(w)){
            return 0;
        }
        if(!dictionaryLegal(w)){
            return 0;
        }
        if(isFirstTime()){
            ArrayList<Word> newWords = getWord(w);
            for(int i = 0; i < newWords.toArray().length; i++){
                res += getScore(newWords.get(i));
            }
        }else{
            flag = 1;
        }
        int x = 0;
        int x2 = w.tiles.length;
        if(w.vertical){
            int iter =0;
            for(int i = w.row; iter < x2; i++){
                if(iter < w.tiles.length) {
                    if (w.tiles[iter] == null) {
                        iter++;
                        x2++;
                        x++;
                        continue;
                    }
                }
                if(x == w.tiles.length){
                    break;
                }
                board[i][w.col] = w.tiles[x];
                x++;
                iter++;
            }
        }else{
            int iter = 0;
            for(int i = w.col; iter < x2; i++){
                if(iter < w.tiles.length) {
                    if (w.tiles[iter] == null) {
                        iter++;
                        x2++;
                        x++;
                        continue;
                    }
                }
                if(x == w.tiles.length){
                    break;
                }
                board[w.row][i] = w.tiles[x];
                x++;
                iter++;
            }
        }
        if(flag == 1) {
            ArrayList<Word> newWords = getWord(w);
            for(int i = 0; i < newWords.toArray().length; i++){
                res += getScore(newWords.get(i));
            }
        }
        return res;
    }
}

