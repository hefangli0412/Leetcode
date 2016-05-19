// 326. Power of Three

/* https://leetcode.com/discuss/78532/summary-all-solutions-new-method-included-at-15-30pm-jan-8th */

// Method 1 works only when the base is prime. 3 ^ ?? 
// why? 对于prime numbers是充要条件，对于composite numbers是充分不必要条件。 4 ^ 11 or 2 ^ 11 are both fine.
public class Solution {
    public boolean isPowerOfThree(int n) {
        int maxPowerOfThree = (int) Math.pow(3, (int)(Math.log(Integer.MAX_VALUE) / Math.log(3)));
        return n > 0 && maxPowerOfThree % n == 0;
    }
}

// Method 2 works for all base numbers
// "Returns a string representation of the first argument in the radix specified by the second argument."
public class Solution {
    public boolean isPowerOfThree(int n) {
        return Integer.toString(n, 3).matches("10*");
    }
}
