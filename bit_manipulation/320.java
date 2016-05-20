// 320. Generalized Abbreviation

public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        
		int n = word.length();
		// get 2's power
		int max = (1 << n) - 1;

		for (int i = 0; i <= max; i++) {
            StringBuilder sb = new StringBuilder();
            int zeroCount = 0;
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					if (zeroCount > 0) {
					    sb.append(zeroCount);
					    zeroCount = 0;
					}
					sb.append(word.charAt(j));
				} else {
				    zeroCount++;
				}
			}
			if (zeroCount > 0) {
			    sb.append(zeroCount);
			}
			result.add(sb.toString());
		}
		return result;
	}
}
