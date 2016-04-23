// 207. Course Schedule
// https://leetcode.com/problems/course-schedule/

/* topological sort */

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] degreeArr = new int[numCourses];
        List<Set<Integer>> childrenList = new ArrayList<>();
        
        buildGraph(numCourses, prerequisites, degreeArr, childrenList);
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (degreeArr[i] == 0) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            degreeArr[cur]--;
            for (Integer child : childrenList.get(cur)) {
                degreeArr[child]--;
                if (degreeArr[child] == 0) {
                    q.offer(child);
                }
            }
        }
        
        for (int i = 0; i < numCourses; i++) {
            if (degreeArr[i] >= 0) {
                return false;
            }
        }
        
        return true;
    }
    
    private void buildGraph(int numCourses, int[][] prerequisites, int[] degreeArr, List<Set<Integer>> childrenList) {
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
