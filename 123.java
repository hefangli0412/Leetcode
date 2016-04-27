// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

// Design an algorithm to find the maximum profit. You may complete at most two transactions.

public class BuyAndSellStockTwoSolver {
	// at most 2 transactions
	public int maxProfit(int[] prices) {
		if (prices.length < 2) {
            return 0;
        }
        
		int[][] states = {{Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0}, {Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0}}; // 0: 1 buy, 1: one buy/sell, 2: 2 buys/1 sell, 3, 2 buys/sells
		
        for(int i = 0; i < prices.length; i++) {
            states[i % 2][0] = Math.max(states[(i + 1) % 2][0], -prices[i]);
            states[i % 2][1] = Math.max(states[(i + 1) % 2][1], states[(i + 1) % 2][0] + prices[i]);
            states[i % 2][2] = Math.max(states[(i + 1) % 2][2], states[(i + 1) % 2][1] - prices[i]);
            states[i % 2][3] = Math.max(states[(i + 1) % 2][3], states[(i + 1) % 2][2] + prices[i]);
        }
        return states[(prices.length - 1) % 2][3];
    }
}

public class BuyAndSellStockTwoSolverTest {
	BuyAndSellStockTwoSolver solver = new BuyAndSellStockTwoSolver();
	
	@Test
	public void test1() {
		System.out.print((-1) % 2);
		assertEquals(38, solver.maxProfit(new int[] {5, -10, 1, 2, 6, -1, -2, 20, 2}));
	}
	
	public void test2() {
		assertEquals(7, solver.maxProfit(new int[] {3,2,6,5,0,3}));
	}

}
