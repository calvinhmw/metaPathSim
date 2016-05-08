package metaLessPathSim;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MetaPathSim {
    private NodeToNode nodeToNode;
    private HashMap<Integer, List<GraphNode>> graph;
    private HashMap<String, HashSet<Integer>> type;

    public MetaPathSim() {
        graph = new HashMap<Integer, List<GraphNode>>();
        initTypes("../data/type/term.txt");
    }

    private void initTypes(String filename) {
        BufferedReader br = new BufferedReader(null);
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        String line;

        //Read File Line By Line
        try {
            while ((line = br.readLine()) != null) {
                // Print the content on the console
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        try {br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        System.out.println("as");
    }
}
