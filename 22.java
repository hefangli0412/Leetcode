https://leetcode.com/problems/generate-parentheses/

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generatePHelper(result, sb, n, n);
        return result;
    }
    
    private void generatePHelper(List<String> result, StringBuilder sb, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(sb.toString());
        }
        
        if (left > 0) {
            sb.append('(');
            generatePHelper(result, sb, left - 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (right > left) {
            sb.append(')');
            generatePHelper(result, sb, left, right - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
