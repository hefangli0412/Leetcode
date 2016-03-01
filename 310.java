// 310. Minimum Height Trees
// https://leetcode.com/problems/minimum-height-trees/

/* topological sort -
  The basic idea is "keep deleting leaves layer-by-layer, until reach the root."
  */

public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 0) return new ArrayList<>();
        if (n == 1) return Arrays.asList(0);
        
        int[] degreeArray = new int[n];
        List<List<Integer>> childrenList = new ArrayList<>();
        
        buildGraph(degreeArray, childrenList, n, edges);
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degreeArray[i] == 1) q.offer(i);
        }
        
        List<Integer> result = new ArrayList<>();;
        while (!q.isEmpty()) {
            int size = q.size();
            result = new ArrayList<Integer>(q);
            
            for (int i = 0; i < size; i++) {
                int id = q.poll();
                degreeArray[id]--;
                for (Integer child : childrenList.get(id)) {
                    degreeArray[child]--;
                    if (degreeArray[child] == 1) q.offer(child);
                }
            }
        }
        return result;
    }
    
    private void buildGraph(int[] degreeArray, List<List<Integer>> childrenList, int n, int[][] edges) {
        Arrays.fill(degreeArray, 0);
        for (int i = 0; i < n; i++) {
            childrenList.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            
            degreeArray[i]++;
            degreeArray[j]++;
            childrenList.get(i).add(j);
            childrenList.get(j).add(i);
        }
    }
}
