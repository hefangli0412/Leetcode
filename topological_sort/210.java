// 210. Course Schedule II
// https://leetcode.com/problems/course-schedule-ii/

/* topological sort */

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] degreeArr = new int[numCourses];
        List<Set<Integer>> childrenList = new ArrayList<>();
        
        buildGraph(numCourses, prerequisites, degreeArr, childrenList);
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (degreeArr[i] == 0) q.offer(i);
        }
        
        int[] result = new int[numCourses];
        int idx = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            degreeArr[cur]--;
            result[idx++] = cur;
            for (Integer child : childrenList.get(cur)) {
                degreeArr[child]--;
                if (degreeArr[child] == 0) q.offer(child);
            }
        }
        
        for (int i = 0; i < numCourses; i++) {
            if (degreeArr[i] >= 0) return new int[0];
        }
        
        return result;
    }
    
    private void buildGraph(int numCourses, int[][] prerequisites, int[] degreeArr, List<Set<Integer>> childrenList) {
        for (int i = 0; i < numCourses; i++) {
            degreeArr[i] = 0;
        }
        for (int i = 0; i < numCourses; i++) {
            childrenList.add(new HashSet<Integer>());
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
            int cur = prerequisites[i][0];
            int pre = prerequisites[i][1];
            
            if (childrenList.get(pre).add(cur)) {
                degreeArr[cur]++;
            }
        }
    }
}
