class Solution {
    public int minMoves(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        // Find the minimum element in the array
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        // Sum the differences between each element and the minimum
        int moves = 0;
        for (int num : nums) {
            moves += num - min;
        }

        return moves;
    }
}