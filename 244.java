// 244. Shortest Word Distance II
// https://leetcode.com/problems/shortest-word-distance-ii/

public class WordDistance {

    private Map<String, List<Integer>> idxMap;
    
    public WordDistance(String[] words) {
        idxMap = new HashMap<String, List<Integer>>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> l = idxMap.get(words[i]);
            if (l == null) {
                l = new ArrayList<Integer>();
                idxMap.put(words[i], l);
            }
            
            l.add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = idxMap.get(word1);
        List<Integer> l2 = idxMap.get(word2);
        
        int minDist = Integer.MAX_VALUE;
        for (int i : l1) {
            for (int j : l2) {
                minDist = Math.min(minDist, Math.abs(i - j));
            }
        }
        
        return minDist;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
