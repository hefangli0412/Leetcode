// 87. Scramble String

/* Solution 1 - recursion */
/* 设isScramble对于长度n的2个string的复杂度是T(n)，假设substring是O(1)
T(n) = 2(T(1) + T(2) + ... + T(n-1))
T(n-1)=2(T(1) + T(2) +... + T(n-2))
T(n) - T(n-1) = 2T(n-1)
T(n) = 3 T(n-1)
T(1)=1
所以T(n)=3^n
不过实际上很多false的情况可以通过数字母来排除，所以实际速度很快。你们可以思考一下是不是存在可以造成3^n次运算的worst case。
这个方法可以写成dp，需要2个string的starting point和string的长度做状态。memoization很容易写。复杂度留给你们思考。
这个代码比dp快很大程度上是由于剪枝的功劳。
*/
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

/* Solution 2 - Dynamic Programming */
/* 填表是以string长度逐渐递增的，然后穷举了所有的可能行，它们只依赖于长度更小的string的结果（以前填写的行），
与当前行无关，所以顺序可以随意.
*/
public class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;  
        }
        if (s1.equals(s2)) {
            return true; 
        }
        
        int length = s1.length();
        // dp[i][j][len]，表示从s1的第i个字符开始长度为len的子串，和从s2的第j个字符开始长度为len的子串，是否互为scramble.
        boolean[][][] dp = new boolean[length][length][length + 1];  
        for (int i = 0; i < length; i++) {  
            for (int j = 0; j < length; j++) {  
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);  
            }  
        }  
          
        for (int len = 2; len <= length; len++) {  
            for (int i = 0; i + len - 1 < length; i++) {  
                for (int j = 0; j + len - 1 < length; j++) {  
                    for (int k = 1; k < len; k++) {  
                        dp[i][j][len] |= dp[i][j][k] && dp[i + k][j + k][len - k] 
                                      || dp[i][j + len - k][k] && dp[i + k][j][len - k];  
                    }  
                }  
            }  
        }  
          
        return dp[0][0][length];
    }
}
