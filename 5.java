https://leetcode.com/problems/longest-palindromic-substring/

public class Solution {
    public String longestPalindrome(String s) {
		int len = s.length();
		boolean[][] M = new boolean[len][len];
		int maxLen = 0;
        int start = 0;

		for (int i = 0; i < len; i++) {
			for (int j = i; j >= 0; j--) {
				if (j == i) {
					M[j][i] = true;
				    if (maxLen < 1) {
				        maxLen = 1;
				        start = j;
				    }
				} else if (j + 1 == i) {
				    if (s.charAt(j) == s.charAt(i)) {
				        M[j][i] = true;
				        if (maxLen < 2) {
				            maxLen = 2;
				            start = j;
				        }
				    }
				} else {
					if (s.charAt(j) == s.charAt(i) && M[j + 1][i - 1]) {
						M[j][i] = true;
						if (maxLen < i - j + 1) {
						    maxLen = i - j + 1;
						    start = j;
						}
					}
				}
			}
		}

		return s.substring(start, start + maxLen);
	}
}


// O(n) space
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        
		int len = s.length();
		boolean[][] M = new boolean[len][2];
        M[0][0] = true;
		int maxLen = 1;
        int start = 0;
        
		for (int i = 1; i < len; i++) {
			for (int j = i; j >= 0; j--) {
				if (j == i) {
					M[j][1] = true;
				} else if (j + 1 == i) {
				    if (s.charAt(j) == s.charAt(i)) {
				        M[j][1] = true;
				        if (maxLen < 2) {
				            maxLen = 2;
				            start = j;
				        }
				    }
				} else {
					if (s.charAt(j) == s.charAt(i) && M[j + 1][0]) {
						M[j][1] = true;
						if (maxLen < i - j + 1) {
						    maxLen = i - j + 1;
						    start = j;
						}
					}
				}
			}
			for (int k = 0; k < len; k++) {
			    M[k][0] = M[k][1];
			    M[k][1] = false;
			}
		}

		return s.substring(start, start + maxLen);
	}
}
