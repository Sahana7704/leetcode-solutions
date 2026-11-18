import java.util.AbstractList;

class Solution {
    List<Integer> res;
    public List<Integer> findSubstring(String s, String[] words) {
        return new AbstractList<Integer>(){
            public Integer get(int index){
                init();
                return res.get(index);
            }
            public int size(){
                init();
                return res.size();
            }

            private void init(){
                if(res != null){
                   return;
                }
                 res = new ArrayList<>();

                 if(s == null || words == null ||
                     words.length == 0){
                        return;
                     }
                int wordLen = words[0].length();
                int wordCount = words.length;
                int totalLen = wordCount * wordLen;

                if(totalLen > s.length()){
                    return;
                }

                Map<String, Integer> wordFreq = new HashMap<>();

                for(String word: words){
                    wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
                }

                for(int i =0; i< wordLen; i++){
                    int left =i;
                    int count =0;
                    
                    Map<String, Integer> seen = new HashMap<>();

                    for(int right = i; right + wordLen <= s.length(); right += wordLen){
                        String word = s.substring(right, right + wordLen);

                        if(!wordFreq.containsKey(word)){
                           seen.clear();
                           count =0;
                           left = right + wordLen;
                           continue;
                        }

                        seen.put(word, seen.getOrDefault(word, 0) + 1);

                        count++;

                        while(seen.get(word) > wordFreq.get(word)){
                            String leftWord = s.substring(left, left + wordLen);
                            
                            seen.put(leftWord, seen.get(leftWord) - 1);

                            left += wordLen;
                            count--;
                        }
                        
                        if(count == wordCount){
                            res.add(left);

                            String leftWord = s.substring(left, left + wordLen);
                            
                            seen.put(leftWord, seen.get(leftWord) - 1);

                            left += wordLen;
                            count--;
                        }
                        

                    }

                }
            }
        
        }; 
    }
}