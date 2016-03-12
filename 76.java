// 76. Minimum Window Substring
// https://leetcode.com/problems/minimum-window-substring/

/* The main idea - 
1. Use two pointers: start and end to represent a window.
2. Move end to find a valid window.
3. When a valid window is found, move start to find a smaller window.
*/

public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        
        Map<Character, Integer> map = constructMap(t);
        int matches = map.size();
        int min = Integer.MAX_VALUE, end = -1;
        int i = 0, j = 0;
        
        while (j < s.length()) {
            if (map.containsKey(s.charAt(j))) {
                Integer cnt_j = map.get(s.charAt(j));
                map.put(s.charAt(j), cnt_j - 1);
                if (cnt_j == 1) {
                    matches--;
                    while (matches == 0) { // find!!
                        if (j - i + 1 < min) {
                            min = j - i + 1;
                            end = j;
                        }
                        
                        if (map.containsKey(s.charAt(i))) {
                            Integer cnt_i = map.get(s.charAt(i));
                            map.put(s.charAt(i), cnt_i + 1);
                            if (cnt_i == 0) {
                                matches++;
                            }
                        } 
                        
                        i++;
                    }
                }
            }
            j++;
        }
        
        return (end == -1) ? "" : s.substring(end + 1 - min, end + 1);
    }
    
    private Map<Character, Integer> constructMap(String str) {
        Map<Character, Integer> countMap = new HashMap<>();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            Integer count = countMap.get(ch);
            if (count == null) {
                countMap.put(ch, 1);
            } else {
                countMap.put(ch, count + 1);
            }
        }
        
        return countMap;
    }
}
