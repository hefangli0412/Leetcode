// 223. Rectangle Area
// https://leetcode.com/problems/rectangle-area/

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaA = Math.abs(A - C) * Math.abs(B - D);
        int areaB = Math.abs(E - G) * Math.abs(F - H);
        int crossedArea = crossedArea(A, B, C, D, E, F, G, H);
        
        return areaA + areaB - crossedArea;
    }
    
    private int crossedArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int topMin = Math.min(D, H);
        int botMax = Math.max(B, F);
        int rightMin = Math.min(C, G);
        int leftMax = Math.max(A, E);
        
        if (topMin < botMax || rightMin < leftMax) return 0;
        
        return (topMin - botMax) * (rightMin - leftMax);
    }
}
