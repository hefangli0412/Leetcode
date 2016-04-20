https://leetcode.com/problems/valid-parentheses/

public class Solution {
    public boolean isValid(String s) {
        // < close, open >
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character open_c = map.get(c);
            if (open_c == null) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.peek() != open_c) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        
        return stack.isEmpty();
    }
}
