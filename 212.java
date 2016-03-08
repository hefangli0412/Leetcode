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

class TrieNode {
    TrieNode[] next = new TrieNode[26];
    String word = null; // used to add to the result list
}

public class Solution {
    TrieNode root = new TrieNode();
    int[][] dirs = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        
        TrieNode root = buildTrie(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(res, board, i, j, root);
            }
        }
        
        return res;
    }
    
    private void dfs(List<String> res, char[][] board, int i, int j, TrieNode cur) {
        // // base case
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#') return;
        
        // check '#' before calling this method
        TrieNode next = cur.next[board[i][j] - 'a'];
        
        if (next == null) return;
        
        if (next.word != null) {
            res.add(next.word);
            next.word = null; // de-duplicate
            // No need to use HashSet to de-duplicate. Use "one time search" trie.
        }
        
        char original = board[i][j];
        board[i][j] = '#';
        
        for (int k = 0; k < dirs.length; k++) {
            int x = dirs[k][0];
            int y = dirs[k][1];
            dfs(res, board, i + x, j + y, next);
        }
        
        board[i][j] = original;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        
        for (String word : words) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (cur.next[ch - 'a'] == null) {
                    cur.next[ch - 'a'] = new TrieNode();
                }
                cur = cur.next[ch - 'a'];
            }
            cur.word = word;
        }
        
        return root;
    }
}
