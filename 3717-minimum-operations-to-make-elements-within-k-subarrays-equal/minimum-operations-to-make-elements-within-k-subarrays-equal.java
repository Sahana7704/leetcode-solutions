import java.util.Arrays;

class Solution {
    private int[] maxHeap;
    private int maxSz;
    private int[] minHeap;
    private int minSz;

    public long minOperations(int[] nums, int x, int k) {
        if (k == 0 || x <= 1) return 0; // Cost to equalize subarrays of size 1 is mathematically 0

        int n = nums.length;
        maxHeap = new int[n];
        minHeap = new int[n];
        maxSz = 0;
        minSz = 0;
        
        boolean[] inLower = new boolean[n];
        int lowerActive = 0, upperActive = 0;
        long lowerSum = 0, upperSum = 0;

        // Circular ring buffer to strictly bind DP memory to O(N)
        long[][] dp = new long[x][k + 1];
        long INF = Long.MAX_VALUE / 2; // Prevents overflow when adding cost
        
        for (int i = 0; i < x; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = 0; i < n; i++) {
            // 1. Add current element to the streaming window
            cleanMax(nums, i - x);
            if (lowerActive == 0 || nums[i] <= nums[maxHeap[0]]) {
                pushMax(nums, i);
                inLower[i] = true;
                lowerSum += nums[i];
                lowerActive++;
            } else {
                pushMin(nums, i);
                inLower[i] = false;
                upperSum += nums[i];
                upperActive++;
            }

            // 2. Lazily remove the element falling out of the window
            if (i >= x) {
                int out = i - x;
                if (inLower[out]) {
                    lowerSum -= nums[out];
                    lowerActive--;
                } else {
                    upperSum -= nums[out];
                    upperActive--;
                }
            }

            // 3. Balance the internal binary heaps
            int sz = Math.min(i + 1, x);
            int targetLower = (sz + 1) / 2;
            
            while (lowerActive > targetLower) {
                cleanMax(nums, i - x);
                int idx = popMax(nums);
                inLower[idx] = false;
                lowerSum -= nums[idx];
                lowerActive--;
                
                pushMin(nums, idx);
                upperSum += nums[idx];
                upperActive++;
            }
            while (lowerActive < targetLower && upperActive > 0) {
                cleanMin(nums, i - x);
                int idx = popMin(nums);
                inLower[idx] = true;
                upperSum -= nums[idx];
                upperActive--;
                
                pushMax(nums, idx);
                lowerSum += nums[idx];
                lowerActive++;
            }

            // 4. Compute cost and resolve DP state when the window is fully saturated
            if (i >= x - 1) {
                cleanMax(nums, i - x);
                long median = nums[maxHeap[0]];
                long cost = (x % 2 == 1) ? (upperSum - lowerSum + median) : (upperSum - lowerSum);

                int len = i + 1;
                int curr = len % x;
                int prev = (len - 1) % x;

                // Backwards evaluation safely overwrites the oldest state in the ring buffer
                for (int j = k; j >= 1; j--) {
                    long skip = dp[prev][j];
                    long take = dp[curr][j - 1] + cost;
                    dp[curr][j] = skip < take ? skip : take;
                }
            }
        }

        return dp[n % x][k];
    }

    // Hardware-level Max Heap (Lower Half)
    private void pushMax(int[] nums, int idx) {
        int curr = maxSz++;
        while (curr > 0) {
            int p = (curr - 1) >> 1;
            if (nums[maxHeap[p]] >= nums[idx]) break;
            maxHeap[curr] = maxHeap[p];
            curr = p;
        }
        maxHeap[curr] = idx;
    }

    private int popMax(int[] nums) {
        int res = maxHeap[0];
        int idx = maxHeap[--maxSz];
        int curr = 0;
        while ((curr << 1) + 1 < maxSz) {
            int child = (curr << 1) + 1;
            int right = child + 1;
            if (right < maxSz && nums[maxHeap[right]] > nums[maxHeap[child]]) {
                child = right;
            }
            if (nums[idx] >= nums[maxHeap[child]]) break;
            maxHeap[curr] = maxHeap[child];
            curr = child;
        }
        maxHeap[curr] = idx;
        return res;
    }

    private void cleanMax(int[] nums, int limitIdx) {
        while (maxSz > 0 && maxHeap[0] <= limitIdx) {
            popMax(nums);
        }
    }

    // Hardware-level Min Heap (Upper Half)
    private void pushMin(int[] nums, int idx) {
        int curr = minSz++;
        while (curr > 0) {
            int p = (curr - 1) >> 1;
            if (nums[minHeap[p]] <= nums[idx]) break;
            minHeap[curr] = minHeap[p];
            curr = p;
        }
        minHeap[curr] = idx;
    }

    private int popMin(int[] nums) {
        int res = minHeap[0];
        int idx = minHeap[--minSz];
        int curr = 0;
        while ((curr << 1) + 1 < minSz) {
            int child = (curr << 1) + 1;
            int right = child + 1;
            if (right < minSz && nums[minHeap[right]] < nums[minHeap[child]]) {
                child = right;
            }
            if (nums[idx] <= nums[minHeap[child]]) break;
            minHeap[curr] = minHeap[child];
            curr = child;
        }
        minHeap[curr] = idx;
        return res;
    }

    private void cleanMin(int[] nums, int limitIdx) {
        while (minSz > 0 && minHeap[0] <= limitIdx) {
            popMin(nums);
        }
    }
}