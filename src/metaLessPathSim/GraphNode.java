package metaLessPathSim;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphNode {
    private int name;
    private String type;
    private NodeToNode nodeToNode;
    private Queue<Package> receivedPackages;
    private Queue<Integer> receivedPackagesFrom;

    public GraphNode(int name, String type, NodeToNode nodeToNode) {
        this.name = name;
        this.type = type;
        this.nodeToNode = nodeToNode;
        this.receivedPackages = new LinkedList<Package>();
        this.receivedPackagesFrom = new LinkedList<Integer>();
    }

    public int getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public void sendPackageToNode(Package p, GraphNode neighbor) {
        Package p1 = new Package(p);
        neighbor.receivedPackages.add(p1);
        neighbor.receivedPackagesFrom.add(this.name);
    }

    public void onReceivePackage(List<GraphNode> neighbors) {
        if(receivedPackages.isEmpty()) return;
        Package p = receivedPackages.poll();
        int from = receivedPackagesFrom.poll();
        int sender = p.name;
        String type = p.type;
        String phaseLabel = p.phaseLabel;
        if (phaseLabel.equals("POP")) {
            p.popStack();
            if (p.emptyStack()) {
                nodeToNode.incrementScore(sender, "", this.name);
            } else {
                // forward packet to neighbors
                for(GraphNode n : neighbors) {
                    if(n.getName()!=from && n.getType().equals(p.topStack())) {
                        //construct a new packet and forward to the neighbor
                        sendPackageToNode(p, n);
                    }
                }
            }
        }else if(phaseLabel.equals("PUSH")){
            if(p.sizeOfStack()<=Config.maxMetaLen/2) {
                // this means we can continue pushing
                p.pushTypeToStack(this.type);
                for(GraphNode n : neighbors) {
                    if(n.getName()!=from) {
                        //construct a new push packet and forward to the neighbor
                        sendPackageToNode(p, n);
                    }
                }
                Package temp_p = new Package(p);
                temp_p.setPhaseLabel("POP");
                nodeToNode.incrementScore(sender, this.type, this.name);
                nodeToNode.incrementScore(this.name, type, sender);
                for(GraphNode n : neighbors) {
                    if(n.getName()!=from && n.getType().equals(temp_p.topStack()))  {
                        //construct a new pop packet and forward to the neighbor
                        sendPackageToNode(temp_p, n);
                    }
                }
            }
        }else{
            System.out.println("Unrecognized phase label");
        }
    }

    @Override
    public String toString() {
        return String.format("Node %d : %s", this.getName(), this.getType());
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
