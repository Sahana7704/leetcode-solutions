import java.util.Scanner;
class Solution {
    public static boolean isPalindrome(int x) {
        String s=Integer.toString(x);
        int n=s.length();
        for(int i=0;i<n/2;i++){
            if(s.charAt(i)!=s.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(isPalindrome(n));
        sc.close();
    }
}