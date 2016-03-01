// 133. Clone Graph
// https://leetcode.com/problems/clone-graph/

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
 
 /* Note that using Integer as the key of the map can save some space */
 
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode copiedNode = new UndirectedGraphNode(node.label);
        map.put(node.label, copiedNode);
        
        cloneGraph(map, node);
        
        return copiedNode;
    }
    
    // The input node is already in the map, expand it.
    private void cloneGraph(Map<Integer, UndirectedGraphNode> map, UndirectedGraphNode node) {
        UndirectedGraphNode copiedNode = map.get(node.label);
        
        for (UndirectedGraphNode nei : node.neighbors) {
            UndirectedGraphNode copiedNei = map.get(nei.label);
            if (copiedNei == null) {
                copiedNei = new UndirectedGraphNode(nei.label);
                map.put(nei.label, copiedNei);
                cloneGraph(map, nei);
            }
            copiedNode.neighbors.add(copiedNei);
        }
    }
}
