package graphs;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

//Directed, unweighted graph (based on adjacency lists)
public class MyGraph<V> implements IGraph<V> {
    private Map<V, Node> adjLists = new HashMap<>();
    private int edgeSize;
    @Override
    public boolean addVertex(V vertex) {
        if(adjLists.containsKey(vertex)) {
            return false; //a duplicate vertex
        }
        adjLists.put(vertex, null);
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
        return adjLists.containsKey(vertex); // O(1) constant
    }

    @Override
    public boolean containsEdge(V source, V dest) {
        //preconditions
        if(!containsVertex(source) || !containsVertex(dest)) {
            return false;
        }

        Node list = adjLists.get(source);
        while(list != null) {
            if(list.data.equals(dest)) {
                return true; //found the end of the edge
            }
            list = list.next;
        }
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
        return adjLists.size();
    }

    @Override
    public int edgeSize() {
        return edgeSize;
    }

    @Override
    public boolean isEmpty() {
        return adjLists.isEmpty();
    }

    @Override
    public void clear() {
        adjLists = new HashMap<>();
        edgeSize = 0;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     * @apiNote In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * The string output is not necessarily stable over time or across
     * JVM invocations.
     * @implSpec The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     */
    @Override
    public String toString() {
        Set<V> keys = adjLists.keySet();
        String result = "";
        for(V vertex : keys) {
            result += vertex + " -> ";
            result += adjLists.get(vertex) + "\n";
        }
        return result;
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
