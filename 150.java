// 150. Evaluate Reverse Polish Notation
// https://leetcode.com/problems/evaluate-reverse-polish-notation/

/* stack - lower time complexity */

public class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].matches("-?\\d+")) {
                stack.push(Integer.parseInt(tokens[i]));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                if (tokens[i].equals("+")) {
                    num1 += num2;
                } else if (tokens[i].equals("-")) {
                    num1 -= num2;
                } else if (tokens[i].equals("*")) {
                    num1 *= num2;
                } else if (tokens[i].equals("/")) {
                    num1 /= num2;
                }
                stack.push(num1);
            }
        }
        
        return stack.peek();
    }
}
