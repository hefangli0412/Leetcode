// 159. Longest Substring with At Most Two Distinct Characters
// https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/

/* hashmap solution */

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> countMap = new HashMap<>();
        int max = 0, end = -1;
        int i = 0, j = 0;
        
        while (j < s.length()) {
            Integer cnt_j = countMap.get(s.charAt(j));
            if (cnt_j == null && countMap.size() == 2) {
                countMap.put(s.charAt(j), 1);
                while (true) {
                    Integer cnt_i = countMap.get(s.charAt(i));
                    countMap.put(s.charAt(i), cnt_i - 1);
                    i++;
                    if (cnt_i == 1) {
                        countMap.remove(s.charAt(i - 1));
                        break;
                    }
                }
            } else {
                if (cnt_j == null) {
                    cnt_j = 0;
                }
                
                countMap.put(s.charAt(j), cnt_j + 1);
                if (j - i + 1 > max) {
                    max = j - i + 1;
                    end = j;
                }
            }
            j++;
        }
        
        return max;
    }
}
