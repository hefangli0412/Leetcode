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
    Deque<Iterator<NestedInteger>> stack; // type is NestedIterator
    Integer nextNum;
    
	public NestedIterator(List<NestedInteger> nestedList) {
		stack = new LinkedList<Iterator<NestedInteger>>();
        stack.push(nestedList.iterator());
        move();
    }
	
	@Override
	public boolean hasNext() {
		move();
        return nextNum != null;
	}

	@Override
	public Integer next() {
		Integer res = nextNum;
		nextNum = null;
		return res;
	}
    
    public void move() {
        while (nextNum == null && !stack.isEmpty()) {
            Iterator<NestedInteger> iter = stack.pop();
            if (iter.hasNext()) {
                NestedInteger nextNI = iter.next();
                if (nextNI.isInteger()) {
                    nextNum = nextNI.getInteger();
                    stack.push(iter);
                    return;
                } else {
                    // 不是数字就把list的iterator放到stack里
                	stack.push(iter);
                	Iterator<NestedInteger> nestIter = nextNI.getList().iterator();
                    stack.push(nestIter);
                }
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
