// 229. Majority Element II

/* nGiven an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space.

分成3类，num1, num2, others, 保证num1, num2数量大于others。这里是必要条件。
不是很好理解，要多走几个例子。
*/

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        // apply Moore's Voting Algorithm
        int num1 = 0;
        int num2 = 0;
        int count1 = 0;
        int count2 = 0;
        
        for (int num : nums) {
            if (num == num1) {
                count1++;
            } else if (num == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = num;
                count1++;
            } else if (count2 == 0) {
                num2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        
        // count again
        count1 = count2 = 0;
        for (int num : nums) {
            if (num == num1) {
                count1++;
            } else if (num == num2) {
                count2++;
            }
        }
        
        // check candidates
        List<Integer> result = new ArrayList<>();
        if (3 * count1 > nums.length) {
            result.add(num1);
        }
        if (3 * count2 > nums.length) {
            result.add(num2);
        }
        return result;
    }
}
