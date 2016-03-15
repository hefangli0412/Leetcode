// 275. H-Index II My Submissions Question
// https://leetcode.com/problems/h-index-ii/

/* condition : the number of elements(including itself) are greater or equal to the value in the array.

"A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
We are to return the number of qualified papers.
After binary search, all papers to the left of left are not qualified. We could simply deduct them from all papers.
*/

public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        int len = citations.length;
        int left = 0;
        int right = len - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return len - left;
    }
}
