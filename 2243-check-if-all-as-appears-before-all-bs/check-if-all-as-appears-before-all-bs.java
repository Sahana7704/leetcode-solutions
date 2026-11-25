class Solution {
    public boolean checkString(String s) {
        int n = s.length();
        int lastOccA = -1;
        int firstOccB = -1;
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            if(ch == 'a')
                lastOccA = i+1;
            else if(firstOccB == -1 && ch == 'b')
                firstOccB = i+1;
        }
        if(firstOccB == -1 || lastOccA == -1)
            return true;
        return firstOccB > lastOccA;
    }
}