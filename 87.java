// 87. Scramble String

public class Solution {
	public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (!containsSameCharacters(s1, s2)) {
        	return false;
        }
        
        if (s1.length() == 1) {
        	return true;
        }
        
        int len = s1.length();
        for (int i = 1; i < len; i++) {
            // case 1: scrumble
            if (isScramble(s1.substring(0, i), s2.substring(len - i)) && isScramble(s1.substring(i), s2.substring(0, len - i))) {
                return true;
            }
            // case 2: not scrumble
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
        }

        return false;
    }
    
    private boolean containsSameCharacters(String str1, String str2) {
        int[] counts = new int[26];
        
        for (int i = 0; i < str1.length(); i++) {
            counts[str1.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < str2.length(); i++) {
            counts[str2.charAt(i) - 'a']--;
            if (counts[str2.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
