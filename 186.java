// 186. Reverse Words in a String II
// https://leetcode.com/problems/reverse-words-in-a-string-ii/

public class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length <= 1) return;
        
        reverse(s, 0, s.length - 1);
        
        int start = 0;
        for (int i = 1; i <= s.length; i++) {
            if (i == s.length || s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
    }
    
    private void reverse(char[] s, int left, int right) {
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            
            left++; right--;
        }
    }
}
