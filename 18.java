// 18. 4Sum
// https://leetcode.com/problems/4sum/

/* General idea is to generate all pairs, 
  then hash all the pairs with sum as the key and pair list as value. 
  Then for this new pair list, hash (target - pair's sum) -> can find the other pair.
  But note that not all pairs are valid (e.g. some pairs share same index, and duplicates, etc.), 
  so needs to do some checks and pruning.

  This is a very Object-oriented solution which is easy to understand, but there are lots of room for improvement.
  The overall complexity is O(n^2), but in practice may not be faster than the standard O(n^3) solution.
*/
public class Solution {
    // binary deduction to 2sum problem - O(n ^ 2)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) return new ArrayList<>();
        
        Map<Integer, List<List<Integer>>> pairSumMap = pairSumMap(nums);
        
        // indexSet filters all duplicate index sequence
        Set<List<Integer>> indexSet = new HashSet<>();
        for (Map.Entry<Integer, List<List<Integer>>> entry : pairSumMap.entrySet()) {
            List<List<Integer>> target2 = pairSumMap.get(target - entry.getKey());
            if (target2 != null) {
                for (List<Integer> list1 : entry.getValue()) {
                    for (List<Integer> list2 : target2) {
                        List<Integer> tmp = merge(list1, list2);
                        if (tmp != null) {
                            indexSet.add(tmp);
                        }
                    }
                }
            }
        }
        
        // valueSet filters all duplicate value sequence
        Set<List<Integer>> valueSet = new HashSet<>();
        for (List<Integer> index : indexSet) {
            List<Integer> value = Arrays.asList(nums[index.get(0)], nums[index.get(1)], nums[index.get(2)], nums[index.get(3)]);
            Collections.sort(value);
            valueSet.add(value);
        }
        return new ArrayList<>(valueSet);
    }
    
    private Map<Integer, List<List<Integer>>> pairSumMap(int[] nums) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                List<List<Integer>> list = map.get(sum);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(sum, list);
                }
                
                list.add(Arrays.asList(i, j));
            }
        }
        
        return map;
    }
    
    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        Collections.sort(result);
        
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i) == result.get(i - 1)) {
                return null;
            }
        }
        
        return result;
    }
}

// O(n ^ 3)
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) return new ArrayList<>();
        
        List<List<Integer>> result = new ArrayList<>();
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // remove duplicates
            
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j - 1 >= i + 1 && nums[j] == nums[j - 1]) continue; // remove duplicates
                
                int m = j + 1;
                int n = nums.length - 1;
                
                while (m < n) {
                    int sum = nums[i] + nums[j] + nums[m] + nums[n];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                        m++;
                        while (m < n && nums[m] == nums[m - 1]) m++; // remove duplicates
                        n--;
                        while (m < n && nums[n] == nums[n + 1]) n--; // remove duplicates
                    } else if (sum < target) {
                        m++;
                        while (m < n && nums[m] == nums[m - 1]) m++; // remove duplicates
                    } else {
                        n--;
                        while (m < n && nums[n] == nums[n + 1]) n--; // remove duplicates
                    }
                }
            }
        }
        
        return result;
    }
}
