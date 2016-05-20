// 318. Maximum Product of Word Lengths

/* Solution 1 - pruning */
// 先把bit map计算出来，compare的时候就不用计算两遍了
public class Solution {
	public int maxProduct(String[] words) {
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String word1, String word2) {
				return Integer.compare(word2.length(), word1.length());
			}
		});

		int[] bitMap = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				bitMap[i] |= 1 << (words[i].charAt(j) - 'a');
			}
		}

		int result = 0;
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < i; j++) {
				int product = words[i].length() * words[j].length();
				if (product <= result) {
					break;
				}
				if ((bitMap[i] & bitMap[j]) == 0) {
					result = Math.max(result, product);
					break;
				}
			}
		}
		return result;
	}
}

/* Solution 2 - Dijkstra */
// slower
public class WordLengthMaxProductSolver {

	static class Pair implements Comparable<Pair> {
		int word1Idx;
		int word2Idx;
		int product;

		public Pair(int word1Idx, int word2Idx, int product) {
			this.word1Idx = word1Idx;
			this.word2Idx = word2Idx;
			this.product = product;
		}

		@Override
		public int compareTo(Pair another) {
			if (product == another.product) {
				return 0;
			}
			return product > another.product ? -1 : 1;
		}
	}

	public int maxProduct(String[] words) {
		if (words == null || words.length < 2) {
			return 0;
		}

		sortByLength(words);

		int num = words.length;
		boolean[][] visited = new boolean[num][num];
		PriorityQueue<Pair> maxHeap = new PriorityQueue<>();
		// initial state
		maxHeap.offer(new Pair(0, 1, words[0].length() * words[1].length()));
		visited[0][1] = true;

		while (!maxHeap.isEmpty()) {
			Pair cur = maxHeap.poll();
			// termination condition
			String word1 = words[cur.word1Idx];
			String word2 = words[cur.word2Idx];
			if (cur.word1Idx < cur.word2Idx && !shareCommonLetters(word1, word2)) {
				return cur.product;
			}

			if (cur.word1Idx + 1 < num && !visited[cur.word1Idx + 1][cur.word2Idx]) {
				maxHeap.offer(new Pair(cur.word1Idx + 1, cur.word2Idx,
						words[cur.word1Idx + 1].length() * words[cur.word2Idx].length()));
				visited[cur.word1Idx + 1][cur.word2Idx] = true;
			}
			if (cur.word2Idx + 1 < num && !visited[cur.word1Idx][cur.word2Idx + 1]) {
				maxHeap.offer(new Pair(cur.word1Idx, cur.word2Idx + 1,
						words[cur.word1Idx].length() * words[cur.word2Idx + 1].length()));
				visited[cur.word1Idx][cur.word2Idx + 1] = true;
			}
		}

		return 0;
	}

	private boolean shareCommonLetters(String word1, String word2) {
		int bitSet = 0;
		for (int i = 0; i < word1.length(); i++) {
			bitSet |= 1 << (word1.charAt(i) - 'a');
		}

		for (int i = 0; i < word2.length(); i++) {
			int idx = word2.charAt(i) - 'a';
			if ((bitSet & (1 << idx)) != 0) {
				return true;
			}
		}
		return false;
	}

	private void sortByLength(String[] words) {
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String word1, String word2) {
				return Integer.compare(word2.length(), word1.length());
			}
		});
	}

}


