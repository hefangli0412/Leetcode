// 276. Paint Fence
// https://leetcode.com/problems/paint-fence/

public class Solution {
    public int numWays(int n, int k) {
        if (k == 0 || n == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        
        // M1[i] denotes the number of different ways to paint fence 1, 2, .., i 
        // and i and i - 1 have differnt colors
        int[] M1 = new int[2];
        // M2[i] denotes the number of different ways to paint fence 1, 2, .., i 
        // and i and i - 1 have the same color
        int[] M2 = new int[2];
        
        M1[0] = k;
        M2[0] = k;
        M1[1] = k * (k - 1);
        M2[1] = k;
        for (int i = 2; i < n; i++) {
            // case 1: different from last post
            M1[i % 2] = (M1[(i - 1) % 2] + M2[(i - 1) % 2]) * (k - 1);
            
            // case 2: same with last post
            M2[i % 2] = M1[(i - 1) % 2];
        }
        return M1[(n - 1) % 2] + M2[(n - 1) % 2];
    }
}
