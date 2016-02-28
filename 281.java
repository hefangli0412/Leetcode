// 281. Zigzag Iterator
// https://leetcode.com/problems/zigzag-iterator/

public class ZigzagIterator {
    private final List<Iterator<Integer>> iterList = new ArrayList<>();
    private int index = 0;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        iterList.add(v1.iterator());
        iterList.add(v2.iterator());
        index = 0;
    }

    public int next() {
        int result = iterList.get(index).next();
        index = (index + 1) % iterList.size();
        return result;
    }

    public boolean hasNext() {
        while (!iterList.isEmpty() && !iterList.get(index).hasNext()) {
            iterList.remove(index);
            if (!iterList.isEmpty()) { // otherwise, the final element will make iterList empty
                index = index % iterList.size();
            }
        }
        
        return !iterList.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
