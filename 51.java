https://leetcode.com/problems/n-queens-ii/

public class NQueensSolver {
  public List<List<Integer>> solveNQueens(int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> curResult = new ArrayList<Integer>();
    solveNQueensHelper(n, curResult, result);
    return result;
  }
    
  private void solveNQueensHelper(int n, List<Integer> curResult, List<List<Integer>> result) {
    if (curResult.size() == n) {
      result.add(new ArrayList<Integer>(curResult));
      return;
    }
    
    // for each level/row, try all possible positions
    for (int i = 0; i < n; i++) {
      curResult.add(i);
      if (isValidQueen(curResult)) {  
          solveNQueensHelper(n, curResult, result);
      }
      curResult.remove(curResult.size() - 1);
    }
  }
    
  private boolean isValidQueen(List<Integer> curResult) {
    int lastIdx = curResult.size() - 1;
    for (int k = 0; k < lastIdx; k++) {
      if (curResult.get(k) == curResult.get(lastIdx) || lastIdx - k == Math.abs(curResult.get(lastIdx) - curResult.get(k))) {
        return false;
      }
    }
    return true;
  }
}


public class NQueensSolverTest {
	NQueensSolver solver = new NQueensSolver();

	@Test
	public void test1() {
		List<List<Integer>> res = solver.solveNQueens(9);
		assertEquals(352, res.size());
		
		res = solver.solveNQueens(13);
		assertEquals(73712, res.size());
	}

	@Test
	public void test2() {
		List<List<Integer>> res = solver.solveNQueens(1);
		assertEquals(1, res.size());

		res = solver.solveNQueens(2);
		assertEquals(0, res.size());
		
		res = solver.solveNQueens(3);
		assertEquals(0, res.size());
		
		res = solver.solveNQueens(4);
		assertEquals(2, res.size());
	}
}
