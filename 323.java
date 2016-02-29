// 323. Number of Connected Components in an Undirected Graph
// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/

public class Solution {
    public int countComponents(int n, int[][] edges) {
        if (n == 0) return 0;
        
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            
            uf.union(i, j);
        }
        
        return uf.count();
    }
}

class UnionFind {
    private int[] id;
    private int count;
    
    public UnionFind(int num) {
        id = new int[num];
        for (int i = 0; i < num; i++) {
            id[i] = i;
        }
        
        count = num;
    }
    
    public int find(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    public boolean union(int i, int j) {
        int i_p = find(i);
        int j_p = find(j);
        
        if (i_p == j_p) return false;
        
        id[i_p] = j_p;
        count--;
        return true;
    }
    
    public int count() {
        return count;
    }
}
