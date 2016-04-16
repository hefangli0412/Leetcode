https://leetcode.com/problems/merge-intervals/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        
        if (intervals == null || intervals.size() == 0) {
            return result;
        }
        
        // <start, end>
     	Map<Integer, Integer> map = new HashMap<>();
    	// start points
    	PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // construct data structure
        for (Interval interval : intervals) {
                int start = interval.start;
                int end = interval.end;
            	Integer oldEnd = map.get(start);
                if (oldEnd == null) {
                    map.put(start, end);
                    minHeap.offer(start);
                } else if (oldEnd < end) {
                    map.put(start, end);
                }
        }
        
        // merge
        Integer curStart = null;
        Integer curEnd = null;
        
        while (!minHeap.isEmpty()) {
            int start = minHeap.poll();
            int end = map.get(start);
            // start > curStart
            if (curStart == null) {
                curStart = start;
            	curEnd = end;
            } else if (start > curEnd) {
            	result.add(new Interval(curStart, curEnd));
            	curStart = start;
            	curEnd = end;
            } else if (end > curEnd) {
            	curEnd = end;
            }
        }
        
        result.add(new Interval(curStart, curEnd));
        
        return result;
    }
}
