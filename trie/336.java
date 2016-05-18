// https://leetcode.com/problems/palindrome-pairs/

/* https://leetcode.com/discuss/91429/solution-with-structure-total-number-words-average-length

分三种情况 1. target word is longer, 好说，检查一下the rest of target word 
2. the same length, 好说，但是注意是不是target word自己
3. target word is shorter, 不好处理，只能在precompute的时间就保存这个信息，这样建trie的时候时间就变成k * m ^ 2

注意： empty string, same word
*/

public class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int wordIndex = -1;
        List<Integer> palinSuffix = new ArrayList<>();
        
        public TrieNode() {};
    }
    
    // O(k * m^2)
    public List<List<Integer>> palindromePairs(String[] words) {
        TrieNode root = new TrieNode();
        buildTrie(root, words);
        
        List<List<Integer>> result = new ArrayList<>();
        searchPalin(result, root, words);
        return result;
    }
    
    // O(k * m^2)
    private void buildTrie(TrieNode root, String[] words) {
        for (int i = 0; i < words.length; i++) {
            insertWord(root, words[i], i);
        }
    }
    
    // insert word in reverse order
    // O(m^2)
    private void insertWord(TrieNode root, String word, int wordIndex) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int idx = word.charAt(i) - 'a';
            if (root.children[idx] == null) {
                root.children[idx] = new TrieNode();
            }
            if (isPalin(word, 0, i)) {
                root.palinSuffix.add(wordIndex);
            }
            root = root.children[idx];
        }
         
        root.wordIndex = wordIndex;
    }
    
    // O(k * m^2)
    private void searchPalin(List<List<Integer>> result, TrieNode root, String[] words) {
        for (int i = 0; i < words.length; i++) {
            searchPalin(result, root, words[i], i);
        }
    }
    
    // O(m^2)
    private void searchPalin(List<List<Integer>> result, TrieNode root, String word, int wordIndex) {
        // target word is longer
        for (int i = 0; i < word.length(); i++) {
            if (root.wordIndex != -1 && isPalin(word, i, word.length() - 1)) {
                result.add(Arrays.asList(wordIndex, root.wordIndex));
            }
            
            int idx = word.charAt(i) - 'a';
            if (root.children[idx] == null) {
                return;
            }
            root = root.children[idx];
        }
        
        // same length
        if (root.wordIndex != -1 && root.wordIndex != wordIndex) {
            result.add(Arrays.asList(wordIndex, root.wordIndex));
        }
        
        // target word is shorter
        for (int i : root.palinSuffix) {
            result.add(Arrays.asList(wordIndex, i));
        }
    }
    
    // O(m)
    private boolean isPalin(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}


/* hashmap */

public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String target = words[i];
            
            for (int j = 0; j <= target.length(); j++) {
                String first = target.substring(0, j);
                String second = target.substring(j);
                if (isPalin(first)) {
                    StringBuilder sb = new StringBuilder(target.substring(j));
                    String candidate = sb.reverse().toString();
                    Integer candidateIdx = map.get(candidate);
                    if (candidateIdx != null && candidateIdx != i) {
                        result.add(Arrays.asList(candidateIdx, i));
                    }
                }
                if (isPalin(second)) {
                    StringBuilder sb = new StringBuilder(target.substring(0, j));
                    String candidate = sb.reverse().toString();
                    Integer candidateIdx = map.get(candidate);
                    // deduplicate
                    if (candidateIdx != null && candidateIdx != i && second.length() > 0) {
                        result.add(Arrays.asList(i, candidateIdx));
                    }
                }
            }
        }
        
        return result;
    }
    
    // O(m^2)
    private boolean isPalin(String word) {
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
