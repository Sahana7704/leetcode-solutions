class Solution {
    static boolean f(int i, int j, char[][] board, String word, int m, int n, int idx, int wordlen){
        if(idx==wordlen){
            return true;
        }
        char temp=board[i][j];
        board[i][j]='*';
        if((j+1)<n){
            if(board[i][j+1] == word.charAt(idx)){
                if(f(i, j+1, board, word, m, n, idx+1, wordlen)){
                    board[i][j]=temp;
                    return true;
                } 
            }
        }
        if((i+1)<m){
            if(board[i+1][j] == word.charAt(idx)){
                if(f(i+1, j, board, word, m, n, idx+1, wordlen)){
                    board[i][j]=temp;
                    return true;
                } 
            }
        }
        if((j-1)>=0){
            if(board[i][j-1] == word.charAt(idx)){
                if(f(i, j-1, board, word, m, n, idx+1, wordlen)){
                    board[i][j]=temp;
                    return true;
                } 
            }
        }
        if((i-1)>=0){
            if(board[i-1][j] == word.charAt(idx)){
                if(f(i-1, j, board, word, m, n, idx+1, wordlen)){
                    board[i][j]=temp;
                    return true;
                } 
            }
        }
        board[i][j]=temp;
        return false;
    }
    public boolean exist(char[][] board, String word) {
        String str="";
        int m=board.length,n=board[0].length, idx=0, wordlen=word.length();
        if(wordlen>(m*n)) return false;
        int[] boardFreq = new int[128];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boardFreq[board[i][j]]++;
            }
        }

        int[] wordFreq = new int[128];
        for (int i = 0; i < wordlen; i++) {
            char ch = word.charAt(i);
            wordFreq[ch]++;
            if (wordFreq[ch] > boardFreq[ch]) {
                return false;
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==word.charAt(idx)){
                    if(word.length()==1) return true;
                    if(f(i, j, board, word, m, n, idx+1, wordlen)) return true;
                }
            }
        }
        return false;
    }
}