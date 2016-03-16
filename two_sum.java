
// https://docs.google.com/document/d/10v5ieA0Q94i1emAKARSpt6KpMk6ldPMHejv1T0wqUPw/edit

1. sorted array, two pointers, no duplicates, sum == target
{1, 1, 3, 3}, target = 4,  return 1

public int twoSum(int[] array, int target) {
  if (array == null || array.length == 0) {
  	return 0;
  }
  
  int count = 0;
  int left = 0;
  int right = array.length - 1;
  while (left < right) {
  	int sum = array[left] + array[right];
    if (sum == target) {
    	count++;
    	left++;
    while (left < right && array[left] == array[left - 1]) {
    	left++;
    }
    right--;
    } else if (sum < target) {
    	left++;
    } else {
     	right--;
    }
  }
  return count;
}

input = { 2, 3, 3, 3, 4, 4, 4, 5, 5, 5 }, 8
output = 2

2. sorted array, two pointers, count duplicates, sum < target

public int twoSum(int[] array, int target) {
  if (array == null || array.length == 0) {
  	return 0;
  }
  
  int count = 0;
  int left = 0;
  int right = array.length - 1;
  while (left < right) {
  	int sum = array[left] + array[right];
    if (sum < target) {
    	count += right - left;
    	left++;
    } else {
    	right--;
    }
  }
  return count;
}

input = { 2, 3, 3, 3, 4, 4, 4, 5, 5, 5 }, 8
output = 21

3. unsorted array, hashmap, count duplicates, sum == target
{1, 1, 3, 3}, target = 4,  return 1

public int twoSum(int[] array, int target) {
  if (array == null || array.length == 0) {
  	return 0;
  }
  
  Map<Integer, Integer> map = new HashMap<>();
  int result = 0;
  
  for (int num : array) {
    Integer matchCount = map.get(target - num);
    if (matchCount != null) {
    	result += matchCount;
    }
    Integer count = map.get(num);
    if (count == null) {
    	map.put(num, 1);
    } else {
    	map.put(num, count + 1);
    }
  }
  
  return result;
}

input = { 2, 3, 3, 3, 4, 4, 4, 5, 5, 5 }, 8
output = 12

4. unsorted array, hashmap, no duplicates, sum == target
{1, 1, 2, 2, 3, 3}, target = 4,  return 2

// The map itself can tell us if we have encountered this number before, 
// the value equals to half of the target should be treated separately

public int twoSum(int[] array, int target) {
  if (array == null || array.length == 0) {
  	return 0;
  }
  
  int halfValueCount = 0;
  
  Map<Integer, Integer> map = new HashMap<>();
  int result = 0;
  
  for (int num : array) {
  	if (target % 2 == 0 && num == target / 2) {
    	halfValueCount++;
    	if (halfValueCount == 2) {
    		result++;
    	}
    	continue;
    	}
    if (map.containsKey(num)) {
    	continue;
    }
    Integer matchCount = map.get(target - num);
    if (matchCount != null) {
    	result ++;
    }
    map.put(num, 1);
  }
  
  return result;
}

input = { 2, 3, 3, 3, 4, 4, 4, 5, 5, 5 }, 8
output = 2

5. sorted array, two pointers, count duplicates, sum == target


public int twoSum(int[] array, int target) {
  if (array == null || array.length == 0) {
  	return 0;
  }
  
  int count = 0;
  int left = 0;
  int right = array.length - 1;
  int leftCount = 1;
  int rightCount = 1;
  while (left < right) {
  	// the value equals to half of the target should be treated separately
    if (target %2 == 0 && array[left] == target / 2) {
      left++;
      while (left <= right && array[left] == array[left - 1]) {
      	leftCount++;
      	left++;
      }
      count += leftCount * (leftCount - 1) / 2;
      break; // no need to move anymore
    }
    if (target %2 == 0 && array[right] == target / 2) {
      right--;
      while (left <= right && array[right] == array[right + 1]) {
      	rightCount++;
      	right--;
      }
      count += rightCount * (rightCount - 1) / 2;
      break; // no need to move anymore
    }
    
    int sum = array[left] + array[right];
    if (sum == target) {
    	left++;
      while (left < right && array[left] == array[left - 1]) {
      	leftCount++;
      	left++;
      }
      right--;
      while (left < right && array[right] == array[right + 1]) {
      	rightCount++;
      	right--;
      }
      count += leftCount * rightCount;
      leftCount = 1;
      rightCount = 1;
    } else if (sum < target) {
    	left++;
    } else {
     	right--;
    }
  }
  return count;
}

input = { 2, 3, 3, 3, 4, 4, 4, 5, 5, 5 }, 8
output = 12

6. unsorted array, hashmap, no duplicates, sum < target

treemap
// 好像每步时间复杂度都要logn，还浪费空间，不如用two pointers

7. sorted array, two pointers, no duplicates, sum < target 

Deduplicate first.

