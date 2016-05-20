// Repeated DNA Sequences

public class RepeatedDNASolver {
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> result = new ArrayList<>();
		if (s.length() <= 10) {
			return result;
		}

		Set<Integer> seen = new HashSet<>();
		Set<Integer> added = new HashSet<>();

		Map<Character, Integer> charMap = new HashMap<>();
		charMap.put('A', 0);
		charMap.put('C', 1);
		charMap.put('G', 2);
		charMap.put('T', 3);

		int cur = 0;

		for (int i = 0; i < s.length(); i++) {
			cur <<= 2;
			cur |= charMap.get(s.charAt(i));
			cur &= (1 << 20) - 1;
			if (i >= 9) {
				if (!seen.add(cur) && added.add(cur)) {
					result.add(s.substring(i - 9, i + 1));
				}
			}
		}
		return result;
	}
}
