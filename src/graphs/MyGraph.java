package graphs;

import java.util.Map;
import java.util.HashMap;

//Directed, unweighted graph (based on adjacency lists)
public class MyGraph<V> implements IGraph<V> {
    private Map<V, Node> adjLists = new HashMap<>();
    private int vertexSize;
    private int edgeSize;
    @Override
    public boolean addVertex(V vertex) {
        if(adjLists.containsKey(vertex)) {
            return false; //a duplicate vertex
        }
        adjLists.put(vertex, null);
        vertexSize++;
        return true;
    }

    @Override
    public boolean addEdge(V source, V dest) {
        if(!adjLists.containsKey(source) || !adjLists.containsKey(dest)) {
            return false; // vertices not in the map!
        }
        if(containsEdge(source, dest)) {
            return false; // edge already exists!
        }
        //look up the source in the map, place dest in the adjacency list
        Node current = adjLists.get(source);
        if(current == null) {
            //first adjecent vertex
            adjLists.put(source, new Node(dest));
        } else {
            //place a new node in the adjacency list
            Node destNode = new Node(dest);
            destNode.next = current;
            adjLists.put(source, destNode);
        }
        edgeSize++;
        return true;
    }

    @Override
    public boolean containsVertex(V vertex) {
        return false;
    }

    @Override
    public boolean containsEdge(V source, V dest) {
        return false;
    }

    @Override
    public boolean removeVertex(V vertex) {
        return false;
    }

    @Override
    public boolean removeEdge(V source, V dest) {
        return false;
    }

    @Override
    public int vertexSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    private class Node {
        private V data;
        private Node next;

        public Node(V data) {
            this.data = data;
        }

        public String toString() {
            String result = data.toString();

            if(next == null) {
                return result + " -> null";
            } else {
                return result + " -> " + next.toString();
            }
        }
    }
}
