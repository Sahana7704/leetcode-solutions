class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        int min = 0, max = 0;
        long ans = 0;

        if (cost1 + cost2 >= costBoth) {
            min = Math.min(need1, need2);
            ans += (long) costBoth * min;

            if (need1 > need2) {
                if (cost1 > costBoth) {
                    ans += (long) costBoth * (need1 - min);
                } else {
                    ans += (long) cost1 * (need1 - min);
                }
            } else {
                if (cost2 > costBoth) {
                    ans += (long) costBoth * (need2 - min);
                } else {
                    ans += (long) cost2 * (need2 - min);
                }
            }
        } else {
            ans += (long) cost1 * need1 + (long) cost2 * need2;
        }

        return ans;
    }
}