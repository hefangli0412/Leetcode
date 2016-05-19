// 201. Bitwise AND of Numbers Range
/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
For example, given the range [5, 7], you should return 4.
https://leetcode.com/discuss/32115/bit-operation-solution-java
*/
public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int moveFactor = 1;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }
}
