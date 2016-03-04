// basic calculator

/* refer to 224.java and 227.java */

import java.util.*;

public class BasicCalculator
{
    public static int calculate(String s) {
        int result = 0;
        char sign = '+';
        int preVal = 0;
        Deque<Integer> numStack = new LinkedList<Integer>();
        Deque<Character> signStack = new LinkedList<Character>();
        
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
                if (sign == '+') {
                    result += preVal;
                    preVal = num;
                } else if (sign == '-') {
                    result += preVal;
                    preVal = -num;
                } else if (sign == '*') {
                    preVal *= num;
                } else if (sign == '/') {
                    preVal /= num;
                }
            } else if (ch == '(') {
                numStack.push(result);
                signStack.push(sign);
                numStack.push(preVal);
                
                result = 0;
                sign = '+';
                preVal = 0;
            } else if (ch == ')') {
                result += preVal;
                int tmp = result;
                
                sign = signStack.pop();
                preVal = numStack.pop();
                result = numStack.poll();
                
                if (sign == '+') {
                    result += preVal;
                    preVal = tmp;
                } else if (sign == '-') {
                    result += preVal;
                    preVal = -tmp;
                } else if (sign == '*') {
                    preVal *= tmp;
                } else if (sign == '/') {
                    preVal /= tmp;
                }
            } else {
                sign = ch;
            }
        }
        
        result += preVal;
        return result;
    }
    
    public static void main(String[] args)
    {
        System.out.println(calculate(" 1+3/2*2") + " = 3");
        System.out.println(calculate("(1+(4+5-6))+2") + " = 6");
        System.out.println(calculate("(1+(4+5*6/7))+2") + " = 11");
        System.out.println(calculate("1 + 2*(3+(2*2))") + " = 15");
    }
}
