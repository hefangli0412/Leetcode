// 286. Walls and Gates
// https://leetcode.com/problems/walls-and-gates/

public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        
        int m = rooms.length;
        int n = rooms[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, m, n, i, j);
                }
            }
        }
    }
    
    private void bfs(int[][] rooms, int m, int n, int i, int j) {
        int count = 0;
        
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(i, j));
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int r = 0; r < size; r++) {
                Point cur = q.poll();
                
                rooms[cur.i][cur.j] = count;
                
                if (cur.i > 0 && rooms[cur.i - 1][cur.j] > count + 1) {
                    q.offer(new Point(cur.i - 1, cur.j));
                }
                if (cur.i < m - 1 && rooms[cur.i + 1][cur.j] > count + 1) {
                    q.offer(new Point(cur.i + 1, cur.j));
                }
                if (cur.j > 0 && rooms[cur.i][cur.j - 1] > count + 1) {
                    q.offer(new Point(cur.i, cur.j - 1));
                }
                if (cur.j < n - 1 && rooms[cur.i][cur.j + 1] > count + 1) {
                    q.offer(new Point(cur.i, cur.j + 1));
                }
            }
            
            count++;
        }
    }
}

class Point {
    int i;
    int j;
    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
