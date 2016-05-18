// https://leetcode.com/problems/flatten-nested-list-iterator/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Deque<Iterator<NestedInteger>> stack;
    Integer nextElement;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        if (nextElement == null) {
            throw new NoSuchElementException();
        }
        
        Integer result = nextElement;
        nextElement = null;
        return result;
    }

    @Override
    public boolean hasNext() {
        if (nextElement != null) {
            return true;
        }
        
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> curIter = stack.peek();
            if (curIter.hasNext()) {
                NestedInteger cur = curIter.next();
                if (cur.isInteger()) {
                    nextElement = cur.getInteger();
                    return true;
                } else {
                    stack.push(cur.getList().iterator());
                }
            } else {
                stack.pop();
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
