// 224. Basic Calculator
// https://leetcode.com/problems/basic-calculator/

/* My approach is based on the fact that the final arithmetic operation on each number 
is not only depend on the sign directly operating on it, but all signs associated with 
each unmatched '(' before that number.

e.g. 5 - ( 6 + ( 4 - 7 ) ), if we remove all parentheses, the expression becomes 5 - 6 - 4 + 7.

sign:

6: (-1)(1) = -1

4: (-1)(1)(1) = -1

7: (-1)(1)(-1) = 1

*/

public static int calculate(String s) {
    int len = s.length(), sign = 1, result = 0;
    Stack<Integer> stack = new Stack<Integer>();
    for (int i = 0; i < len; i++) {
        if (Character.isDigit(s.charAt(i))) {
            int sum = s.charAt(i) - '0';
            while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                sum = sum * 10 + s.charAt(i + 1) - '0';
                i++;
            }
            result += sum * sign;
        } else if (s.charAt(i) == '+')
            sign = 1;
        else if (s.charAt(i) == '-')
            sign = -1;
        else if (s.charAt(i) == '(') {
            stack.push(result);
            stack.push(sign);
            result = 0;
            sign = 1;
        } else if (s.charAt(i) == ')') {
            result = result * stack.pop() + stack.pop();
        }

    }
    return result;
}

/* My Stack Version */

public class Solution {
    public int calculate(String s) {
        char[] arr = s.toCharArray();
        
        Deque<Integer> numStack = new LinkedList<>();
        Deque<Character> opStack = new LinkedList<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                continue;
            } else if (arr[i] == '(' || arr[i] == '+' || arr[i] == '-') {
                opStack.push(arr[i]);
            } else {
                int num = 0;
                if (arr[i] == ')') {
                    opStack.pop(); // must be '('
                    num = numStack.pop();
                } else {
                    num = arr[i] - '0';
                    while (i + 1 < arr.length && arr[i + 1] >= '0' && arr[i + 1] <= '9') {
                        i++;
                        num = num * 10 + arr[i] - '0';
                    }
                }
                
                while (!opStack.isEmpty() && (opStack.peek() == '+' || opStack.peek() == '-')) {
                    char op = opStack.pop();
                    if (op == '+') {
                        num = numStack.pop() + num;
                    } else if (op == '-') {
                        num = numStack.pop() - num;
                    }
                }
                
                numStack.push(num);
            }
        }
        
        return numStack.peek();
    }
}
