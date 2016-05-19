// 251. Flatten 2D Vector
// https://leetcode.com/problems/flatten-2d-vector/

/* solution 1 */
// 思维难度稍大一点，不过代码比较简单，复杂度也低

public class Vector2D implements Iterator<Integer> {
    
    private final Iterator<List<Integer>> outerIter;
    private Iterator<Integer> innerIter;

    public Vector2D(List<List<Integer>> vec2d) {
        outerIter = vec2d.iterator();
        innerIter = Collections.emptyIterator();
    }

    @Override
    public Integer next() {
        return innerIter.next();
    }

    @Override
    public boolean hasNext() {
        while (!innerIter.hasNext() && outerIter.hasNext()) {
            innerIter = outerIter.next().iterator();
        }
        return innerIter.hasNext();
    }
}

/* solution 2 */

public class Vector2D implements Iterator<Integer> {

    Deque<Iterator<Integer>> iterStack;
    
    public Vector2D(List<List<Integer>> vec2d) {
        iterStack = new LinkedList<>();
        for (int i = vec2d.size() - 1; i >= 0; i--) {
            List<Integer> vec = vec2d.get(i);
            if (!vec.isEmpty()) {
                iterStack.push(vec.iterator());
            }
        }
    }

    @Override
    public Integer next() {
        Iterator<Integer> curIter = iterStack.pop();
        Integer result = curIter.next();
        if (curIter.hasNext()) {
            iterStack.push(curIter);
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return !iterStack.isEmpty();
    }
}
 

