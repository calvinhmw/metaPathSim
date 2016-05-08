package metaLessPathSim;

import java.util.HashMap;

public class NodeToNode {
    private HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>> nodeToNode;

    public NodeToNode() {
        this.nodeToNode = new HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>>();
    }

    public Integer getScore(int sender, String midPointType, int receiver) {
        if(!nodeToNode.containsKey(sender) || nodeToNode.get(sender).containsKey(midPointType) || !nodeToNode.get(sender).get(midPointType).containsKey(receiver)) {
            return 0;
        }
        return nodeToNode.get(sender).get(midPointType).get(receiver);
    }

    public void incrementScore(int sender, String midPointType, int receiver) {
        Integer count = getScore(sender, midPointType, receiver) + 1;
        if(!nodeToNode.containsKey(sender)) {
            nodeToNode.put(sender, new HashMap<String, HashMap<Integer, Integer>>());
        }
        if(!nodeToNode.get(sender).containsKey(midPointType)) {
            nodeToNode.get(sender).put(midPointType, new HashMap<Integer, Integer>());
        }
        nodeToNode.get(sender).get(midPointType).put(receiver, count);
    }

    public void insertScore(int sender, String midPointType, int receiver, Integer count) {
        if(!nodeToNode.containsKey(sender)) {
            nodeToNode.put(sender, new HashMap<String, HashMap<Integer, Integer>>());
        }
        if(!nodeToNode.get(sender).containsKey(midPointType)) {
            nodeToNode.get(sender).put(midPointType, new HashMap<Integer, Integer>());
        }
        nodeToNode.get(sender).get(midPointType).put(receiver, count);
    }

//    public static void main(String[] args) {
//        NodeToNode n = new NodeToNode();
//        System.out.println(n);
//    }
}
