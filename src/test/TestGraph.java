package test;

import graphs.MyGraph;

public class TestGraph {
    public static void main(String[] args) {
        MyGraph<String> socialNetwork = new MyGraph<>();

        socialNetwork.addVertex("Billie");
        socialNetwork.addVertex("Tammy");
        socialNetwork.addVertex("Silvia");
        socialNetwork.addVertex("Bruno");

        socialNetwork.addEdge("Billie", "Tammy");
        socialNetwork.addEdge("Bruno", "Tammy");
        socialNetwork.addEdge("Silvia", "Bruno");
        socialNetwork.addEdge("Bruno", "Billie");
        socialNetwork.addEdge("Tammy", "Bruno");

        System.out.println(socialNetwork.containsEdge("Billie", "Tammy"));
        System.out.println(socialNetwork.containsEdge("Tammy", "Billie"));

        System.out.println(socialNetwork);
    }
}
