// 245. Shortest Word Distance III
// https://leetcode.com/problems/shortest-word-distance-iii/

public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (word1.equals(word2)) {
            int minDist = words.length;
            
            int lastIndex = -1;
            
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1)) {
                    if (lastIndex != -1) {
                        minDist = Math.min(minDist, i - lastIndex);
                    }
                    lastIndex = i;
                } 
            }
            
            return minDist;
        } else {
            int minDist = words.length;
            
            int lastIndexW1 = -1;
            int lastIndexW2 = -1;
            
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1)) {
                    if (lastIndexW2 != -1) {
                        minDist = Math.min(minDist, i - lastIndexW2);
                    }
                    lastIndexW1 = i;
                } else if (words[i].equals(word2)) {
                    if (lastIndexW1 != -1) {
                        minDist = Math.min(minDist, i - lastIndexW1);
                    }
                    lastIndexW2 = i;
                }
            }
            
            return minDist;
        }
    }
}
