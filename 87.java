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

填表的顺序可以通过recursive call观察得到。
假设dp状态是i,j,k分别表示两个string的起点和长度，不难发现
dp(i,j,k) depends on dp(i, j, t), dp(i+t, j+t, k-t), dp(i, j+k-t, t), dp(i+t, j, k-t), 1 <= t < k。
由于 dp(i, j, t) 所以k是从小到大填的，其他的dependency要么i变大，要么j变大，所以应该按照下面的顺序填表肯定满足topological order。
    for (int i = n - 1; i >= 0; i--) 
      for (int j = n - 1; j >= 0; j--) 
        for (int k = 1; k <= n - Math.max(i, j); k++) 
*/
public class Solution {
	public boolean isScramble2(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		if (s1.equals(s2)) {
			return true;
		}
		
		int len = s1.length();
		boolean[][][] dp = new boolean[len][len][len + 1];
		
		// 写法1
		for (int i = len - 1; i >= 0; i--) {
			for (int j = len - 1; j >= 0; j--) {
				for (int k = 1; k <= len - Math.max(i, j); k++) {
					if (k == 1) {
						dp[i][j][k] = s1.charAt(i) == s2.charAt(j);
					} else {
						for (int t = 1; t < k; t++) {
							dp[i][j][k] |= dp[i][j][t] && dp[i+t][j+t][k-t] || dp[i][j+k-t][t] && dp[i+t][j][k-t];
						}
					}
				}
			}
		}
		
		// 写法2
		for (int k = 1; k <= len; k++) {
		    	// 以i,j开头的string长度不能小于k
			for (int i = 0; i + k - 1 < len; i++) {
				for (int j = 0; j + k - 1 < len; j++) {
					if (k == 1) {
						dp[i][j][k] = s1.charAt(i) == s2.charAt(j);
					} else {
						for (int t = 1; t < k; t++) {
							dp[i][j][k] |= dp[i][j][t] && dp[i+t][j+t][k-t] || dp[i][j+k-t][t] && dp[i+t][j][k-t];
						}
					}
				}
			}
		}
		
		return dp[0][0][len];
	}
}
