class Solution {
    public int wordCount(String[] startWords, String[] targetWords) {
        int count=0;
        HashSet<String> set=new HashSet<>();
        for(String s:startWords){
            char[] ch =s.toCharArray();
            Arrays.sort(ch);
            set.add(new String(ch));
        }
        for(String s:targetWords){
            char[] ch=s.toCharArray();
            Arrays.sort(ch);
            for(int i=0;i<s.length();i++){
                String newWord=new String(ch, 0, i)+new String(ch, i+1,ch.length-i-1);
                if(set.contains(newWord)){
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}