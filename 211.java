// 211. Add and Search Word - Data structure design
// https://leetcode.com/problems/add-and-search-word-data-structure-design/

/*  The root of the Trie is an empty character. When following a path in the Trie,
  the current character should be a child node of the current node.
  
  Pay attention to "*". Before calling searchHelper, check if the cur.children[i] is not null.
*/



public class WordDictionary {
    
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
        
        public TrieNode() {};
    }
    
    TrieNode root = new TrieNode();
    
    public WordDictionary() {};

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];
        }
        cur.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }
    
    private boolean searchHelper(String word, TrieNode parent, int i) {
        if (i == word.length()) {
            return parent.isWord;
        }
        
        if (word.charAt(i) == '.') {
            for (TrieNode child : parent.children) {
                if (child != null) {
                    if (searchHelper(word, child, i + 1)) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        int idx = word.charAt(i) - 'a';
        if (parent.children[idx] == null) {
            return false;
        }
        return searchHelper(word, parent.children[idx], i + 1);
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
