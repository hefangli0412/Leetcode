// 269. Alien Dictionary
// https://leetcode.com/problems/alien-dictionary/

/* The description said the words are sorted lexicographically, 
  not the individual letters. As an example, consider these words 
  from the English dictionary:
  
  game,
  zebra,
  zoo
  
  See? The word zoo doesn't imply the ordering z < o.
  */

public class Solution {
    private final static int NUM_CHARS = 26;
    
    public String alienOrder(String[] words) {
        int[] degreeArr = new int[NUM_CHARS];
        List<Set<Integer>> childrenList = new ArrayList<Set<Integer>>();
        
        buildGraph(words, degreeArr, childrenList);
        
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < NUM_CHARS; i++) {
            if (degreeArr[i] == 0)  q.offer(i);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int cur = q.poll();
            degreeArr[cur]--;
            sb.append((char)('a' + cur));
            for (Integer child : childrenList.get(cur)) {
                degreeArr[child]--;
                if (degreeArr[child] == 0) {
                    q.offer(child);
                }
            }
            
        }
        
        for (int i = 0; i < NUM_CHARS; i++) {
            if (degreeArr[i] >= 0)  return "";
        }
        
        return sb.toString();
    }
    
    private void buildGraph(String[] words, int[] degreeArr, List<Set<Integer>> childrenList) {
        for (int i = 0; i < NUM_CHARS; i++) {
            degreeArr[i] = -1;
        }
        for (int i = 0; i < NUM_CHARS; i++) {
            childrenList.add(new HashSet<Integer>());
        }
        
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) { 
                if (degreeArr[c - 'a'] < 0) degreeArr[c - 'a'] = 0;
            }
            
            if (i > 0) {
                String w1 = words[i - 1];
                String w2 = words[i];
                int len = Math.min(w1.length(), w2.length());
                for (int j = 0; j < len; j++) {
                    if (w1.charAt(j) != w2.charAt(j)) {
                        int idx1 = w1.charAt(j) - 'a';
                        int idx2 = w2.charAt(j) - 'a';
                        
                        if (childrenList.get(idx1).add(idx2)) {
                            degreeArr[idx2]++;
                        }
                        break;
                    }
                }
            }
        }
    }
}
