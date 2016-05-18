// https://leetcode.com/problems/palindrome-pairs/

public class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int wordIndex = -1;
        // see if the rest forms a palindrome, inclusive
        List<Integer> palinSuffix = new ArrayList<>();
        
        public TrieNode() {};
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        TrieNode root = new TrieNode();
        buildTrie(root, words);
        
        List<List<Integer>> result = new ArrayList<>();
        searchPalin(result, root, words);
        return result;
    }
    
    private void buildTrie(TrieNode root, String[] words) {
        for (int i = 0; i < words.length; i++) {
            insertWord(root, words[i], i);
        }
    }
    
    // insert word in reverse order
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
        
        // "" is also a palindrome
        root.palinSuffix.add(wordIndex);               
        root.wordIndex = wordIndex;
    }
    
    private void searchPalin(List<List<Integer>> result, TrieNode root, String[] words) {
        for (int i = 0; i < words.length; i++) {
            searchPalin(result, root, words[i], i);
        }
    }
    
    private void searchPalin(List<List<Integer>> result, TrieNode root, String word, int wordIndex) {
        for (int i = 0; i < word.length(); i++) {
            if (root.wordIndex != -1 && root.wordIndex != wordIndex && isPalin(word, i, word.length() - 1)) {
                result.add(Arrays.asList(wordIndex, root.wordIndex));
            }
            
            int idx = word.charAt(i) - 'a';
            if (root.children[idx] == null) {
                return;
            }
            root = root.children[idx];
        }
        
        for (int j : root.palinSuffix) {
            if (j != wordIndex) {
                result.add(Arrays.asList(wordIndex, j));
            }
        }
    }
    
    private boolean isPalin(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
