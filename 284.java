// 284. Peeking Iterator
// https://leetcode.com/problems/peeking-iterator/
// Google solution : https://github.com/google/guava/blob/703ef758b8621cfbab16814f01ddcc5324bdea33/guava-gwt/src-super/com/google/common/collect/super/com/google/common/collect/Iterators.java#L1125

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    private final Iterator<Integer> iterator;
    private boolean hasPeeked;
    private Integer peekedElement;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;
	    hasPeeked = false;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
	    if (!hasPeeked) {
	        peekedElement = iterator.next();
	        hasPeeked = true;
	    }
	    
        return peekedElement;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (!hasPeeked) {
	        return iterator.next();
	    }
	    
	    Integer result = peekedElement;
	    peekedElement = null;
        hasPeeked = false;
        return result;
	}

	@Override
	public boolean hasNext() {
	    return hasPeeked || iterator.hasNext();
	}
}
