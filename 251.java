// 251. Flatten 2D Vector
// https://leetcode.com/problems/flatten-2d-vector/

public class Vector2D {
    private final Iterator<List<Integer>> iterator;
    private Iterator<Integer> curIter;
    
    public Vector2D(List<List<Integer>> vec2d) {
        iterator = vec2d.iterator();
        curIter = null;
    }

    public int next() {
        return (int)curIter.next();
    }

    public boolean hasNext() {
        while (curIter == null || !curIter.hasNext()) {
            if (iterator.hasNext()) {
                curIter = iterator.next().iterator();
            } else {
                return false;
            }
        }
        return true; 
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
