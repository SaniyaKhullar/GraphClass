//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Graph.java
// Files: P4: PackageManager.java, Graph.Java, PackageManagerTest.java, GraphTest.java
// Course: CS 400, Section 001, Fall 2019

/**
 * Please note that this class is Saniya's implementation of the PackageManager class, which uses
 * her Graph class to build a dependency graph that has packages as the vertices and focuses on the
 * relationships among the nodes.
 * 
 * @author Saniya Khullar
 * @param
 * @param
 */



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


/**
 * Filename: Graph.java Project: p4 Authors:
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {


  /**
   * Private inner class by Saniya holds the information that we need for each vertex node in the
   * graph
   * 
   * @author Saniya Khullar :)
   *
   * @param <String>
   */
  private class Graphnode<String> {
    String vertex; // data: node label, reference
    List<Graphnode<String>> outEdgesList; // neighbors...out edges: this stores an adjacency list or successors (Where we can get to from the
                                          // current node), the out edges (current edge -->)
    List<Graphnode<String>> inEdgesList; // in edges: this stores a list of edges that come into this node (--> current edge), the in edges

    // please note that this is the information that each vertex has.

    /**
     * Please note this constructor of the Graphnode class
     * 
     * @param vertex
     */
    private Graphnode(String vertex) {
      this.vertex = vertex; // updates the vertex to hold this vertex
      outEdgesList = new ArrayList<Graphnode<String>>(); // the ArrayList implementation is used
      inEdgesList = new ArrayList<Graphnode<String>>();
    }


    private void setInEdgesList(List<Graphnode<String>> inEdgesList) {
      this.inEdgesList = inEdgesList;

    }



    private void setOutEdgesList(List<Graphnode<String>> outEdgesList) {
      this.outEdgesList = outEdgesList;

    }

    /**
     * Getter method that returns the vertex of a node
     * 
     * @return
     */
    private String getVertex() {
      return vertex;

    }


    /**
     * Please note that this method adds an edge between the current node and the node that it is going
     * out to (current node --> nodeCurrentNodeIsGoingTo)
     * 
     * @param node
     */
    private void addOutEdge(Graphnode<String> nodeCurrentNodeIsGoingTo) {
      outEdgesList.add(nodeCurrentNodeIsGoingTo);
    }


    /**
     * Please note that this method adds an edge between another node that is coming into the current
     * node and the current node (parent node --> current node) that is: previousNode --> current node
     * 
     * @param node
     */
    private void addInEdge(Graphnode<String> previousNode) {
      inEdgesList.add(previousNode);
    }


    // getter method that returns the list of outgoing edges
    private List<Graphnode<String>> getOutEdgesList() {

      // maybe return all edges (both directions)
      return outEdgesList;
    }

    // getter method that returns the list of incoming edges
    private List<Graphnode<String>> getInEdgesList() {

      // maybe return all edges (both directions)
      return inEdgesList;
    }


  }


  // outer class

  List<Graphnode<String>> vertexList; // stores the vertices
  int orderOfGraph = 0; // number of vertices in this graph.
  int sizeOfGraph = 0; // number of edges in this graph

  /*
   * Default no-argument constructor
   */
  public Graph() {
    vertexList = new ArrayList<Graphnode<String>>(); // holds the nodes of vertices in graph
    orderOfGraph = 0;
    sizeOfGraph = 0;
  }



  /**
   * private helper method to find the vertexNode that has that vertex info in the graph finding the
   * node in the graph given the vertex
   * 
   * @param vertex
   * @return
   */
  private Graphnode<String> findVertexNode(String vertex) {
    if (isVertexInGraph(vertex) == false) {
      return null; // the Graphnode is NOT in the graph
    } else {
      Graphnode<String> nodeWithVertex = null;
      for (int i = 0; i < vertexList.size(); i++) {
        Graphnode<String> currentNode = vertexList.get(i); // current graph node in the list
        String currentVertex = currentNode.getVertex();
        if (vertex.equals(currentVertex)) {
          nodeWithVertex = currentNode;
        }

      }
      return nodeWithVertex;

    }
  }

  // please find the vertexNode with that vertex



  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void addVertex(String vertex) {
    // if vertex is null, method ends without adding a vertex. No exception thrown
    if (vertex == null) {
      return;
    }
    // please make sure the graph is not empty before we hunt through it
    if (isVertexInGraph(vertex) == true) {
      return; // the vertex is already in the graph, so we just return
    } else {
      // this is a new vertex:
      Graphnode vertexNodeToAdd = new Graphnode(vertex); // creating a node object
      vertexList.add(vertexNodeToAdd); // please add the vertex to our list of vertices for our graph
      orderOfGraph = orderOfGraph + 1; // added a vertex to graph, so we increase the size
    }
  }


  /**
   * private helper method by Saniya that sees if a vertex is in the graph:
   * 
   * @param vertex
   * @return
   */
  private boolean isVertexInGraph(String vertex) {
    if (vertex == null) {
      return false;
    }

    else if (vertexList == null || vertexList.size() == 0) {
      return false; // graph is empty so no vertices in it! Thus, this vertex is not in graph
    }
    // please make sure the graph is not empty before we hunt through it
    else {
      Graphnode<String> vertexToFind = new Graphnode(vertex);

      // please see if vertex is already in the graph's vertexList:
      for (int i = 0; i < vertexList.size(); i++) {
        Graphnode<String> currentNode = vertexList.get(i);
        String currentVertex = currentNode.getVertex();
        if (currentVertex.equals(vertex)) {
          // the vertex is already in the list, so we would NOT add it again
          return true;
        }
      }
      return false; // if we get here, this means that the vertex is NOT in the graph
    }


  }



  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void removeVertex(String vertex) {
    // if vertex is null, method ends without removing vertex. No exception thrown
    if (vertex == null) {
      return;
    }
    // please make sure the graph is not empty before we hunt through it
    if (isVertexInGraph(vertex) == false) {
      return; // the vertex is NOT in the graph, so we just return
    }



    // if we are here, the node is in the graph, and we can remove it
    Graphnode<String> vertexNodeToRemove = findVertexNode(vertex); // this is the node we want to remove from the graph
    List<Graphnode<String>> inEdgesListOfNodeToRemove =
        vertexNodeToRemove.getInEdgesList();

    List<Graphnode<String>> outEdgesListOfNodeToRemove =
        vertexNodeToRemove.getOutEdgesList();

    int numOfEdgesRemoved = 0; // the # of edges that were removed


    vertexList.remove(vertexNodeToRemove); // remove that node from vertex list


    // loop through its in edges list and remove those edges from the corresponding
    // out edges list of those other vertices

    for (int i = 0; i < inEdgesListOfNodeToRemove.size(); i++) {
      Graphnode<String> inEdgeNode = inEdgesListOfNodeToRemove.get(i); // one of the nodes that comes into the node to remove
      String incomingVertex = inEdgeNode.getVertex();
      List<Graphnode<String>> inEdgesOutEdgeList = inEdgeNode.getOutEdgesList(); // list of nodes that that node goes to


      inEdgesOutEdgeList.remove(vertexNodeToRemove); // removing the vertex Node from this
      numOfEdgesRemoved = numOfEdgesRemoved + 1; // one edge removed


      inEdgeNode.setOutEdgesList(inEdgesOutEdgeList); 

    }


    // loop through its out edges list and remove those edges from the corresponding
    // in edges list of those other vertices
    for (int j = 0; j < outEdgesListOfNodeToRemove.size(); j++) {
      Graphnode<String> outEdgeNode = outEdgesListOfNodeToRemove.get(j);
      String outgoingVertex = outEdgeNode.getVertex();
      List<Graphnode<String>> outEdgesInEdgeList = outEdgeNode.getInEdgesList();

      outEdgesInEdgeList.remove(vertexNodeToRemove); // removing the vertex Node from this
      numOfEdgesRemoved = numOfEdgesRemoved + 1;

      outEdgeNode.setInEdgesList(outEdgesInEdgeList); 

    }

    // for each in-edge remove from out-edges list

    this.sizeOfGraph = this.sizeOfGraph - numOfEdgesRemoved; // reducing this by the number of edges removed

    // loop through its out edges list and remove those edges from the corresponding in edges list of
    // those other vertices

    this.orderOfGraph = this.orderOfGraph - 1; // removed a vertex
    // neeed a way to keep track of how many vertices removed and decrement the size by that amount

    vertexNodeToRemove.setInEdgesList(null);
    vertexNodeToRemove.setOutEdgesList(null);

    vertexNodeToRemove = null;
  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If either
   * vertex does not exist, add vertex, and add edge, no exception is thrown. If the edge exists in
   * the graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge is not in the graph
   */
  public void addEdge(String vertex1, String vertex2) {
    if (vertex1 == null || vertex2 == null) {
      return; // cannot add the edge
    }
    if (isEdgeInGraph(vertex1, vertex2) == true) {
      return; // edge already exists so should not add it again
    }

    // otherwise, the edge is new!
    this.sizeOfGraph = sizeOfGraph + 1; // added an edge

    if (isVertexInGraph(vertex1) == false
        && isVertexInGraph(vertex2) == false) {
      // create new node for vertex1

      Graphnode<String> vertexNode1 = new Graphnode(vertex1);
      vertexList.add(vertexNode1);

      Graphnode<String> vertexNode2 = new Graphnode(vertex2);
      vertexList.add(vertexNode2);

      this.orderOfGraph = orderOfGraph + 2;

      vertexNode1.addOutEdge(vertexNode2);
      vertexNode2.addInEdge(vertexNode1);
    } else if (isVertexInGraph(vertex1) == false
        && isVertexInGraph(vertex2) == true) {
      // create new node for vertex1
      Graphnode<String> vertexNode1 = new Graphnode(vertex1);
      vertexList.add(vertexNode1);

      this.orderOfGraph = orderOfGraph + 1;

      Graphnode<String> vertexNode2 = findVertexNode(vertex2);

      vertexNode1.addOutEdge(vertexNode2);
      vertexNode2.addInEdge(vertexNode1);
    } else if (isVertexInGraph(vertex1) == true
        && isVertexInGraph(vertex2) == false) {
      // create new node for vertex1
      Graphnode<String> vertexNode2 = new Graphnode(vertex2);
      vertexList.add(vertexNode2);
      this.orderOfGraph = orderOfGraph + 1;
      Graphnode<String> vertexNode1 = findVertexNode(vertex1);

      vertexNode1.addOutEdge(vertexNode2);
      vertexNode2.addInEdge(vertexNode1);

    } else {
      // both are there
      Graphnode<String> vertexNode1 = findVertexNode(vertex1);
      Graphnode<String> vertexNode2 = findVertexNode(vertex2);
      vertexNode1.addOutEdge(vertexNode2);
      vertexNode2.addInEdge(vertexNode1);

    }


  }

  /**
   * Please see if edge vertex1 --> vertex2 is in the graph. If so, please return true. Otherwise,
   * return false.
   * 
   * @param vertex1
   * @param vertex2
   * @return
   */
  private boolean isEdgeInGraph(String parentVertex, String childVertex) {
    // if either vertex is NOT in the graph, then there would be no edge either
    if (isVertexInGraph(parentVertex) == false
        || isVertexInGraph(childVertex) == false) {
      return false;
    }
    // now that we are here, we are assured that both vertices are in the graph

    Graphnode<String> parentNode = findVertexNode(parentVertex);
    Graphnode<String> childNode = findVertexNode(childVertex);

    // if the edge is in the Graph, then it will be in the:
    // outgoing edges for vertex1 (the parentNode)
    // incoming edges for vertex2 (the childNode)

    List<Graphnode<String>> parentOutgoingEdgesList =
        parentNode.getOutEdgesList(); // outgoing edges for parentNode
    List<Graphnode<String>> childIncomingEdgesList = childNode.getInEdgesList(); // incoming edges for parentNode


    boolean childNodeInParentOutList = false; // initialized to false (is the child node found in the parent's outgoing node list?)
    boolean parentNodeInChildInList = false; // initialized to false (is the parent node found in the child's incoming node list?)


    // please see if the childNode is in the parentOutgoingEdgesList
    for (int i = 0; i < parentOutgoingEdgesList.size(); i++) {
      Graphnode<String> currentOutNode = parentOutgoingEdgesList.get(i);
      String currentOutVertex = currentOutNode.getVertex();
      if (currentOutVertex.equals(childVertex)) {
        childNodeInParentOutList = true; // we found the child node in the parent list

      }

    }

    // do the same for the other way around
    // please see if the parentNode is in the childIncomingEdgesList
    for (int i = 0; i < childIncomingEdgesList.size(); i++) {
      Graphnode<String> currentOutNode = childIncomingEdgesList.get(i);
      String currentOutVertex = currentOutNode.getVertex();
      if (currentOutVertex.equals(parentVertex)) {
        parentNodeInChildInList = true; // we found the child node in the parent list

      }

    }

    // the edge does exist in the graph! :)
    if (childNodeInParentOutList == true && parentNodeInChildInList == true) {
      return true;
    }

    return false;
  }



  /**
   * Please note that this method returns the list of vertices that have no predecessors
   * 
   * @return
   */
  private List<Graphnode<String>> verticesWithNoPredecessors() {
    List<Graphnode<String>> vertexNodesWithNoPredecessorsList =
        new ArrayList<Graphnode<String>>();

    for (int i = 0; i < vertexList.size(); i++) {
      Graphnode<String> currentVertexNode = vertexList.get(i);
      if (currentVertexNode.getInEdgesList().size() == 0) {
        vertexNodesWithNoPredecessorsList.add(currentVertexNode);
      }

    }
    return vertexNodesWithNoPredecessorsList;
  }



  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge from vertex1 to vertex2 is in the graph
   */
  public void removeEdge(String vertex1, String vertex2) {

    // if either vertex1 or vertex2 is null, we return nothing.
    if (vertex1 == null || vertex2 == null) {
      return;
    }

    // if vertex1 is NOT in the graph and/or vertex2 is NOT in the graph, we do nothing
    if (isVertexInGraph(vertex1) == false
        || isVertexInGraph(vertex2) == false) {
      return;
    }

    if (isEdgeInGraph(vertex1, vertex2) == false) {
      return; // edge is NOT in graph
    }

    // if we get here we have non-null vertices and the edge exists in the graph, so we can remove it
    // finding the corresponding nodes for these vertices
    Graphnode<String> parentVertexNode = findVertexNode(vertex1);
    Graphnode<String> childVertexNode = findVertexNode(vertex2);


    List<Graphnode<String>> parentOutEdgesList =
        parentVertexNode.getOutEdgesList();
    List<Graphnode<String>> childInEdgesList = childVertexNode.getInEdgesList();


    // to remove the edge:
    // please remove the child vertex (vertex2) from the parent vertex (vertex1)'s outgoing edges
    // please remove the parent vertex (vertex1) from the child vertex (vertex2)'s incoming edges

    parentOutEdgesList.remove(childVertexNode); // removing vertex2 from vertex1's outgoing edges
    childInEdgesList.remove(parentVertexNode); // removing vertex1 from vertex2's incoming edges


    // just to be safe and do defensive programming, please update:
    parentVertexNode.setOutEdgesList(parentOutEdgesList);
    childVertexNode.setInEdgesList(childInEdgesList);
    // get the parent's outedges --> remove vertex2 node
    // get vertex2's in edges --> remove vertex1 node


    this.sizeOfGraph = this.sizeOfGraph - 1; // removed an edge
  }

  /**
   * Returns a Set that contains all the vertices
   * 
   */
  public Set<String> getAllVertices() {
    Set<String> verticesSet = new HashSet<String>(); // cannot instantiate a Set object
    if (vertexList == null) {
      return null;
    }
    for (int i = 0; i < vertexList.size(); i++) {
      Graphnode<String> currentNode = vertexList.get(i); // node for the currentVertex in the list
      String currentVertex = currentNode.getVertex();
      verticesSet.add(currentVertex);

    }

    return verticesSet;
  }

  /**
   * Get all the neighbor (adjacent) vertices of a vertex
   *
   */
  public List<String> getAdjacentVerticesOf(String vertex) {
    // please get the node object for this vertex
    if (isVertexInGraph(vertex) == false) {
      return null; // this vertex is NOT found in the graph, so it does NOT exist
    }

    // if we get here, then the vertex IS in the graph
    // please find the corresponding vertexNode for this vertex
    Graphnode<String> targetNode = findVertexNode(vertex);


    // finding the node that corresponds to this vertex in the graph
    List<Graphnode<String>> neighborsOfTargetNodeList =
        targetNode.getOutEdgesList(); // the out edges list

    List<String> adjacencyListOfVertex = new ArrayList<String>(); // holds the String of vertices for the Adjacency List
    for (int i = 0; i < neighborsOfTargetNodeList.size(); i++) {
      // getting the tragetNodeNeighbor
      Graphnode<String> targetNodeNeighbor = neighborsOfTargetNodeList.get(i);
      String targetNeighborVertex = targetNodeNeighbor.getVertex();
      adjacencyListOfVertex.add(targetNeighborVertex);

    }


    return adjacencyListOfVertex;
  }


  /**
   * Returns the number of edges in this graph.
   */
  public int size() {
    return sizeOfGraph;
  }



  /**
   * Returns the number of vertices in this graph.
   */
  public int order() {
    return orderOfGraph;
  }
}
