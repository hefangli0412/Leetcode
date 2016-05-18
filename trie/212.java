// 212. Word Search II
// https://leetcode.com/problems/word-search-ii/

/* 
You would need to optimize your backtracking(dfs) to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? 
How about a Trie?

TrieNode, the structure itself, is all we need. Like recursion, right? Search and startsWith are useless.

How can we not go back to the previous word?
Modifying board[i][j] = '#' and revert it back later.

*/

public class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;

        public TrieNode() {};
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        buildTrie(root, words);
        
        List<String> result = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWords(result, board, i, j, root, visited);
            }
        }
        return result;
    }
    
    private void findWords(List<String> result, char[][] board, int i, int j, TrieNode root, boolean[][] visited) {
        if (visited[i][j]) {
            return;
        }
        
        int idx = board[i][j] - 'a';
        if (root.children[idx] == null) {
            return;
        }
        root = root.children[idx];
        
        if (root.word != null) {
            result.add(root.word);
            root.word = null; // deduplicate
        }
        
        visited[i][j] = true;
        
        for (int[] dir : new int[][] {{1,0},{0,1},{-1,0},{0,-1}}) {
            int new_i = i + dir[0];
            int new_j = j + dir[1];
            if (new_i >= 0 && new_i < board.length && new_j >= 0 && new_j < board[0].length) {
                findWords(result, board, new_i, new_j, root, visited);
            }
        }
        
        visited[i][j] = false;
    }
    
    // O(k * m)
    private void buildTrie(TrieNode root, String[] words) {
        for (int i = 0; i < words.length; i++) {
            insertWord(root, words[i], i);
        }
    }
    
    // O(m)
    private void insertWord(TrieNode root, String word, int wordIndex) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int idx = word.charAt(i) - 'a';
            if (root.children[idx] == null) {
                root.children[idx] = new TrieNode();
            }
            root = root.children[idx];
        }
         
        root.word = word;
    }
}
