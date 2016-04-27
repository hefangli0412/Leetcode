public class IncreasingSubsequenceSolver {
	
	// time - O(n * k)
	// space - O(k)
	public boolean incK(int[] a, int k) {
		if (a.length < k || k == 0) {
			return false;
		}
		if (k == 1) {
			return a.length > 0;
		}
		
		// M[i] denotes the smallest ending of all increasing 
		// subsequence of length i in a[0, ..., iteration]
		int[] M = new int[k];
		Arrays.fill(M, Integer.MAX_VALUE);
		
		for (int i = 0; i < a.length; i++) {
			// 前面k - 1个递增元素加上a[i]
			if (a[i] > M[k - 1]) {
				return true;
			}
			
			for (int j = k - 1; j > 1; j--) {
				if (a[i] < M[j] && a[i] > M[j - 1]) {
					M[j] = a[i];
				}
			}
			
			M[1] = Math.min(M[1], a[i]);
		}
		
		return false;
	}
	
}


public class IncreasingSubsequenceSolverTest {
	IncreasingSubsequenceSolver solver = new IncreasingSubsequenceSolver();
	
	@Test
	public void test() {
		// The longest increasing subsequence is [2, 3, 7, 101]
		int[] test1 = {10, 9, 2, 5, 3, 7, 101, 18};
		assertTrue(solver.incK(test1, 1));
		assertTrue(solver.incK(test1, 2));
		assertTrue(solver.incK(test1, 3));
		assertTrue(solver.incK(test1, 4));
		assertFalse(solver.incK(test1, 5));
		assertFalse(solver.incK(test1, 6));
	}

}
