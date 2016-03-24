// 3. Longest Substring Without Repeating Characters My Submissions Question
// https://leetcode.com/problems/longest-substring-without-repeating-characters/

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> set = new HashSet<>(); // no duplicates
        int max = 0;
        int slow = 0;
        int fast = 0;
        while (fast < s.length()) {
            if (!set.contains(s.charAt(fast))) {
                set.add(s.charAt(fast));
                fast++;
                max = Math.max(max, fast - slow);
            } else {
                set.remove(s.charAt(slow));
                slow++;
            }
        }
        
        return max;
    }
}
