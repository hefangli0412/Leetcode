https://leetcode.com/problems/n-queens-ii/

public class Solution {
    private int count = 0;
    
    public int totalNQueens(int n) {
        count = 0;
        List<Integer> cur = new ArrayList<>();
        
        dfs(cur, n);
        
        return count;
    }
    
    private void dfs(List<Integer> cur, int n) {
        if (cur.size() == n) {
            count++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            cur.add(i);
            if (checkConflict(cur)) {
                dfs(cur, n);
            }
            cur.remove(cur.size() - 1);
        }
    }
    
    private boolean checkConflict(List<Integer> cur) {
        int last = cur.get(cur.size() - 1);
        
        for (int i = 0; i < cur.size() - 1; i++) {
            if (last == cur.get(i) || Math.abs(last - cur.get(i)) == Math.abs(cur.size() - 1 - i)) {
                return false;
            }
        }
        
        return true;
    }
}
