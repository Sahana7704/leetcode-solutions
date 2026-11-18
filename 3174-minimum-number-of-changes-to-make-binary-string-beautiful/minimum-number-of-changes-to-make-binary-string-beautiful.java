class Solution {
    public int minChanges(String s) {
        int n = s.length();
        int cnt = 0;
        for(int i = 1; i < n; i += 2) {
            int pc = s.charAt(i - 1);
            int c = s.charAt(i);
            if(c != pc) cnt++;
        }
        return cnt;
    }
}