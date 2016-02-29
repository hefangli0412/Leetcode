// 305. Number of Islands II
// https://leetcode.com/problems/number-of-islands-ii/

public class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (m == 0 || n == 0 || positions == null || positions.length == 0 
        || positions[0].length == 0) return result;
        
        UnionFind uf = new UnionFind(m * n);
        boolean[] placed = new boolean[m * n];
        
        for (int[] pos : positions) {
            int i = pos[0];
            int j = pos[1];
            int cur = i * n + j;
            
            placed[cur] = true;
            uf.addCount();
            
            int nei = cur - n;
            if (i > 0 && placed[nei]) { // up
                uf.union(cur, nei);
            }
            nei = cur - 1;
            if (j > 0 && placed[nei]) { // left
                uf.union(cur, nei);
            }
            nei = cur + n;
            if (i < m - 1 && placed[nei]) { // down
                uf.union(cur, nei);
            }
            nei = cur + 1;
            if (j < n - 1 && placed[nei]) { // right
                uf.union(cur, nei);
            }
            
            result.add(uf.count());
        }
        
        return result;
    }
}

class UnionFind {
    private int[] id;
    private int count;
    
    public UnionFind(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
        
        count = 0;
    }
    
    public int find(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        
        return i;
    }
    
    public boolean union(int i, int j) {
        int p_i = find(i);
        int p_j = find(j);
        
        if (p_i == p_j) return false;
        
        id[p_i] = p_j;
        count--;
        return true;
    }
    
    public int count() {
        return count;
    }
    
    public void addCount() {
        count++;
    }
}
