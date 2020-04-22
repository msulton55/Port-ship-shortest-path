import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {

    public static class Node {
        private int id = 0;
        private String name;
        private HashMap<Node, Edge> adjList;

        public Node(String name) {
            id = id + 1;
            this.name = name;
            adjList = new HashMap<Node, Edge>();
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAllAdjList() {
            List<Node> node = new ArrayList<>();
            List<Edge> edge = new ArrayList<>();
            for (Map.Entry<Node, Edge> entry : adjList.entrySet()) {
                node.add(entry.getKey()); 
                edge.add(entry.getValue());
            }
            return "Nodes: " + node + "\nEdges: " + edge;
        }

        public void setAdjList(Node node, Edge edge) {
            adjList.put(node, edge);
        }

        @Override
        public String toString() {
            return "\nNode ID:" + getId() + "\nNode Name: " + getName();
        }
    }

    public static class Edge {
        private int id = 0;
        private String connectionName;
        private int weight;

        public Edge(String connectionName, int weight) {
            id = id + 1;
            this.connectionName = connectionName;
            this.weight = weight;
        }

        public int getId() {
            return id;
        }

        public String getConnectionName() {
            return connectionName;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "\nEdge ID: " + getId() + "\nConnection name: " + getConnectionName() + "\nDistances: " + getWeight() + " KM";
        }
    }

    public static void main(String[] args) {
        Node tangerang = new Node("Tangerang");
        Node bsd = new Node("BSD");
        Node depok = new Node("Depok");
        Edge tng_bsd = new Edge("Tangerang-BSD", 24);
        Edge tng_depok = new Edge("Tangerang-Depok", 48);
        tangerang.setAdjList(bsd, tng_bsd);
        tangerang.setAdjList(depok, tng_depok);
        bsd.setAdjList(tangerang, tng_bsd);
        //System.out.println(tng_bsd); 
        System.out.println(tangerang.getAllAdjList());
        //System.out.println(bsd); 
        
    }
}