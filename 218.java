// 218. The Skyline Problem
// https://leetcode.com/problems/the-skyline-problem/

/* Refer to https://leetcode.com/discuss/67091/once-for-all-explanation-with-clean-java-code-nlog-time-space

https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
PriorityQueue has a remove(Object) method, which takes O(n) time.

The time complexity is O(n log n + n * k).
k is the max size of priority queue.

The general idea is - 

for position in sorted(all start points and all end points)
 if this position is a start point
        add its height
 else if this position is a end point
        delete its height
 compare current max height with previous max height, if different, 
 add current position together with this new max height to our result, 
 at the same time, update previous max height to current max height;
 */
       
public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
    	List<int[]> heights = new ArrayList<>();
    
    	for (int[] b : buildings) {
    		heights.add(new int[]{b[0], -b[2]}); // start point has negative height value
    		heights.add(new int[]{b[1], b[2]}); // end point has normal height value
    	}
    
    	// Sort heights array, based on the first value, 
    	// if necessary, use the second to break ties
    	// (consider four consitions, the sequence is a must)
    	Collections.sort(heights, new Comparator<int[]>() {
    		public int compare(int[] a, int[] b) {
    			if (a[0] < b[0]) {
    				return -1;
    			} else if (a[0] > b[0]) {
    				return 1;
    			} else if (a[1] < b[1]) {
    				return -1;
    			} else if (a[1] > b[1]) {
    				return 1;
    			} else {
    			    return 0;
    			}
    		}
    	});
    
    	// Use a maxHeap to store possible heightss
    	PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    
      // Provide a initial value to make pq not empty during processing
    	pq.offer(0);
    
    	int prev = 0;
    
    	for (int[] h : heights) {
    		if (h[1] < 0) { // start point, add heights
    			pq.offer(-h[1]);
    		} else { // end point, remove height
    			pq.remove(h[1]);
    		}
    
    		int cur = pq.peek(); // current max height;
    
        // compare current max height with previous max height, 
        // update result and previous max height if necessary
    		if (prev != cur) {
    			result.add(new int[]{h[0], cur});
    			prev = cur;
    		}
    	}
    
    	return result;
    }
}

/* Other good solutions - https://leetcode.com/discuss/48638/java-570ms-430ms-divide-conquer-solution-with-explanation

This solution uses TreeMap instead of PriorityQueue, do give it a look!

The key point is to use height as the key.

https://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html
A Red-Black tree based NavigableMap implementation.
This implementation provides guaranteed log(n) time cost for the containsKey, get, put and remove operations. 
*/



public class SkylineProblemSolver {
    static class Point implements Comparable<Point> {
        int xcoord;
        int height;
        boolean isLeft;
        
        public Point(int xcoord, int height, boolean isLeft) {
            this.xcoord = xcoord;
            this.height = height;
            this.isLeft = isLeft;
        }
        
        @Override
        public int compareTo(Point another) {
            if (xcoord != another.xcoord) { // smaller xcoordinates have higher priority
                return Integer.compare(xcoord, another.xcoord);
            } else if (isLeft != another.isLeft) { // right points have higher priority
            	return isLeft ? -1 : 1;
            } else if (isLeft) { // start with higher first
                return Integer.compare(another.height, height);
            } else { // end with lower first
                return Integer.compare(height, another.height);
            }
        }
    }
    
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return result;
        }
        
        List<Point> points = new ArrayList<>();
        for (int[] building : buildings) {
            points.add(new Point(building[0], building[2], true));
            points.add(new Point(building[1], building[2], false));
        }
        Collections.sort(points);
        
        TreeMap<Integer, Integer> heightMaxHeap = new TreeMap<>(Collections.reverseOrder());
        for (Point point : points) {
            if (point.isLeft) {
                if (heightMaxHeap.isEmpty() || point.height > heightMaxHeap.firstKey()) {
                    result.add(new int[]{point.xcoord, point.height});
                }
                
                Integer heightCount = heightMaxHeap.get(point.height);
                if (heightCount == null) {
                	heightCount = 0;
                }
                heightMaxHeap.put(point.height, heightCount + 1);
            } else {
                if (point.height == heightMaxHeap.firstKey() && heightMaxHeap.firstEntry().getValue() == 1) {
                	Map.Entry<Integer, Integer> secondLargestEntry = heightMaxHeap.higherEntry(heightMaxHeap.firstKey());
                	int secondLargestHeight = 0;
                	if (secondLargestEntry != null) {
                		secondLargestHeight = secondLargestEntry.getKey();
                	}
                    result.add(new int[]{point.xcoord, secondLargestHeight});
                }
                
                Integer heightCount = heightMaxHeap.get(point.height);
                if (heightCount == 1) {
                	heightMaxHeap.remove(point.height);
                } else {
                    heightMaxHeap.put(point.height, heightCount - 1);                	
                }
            }
        }
        return result;
    }
}
