// 276. Paint Fence
// https://leetcode.com/problems/paint-fence/

/* Dynamic Programming - 

  When seeing back from the next level, ways to paint the same, or variable same would equal to dif*1 = dif, 
  and ways to paint differently, variable dif, would equal to same*(k-1)+dif*(k-1) = (same + dif)*(k-1)
*/

public class Solution {
    public int numWays(int n, int k) {
        if (k == 0 || n == 0) return 0;
        
        // For the first post
        int same = 0;
        int diff = k;
        
        // From the second and on
        for (int i = 2; i <= n; i++) {
            int total = same + diff;
            same = diff; // cannot be three same colors
            diff = total * (k - 1);
        }
        
        return same + diff;
    }
}
