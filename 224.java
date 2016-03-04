// 224. Basic Calculator
// https://leetcode.com/problems/basic-calculator/

/* convert char to int : int num = ch - '0';
find if char is a digit number : Character.isDigit(ch);
*/

/* My approach is based on the fact that the final arithmetic operation on each number 
is not only depend on the sign directly operating on it, but all signs associated with 
each unmatched '(' before that number.

e.g. 5 - ( 6 + ( 4 - 7 ) ), if we remove all parentheses, the expression becomes 5 - 6 - 4 + 7.

sign:

6: (-1)(1) = -1

4: (-1)(1)(1) = -1

7: (-1)(1)(-1) = 1

*/

/* We only record one value because adding the current number only need the help of 
the current sign. When we encounter any '(' we should record the current state and 
start a new process, so we need a stack. The stack only contains the state before '('.
When ')' comes, we merge the current state with the previous one. '(' and ')' come 
in pairs.
*/

public class Solution {
    public int calculate(String s) {
        int result = 0;
        int sign = 1;
        Deque<Integer> stack = new LinkedList<>();  // help to record and restore the current state
                                                    // when encountering '(' and ')'.
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            } else if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    i++;
                    num = num * 10 + s.charAt(i) - '0';
                }
                result += num * sign;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1; // just like there is no parenthesis
            } else if (ch == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else { // ch == ')'
                result = result * stack.pop() + stack.pop();
                // sign does not matter here, since no number following
            }
        }
        
        return result;
    }
}
