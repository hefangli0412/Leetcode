// 261. Graph Valid Tree
// https://leetcode.com/problems/graph-valid-tree/

/* According to the definition of tree on Wikipedia: “a tree is an undirected 
graph in which any two vertices are connected by exactly one path. In other words, 
any connected graph without simple cycles is a tree.
*/

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            
            if (!uf.union(i, j)) return false; // indicates there is a circle
        }
        
        return uf.count() == 1; // indicates the graph is connected
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
