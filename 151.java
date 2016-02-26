
// 151. Reverse Words in a String
// https://leetcode.com/problems/reverse-words-in-a-string/

/* What constitutes a word?
  A sequence of non-space characters constitutes a word.
  
  Could the input string contain leading or trailing spaces?
  Yes. However, your reversed string should not contain leading or trailing spaces.
  
  How about multiple spaces between two words?
  Reduce them to a single space in the reversed string.
*/

public class Solution {
    public String reverseWords(String s) {
       String[] parts = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        for (int i = parts.length - 1; i >= 0; i--)
            sb.append(parts[i].trim() + " ");
        return sb.toString().trim();
    }
}
