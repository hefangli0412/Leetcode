// 3. Longest Substring Without Repeating Characters My Submissions Question
// https://leetcode.com/problems/longest-substring-without-repeating-characters/

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> countMap = new HashMap<>();
        int max = 0, i = 0, j = 0;
        
        while (j < s.length()) {
            Integer cnt_j = countMap.get(s.charAt(j));
            if (cnt_j == null || cnt_j == 0) {
                max = Math.max(max, j - i + 1);
                countMap.put(s.charAt(j), 1);
                j++;
            } else { // cnt_j == 1
                countMap.put(s.charAt(j), 2);
                
                while (true) {
                    Integer cnt_i = countMap.get(s.charAt(i));
                    countMap.put(s.charAt(i), cnt_i - 1);
                    i++;
                    if (cnt_i == 2) break;
                }
                j++;
            }
        }
        
        return max;
    }
}
