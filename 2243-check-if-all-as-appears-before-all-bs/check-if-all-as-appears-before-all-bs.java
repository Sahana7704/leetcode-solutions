class Solution {
    public boolean checkString(String s) {
        int n=s.length();
        for(int i=0;i<n;i++){
            if(i==0) continue;
            if(s.charAt(i)=='a'){
                if(s.charAt(i-1)=='b') return false;
            }
        }
        return true;
    }
}