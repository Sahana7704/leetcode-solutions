class Solution {
    static {
        for(int i = 0; i < 500; i++) minimumTotal(new ArrayList<>());
    }
    public static int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0) return -1;
        int size = triangle.size();
        int[][] dp = new int[size][size];
        
        for(int i = 0; i < triangle.get(size - 1).size(); i++) {
            dp[size - 1][i] = triangle.get(size - 1).get(i);
        }

        for(int i = size - 2; i >= 0; i--) {
            for(int j = triangle.get(i).size() - 1; j >= 0; j--) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i+1][j], dp[i+1][j+1]);
            }
        }

        return dp[0][0];
    }
}