// 312. Burst Balloons
// https://leetcode.com/problems/burst-balloons/

// https://leetcode.com/discuss/72216/share-some-analysis-and-explanations

// Dynamic Programmming - O(n ^ 3)

public class Solution {
    
    public int maxCoins(int[] nums) {
        int[] extNums = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            extNums[i + 1] = nums[i];
        }
        extNums[0] = extNums[extNums.length - 1] = 1;

        int[][] dp = new int[extNums.length][extNums.length];
        
        for (int i = extNums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < extNums.length; j++) {
                for (int t = i + 1; t < j; t++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][t] + dp[t][j] + extNums[i] * extNums[t] * extNums[j]);
                }
            }
        }
        
        return dp[0][extNums.length - 1];
    }
}

// Divide and Conquer with Memorization

public class Solution {

    public int maxCoins(int[] nums) {
        int[] extNums = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            extNums[i + 1] = nums[i];
        }
        extNums[0] = extNums[extNums.length - 1] = 1;

        int[][] memo = new int[extNums.length][extNums.length];
        
        return dfs(memo, extNums, 0, extNums.length - 1);
    }
    
    private int dfs(int[][] memo, int[] extNums, int left, int right) {
        if (left + 1 == right) {
            return 0;
        }
        if (memo[left][right] != 0) {
            return memo[left][right];
        }
        
        int res = 0;
        
        for (int i = left + 1; i < right; i++) {
            res = Math.max(res, dfs(memo, extNums, left, i) + extNums[left] * extNums[i] * extNums[right] + dfs(memo, extNums, i, right));
        }
        
        memo[left][right] = res;
        return res;
    }
}

// DFS - O(n!)

public class Solution {

    public int maxCoins(int[] nums) {
        int[] res = new int[1];
        int[] curRes = new int[1];
        
        List<Integer> curList = new ArrayList<>();
        curList.add(1);
        for (int num : nums) {
            curList.add(num);
        }
        curList.add(1);
        
        dfs(res, curRes, curList);
        return res[0];
    }
    
    private void dfs(int[] res, int[] curRes, List<Integer> curList) {
        if (curList.size() == 2) {
            res[0] = Math.max(res[0], curRes[0]);
            return;
        }
        
        // select one of the balloon to burst
        for (int i = 1; i < curList.size() - 1; i++) {
            int coin = curList.get(i - 1) * curList.get(i) * curList.get(i + 1);
            int balloon = curList.get(i);
            curRes[0] += coin;
            curList.remove(i);
            dfs(res, curRes, curList);
            curRes[0] -= coin;
            curList.add(i, balloon);
        }
    }
}
