public class DisjointSubarraysSolver {
	public int dp3(int[] a, int k) {
		int[] prefix = new int[a.length];
		prefix[0] = a[0];
		for (int i = 1; i < a.length; i++) {
			prefix[i] = prefix[i - 1] + a[i];
		}		
		
		int[] pre = new int[a.length];
		int[] cur = new int[a.length];

		for (int i = 1; i <= k; i++) {
			// swap pre and cur
			int[] tmp = pre;
			pre = cur;
			cur = tmp;
			
			cur[0] = a[0];
			int localMax = pre[0] - prefix[0];
			for (int j = 1; j < a.length; j++) {
				cur[j] = Math.max(cur[j - 1], prefix[j] + localMax);
				// 下一个循环做减法时，j + 1可能是last subarray的first index
				localMax = Math.max(localMax, pre[j] - prefix[j]);
			}
		}
		return cur[a.length - 1];
	}
}


public class DisjointSubarraysSolverTest {

	DisjointSubarraysSolver solver = new DisjointSubarraysSolver();
	
	@Test
	public void test() {
		int[] test1 = {5, -10, 1, 2, 6, -1, -2, 20, 2};
		assertEquals(28, solver.dp3(test1, 1));
		assertEquals(33, solver.dp3(test1, 2));
	}

}
