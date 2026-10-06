class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k=m+n;
        int j=n-1;
        for(int i=0;i<k;i++){
            if(nums1[i]==0 && j>=0){
                nums1[i] = nums2[j];
                j--;
            }
        }
        Arrays.sort(nums1);
    }
}