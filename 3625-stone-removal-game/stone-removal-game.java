class Solution {
    public boolean canAliceWin(int n) {
        int t = 0;
        int pile = 10;

        while (n >= pile) {
            n -= pile--;
            t ^= 1;
        }

        return (t & 1) == 1;
    }
}