
Next taller person

Array of integers representing the heights of a list of people, for each of the people, find the closest taller person on the right side.
Example, heights = {1, 4, 2, 5, 7, 3}, return {1, 3, 3, 4, -1, -1}

单调stack/deque, property: 
- find the closest element  <or> the current position on left and right side
- increasing/decreasing order

1(2), 4(3), 2(3), 5(4), 7(-1), 3(-1)

// from right to left, maintain an decreasing stack
{}
{3(5)}
{7(4)}
{7(4), 5(3)}
{7(4), 5(3), 2(2)}
{7(4), 5(3), 4(1)}

public int[] nextTallerPerson(int[] array) {
	if (array == null || array.length == 0) {
    return new int[0];
	}

	int[] res = new int[array.length];
  Deque<Integer> stack = new LinkedList<>();
  for (int i = array.length - 1; i >= 0; i--) {
    // because we will push i to stack, no need to consider elements 
    // that are smaller than array[i] but have higher indices than i
    while (!stack.isEmpty() && array[i] >= array[stack.peek()]) {
    stack.pop();	
  }

  if (stack.isEmpty()) {
  	res[i] = -1;
  } else {
  	res[i] = stack.peek();
  }
  
  stack.push(i);
  }
  
  return res;
}
