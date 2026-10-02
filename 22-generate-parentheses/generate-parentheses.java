class Solution {
    public List<String> generateParenthesis(int n) {
           List<String> ans=new ArrayList<String>();
          generate(n, ans, "", 0,0);
          return ans;
    }
    public void generate(int n, List<String> ans, String s, int open, int close){
        if(open==n && close == n){
            ans.add(s);
            return;
        }
        if(open<n){
            generate(n, ans, s+"(", open+1, close);
        }
        if(close<open){
            generate(n, ans, s+")", open, close+1);
        }
    }
}