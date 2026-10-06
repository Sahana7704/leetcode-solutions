class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int low=0;
        int res=Integer.MAX_VALUE;
        int sum=0;
        for(int high=0;high<nums.length;high++){
            sum+=nums[high];
            while(sum>=target){
                res=Math.min(res, high-low+1);
                sum-=nums[low];
                low++;
            }
        }
        return res == Integer.MAX_VALUE?0:res;
    }
}