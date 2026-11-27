class Solution {
    private int r,c;

    private void replaceChar(char[][] board,int i,int j,char rep){
        board[i][j] = rep;
        return;
    }
    private void dfs(char[][] board,int i,int j){
        if(i<0||j<0||i>=r||j>=c||board[i][j]!='O'){
            return;
        }
        replaceChar(board,i,j,'#');
        dfs(board,i,j-1);
        dfs(board,i,j+1);
        dfs(board,i-1,j);
        dfs(board,i+1,j);
        
    }
    public void solve(char[][] board) {
        r = board.length;
        c = board[0].length;

        //checking boundary regions mark them as '#'
        for(int i=0;i<r;i++){
            dfs(board,i,0);
            dfs(board,i,c-1);
        }

        for(int j=0;j<c;j++){
            dfs(board,0,j);
            dfs(board,r-1,j);
        }

        //change all the fields other than '#' to 'X'
        replaceBoard(board,'O','X');

        //replace all '#' to 'O'
        replaceBoard(board,'#','O');

        return;
    }

    private void replaceBoard(char[][] board,char comp,char rep){
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(board[i][j]==comp)    replaceChar(board,i,j,rep);
            }
        }
    }
}