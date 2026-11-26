// You are using a 1D DP array and optimizing the space complexity to O(c)

class Solution {
       static {
        for (int i = 0; i < 100; i++) {
            new Solution().minPathSum(new int[][] {{1, 2, 3}, {1, 2, 3}});
        }
    }
    public static int minPathSum(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[] dp = new int[c];
       

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                int left = Integer.MAX_VALUE;
                int top = Integer.MAX_VALUE;
                int cur = grid[i][j];
                if(i==0 && j==0){
                    dp[j] = cur;
                    continue;
                }
                if(j>0){
                   left =  grid[i][j] + dp[j-1];
                }
                if(i>0){
                    top = dp[j] + grid[i][j];
                }
                dp[j] = Math.min(left,top);
                
            }
        }
        return dp[c-1];
    }
}

