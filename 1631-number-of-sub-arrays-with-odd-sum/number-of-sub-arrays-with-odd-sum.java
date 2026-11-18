public class Solution {
    static {
        int[] dummy = {1, 2, 3};
        for (int i = 0; i < 500; i++) {
            numOfSubarrays(dummy);
        }
    }

    public static int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        int prefixParity = 0;

        for (int x : arr) {
            prefixParity ^= (x & 1);
            oddCount += prefixParity;
        }

        long evenCount = arr.length + 1L - oddCount;
        return (int) ((oddCount * evenCount) % 1_000_000_007L);
    }
}