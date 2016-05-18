// 281. Zigzag Iterator
// https://leetcode.com/problems/zigzag-iterator/

public class ZigzagIterator {

    Queue<Iterator<Integer>> iterQueue;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        iterQueue = new LinkedList<>();
        if (!v1.isEmpty()) {
            iterQueue.offer(v1.iterator());
        }
        if (!v2.isEmpty()) {
            iterQueue.offer(v2.iterator());
        }
    }

    public int next() {
        Iterator<Integer> curIter = iterQueue.poll();
        
        int result = curIter.next();
        
        if (curIter.hasNext()) {
            iterQueue.offer(curIter);
        }
        
        return result;
    }

    public boolean hasNext() {
        return !iterQueue.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
