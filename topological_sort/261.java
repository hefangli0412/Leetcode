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

public class Solution {
    public boolean validTree(int numVertices, int[][] edges) {
		UnionFind uf = new UnionFind(numVertices);
		for (int[] edge : edges) {
			int i = edge[0];
			int j = edge[1];
			
			if (!uf.union(i, j)) {
				return false;
			}
		}
		
		return uf.count == 1;
	}

	// weighted, path compression
	static class UnionFind {
		private int[] ids;
		private int[] sizes;
		private int count;
		
		public UnionFind(int num) {
			count = num;
			ids = new int[num];
			sizes = new int[num];
			for (int i = 0; i < num; i++) {
				ids[i] = i;
				sizes[i] = 1;
			}
		}
		
		public int find(int i) {
			int root = i;
			while (ids[root] != root) {
				root = ids[root];
			}
			while (i != root) {
				int next = ids[i];
				ids[i] = root;
				i = next;
			}
			return root;
		}
		
		public boolean union(int i, int j) {
			int i_root = find(i);
			int j_root = find(j);
			if (i_root == j_root) {
				return false;
			}
			
			if (sizes[i_root] >= sizes[j_root]) {
				ids[j_root] = i_root;
				sizes[i_root] += sizes[j_root];
			} else {
				ids[i_root] = j_root;
				sizes[j_root] += sizes[i_root];
			}
			count--;
			return true;
		}
		
		public int count() {
			return count;
		}
	}
}
