// 211. Add and Search Word - Data structure design
// https://leetcode.com/problems/add-and-search-word-data-structure-design/

/*  The root of the Trie is an empty character. When following a path in the Trie,
  the current character should be a child node of the current node.
  
  Pay attention to "*". Before calling searchHelper, check if the cur.children[i] is not null.
*/

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord = false;
    public TrieNode() {}
}

public class WordDictionary {
    TrieNode root = new TrieNode();
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode();
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }
    
    public boolean searchHelper(String word, int idx, TrieNode cur) {
        if (idx == word.length()) {
            return cur != null && cur.isWord;
        }
        
        char ch = word.charAt(idx);
        
        if (ch == '.') {
            boolean flag = false;
            for (int i = 0; i < 26; i++) {
                if (cur.children[i] != null && searchHelper(word, idx + 1, cur.children[i]) == true) {
                    flag = true;
                }
            }
            return flag;
        }
        
        if (cur.children[ch - 'a'] == null) return false;
        
        return searchHelper(word, idx + 1, cur.children[ch - 'a']);
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
