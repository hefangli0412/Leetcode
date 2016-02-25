// 170. Two Sum III - Data structure design
// https://leetcode.com/problems/two-sum-iii-data-structure-design/

// pre-process solution
public class TwoSum {
    
    Set<Integer> sumSet = new HashSet<>();
    Set<Integer> numSet = new HashSet<>();

    // Add the number to an internal data structure.
	public void add(int number) {
        if (numSet.contains(number)) {
            sumSet.add(number * 2);
        } else {
    	    for (Integer num : numSet) {
    	        sumSet.add(num + number);
    	    }
    	    
    	    numSet.add(number);
        }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    return sumSet.contains(value);
	}
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);

// post-process  --  pass
public class TwoSum {
    
    Map<Integer, Integer> countMap = new HashMap<>();

    // Add the number to an internal data structure.
	public void add(int number) {
	    Integer count = countMap.get(number);
        if (count == null) {
            countMap.put(number, 1);
        } else {
            countMap.put(number, count + 1);
        }
	}
	
    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
	        int num1 = entry.getKey();
	        int num2 = value - num1;
	        
	        if (num1 == num2 && entry.getValue() > 1
            || num1 != num2 && countMap.containsKey(num2)) {
	            return true;
	        }
	    }
	    
	    return false;
	}
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
