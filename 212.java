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
    String word = null;
}

public class Solution {
    int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        
        TrieNode root = buildTrie(words);
        
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(res, board, visited, i, j, root);
            }
        }
        
        return res;
    }
    
    private void dfs(List<String> res, char[][] board, boolean[][] visited, int x, int y, TrieNode cur) {
        TrieNode next = cur.next[board[x][y] - 'a'];
        
        if (next == null) {
            return;
        }
        
        if (next.word != null) {
            res.add(next.word);
            next.word = null; // de-duplicate
        }
        
        visited[x][y] = true;
        
        for (int i = 0; i < 4; i++) {
            int newX = x + DIRS[i][0];
            int newY = y + DIRS[i][1];
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && !visited[newX][newY]) {
                dfs(res, board, visited, newX, newY, next);
            }
        }
        
        visited[x][y] = false;
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
