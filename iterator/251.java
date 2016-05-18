// 251. Flatten 2D Vector
// https://leetcode.com/problems/flatten-2d-vector/

/* solution 1 */

public class Vector2D implements Iterator<Integer> {

    Deque<Iterator<Integer>> iterQueue;
    
    public Vector2D(List<List<Integer>> vec2d) {
        iterQueue = new LinkedList<>();
        for (int i = vec2d.size() - 1; i >= 0; i--) {
            List<Integer> vec = vec2d.get(i);
            if (!vec.isEmpty()) {
                iterQueue.push(vec.iterator());
            }
        }
    }

    @Override
    public Integer next() {
        Iterator<Integer> curIter = iterQueue.pop();
        Integer result = curIter.next();
        if (curIter.hasNext()) {
            iterQueue.push(curIter);
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return !iterQueue.isEmpty();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
 /* solution 2 */

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
