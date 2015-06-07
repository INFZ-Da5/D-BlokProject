
package infdpacman.character;

import infdpacman.Cell;
import infdpacman.Board;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Lenovo
 */
public class Pathfinding {
      private  List<Cell> nodes;
  private  List<Cellconnection> edges;
  private Set<Cell> settledNodes;
  private Set<Cell> unSettledNodes;
  private Map<Cell, Cell> predecessors;
  private Map<Cell, Integer> distance;

  public Pathfinding(Board level) {
    // create a copy of the array so that we can operate on this array
    //this.nodes = new ArrayList<Cell>(level.vakjes);
   this.edges = new ArrayList(level.getNeighbors());
  }

  public void execute(Cell source) {
    settledNodes = new HashSet<Cell>();
    unSettledNodes = new HashSet<Cell>();
    distance = new HashMap<Cell, Integer>();
    predecessors = new HashMap<Cell, Cell>();
    distance.put(source, 0);
    unSettledNodes.add(source);
    while (unSettledNodes.size() > 0) {
      Cell node = getMinimum(unSettledNodes);
      settledNodes.add(node);
      unSettledNodes.remove(node);
      findMinimalDistances(node);
    }
  }

  private void findMinimalDistances(Cell node) {
    List<Cell> adjacentNodes = getNeighbors(node);
    for (Cell target : adjacentNodes) {
      if (getShortestDistance(target) > getShortestDistance(node)
          + getDistance(node, target)) {
        distance.put(target, getShortestDistance(node)
            + getDistance(node, target));
        predecessors.put(target, node);
        unSettledNodes.add(target);
      }
    }

  }

  private int getDistance(Cell node, Cell target) {
    for (Cellconnection edge : edges) {
      if (edge.getSource().equals(node)
          && edge.getDestination().equals(target)) {
        return edge.getWeight();
      }
    }
    throw new RuntimeException("Should not happen");
  }

  private List<Cell> getNeighbors(Cell node) {
    List<Cell> neighbors = new ArrayList<Cell>();
    for (Cellconnection edge : edges) {
      if (edge.getSource().equals(node)
          && !isSettled(edge.getDestination())) {
        neighbors.add(edge.getDestination());
      }
    }
    return neighbors;
  }

  private Cell getMinimum(Set<Cell> Cell) {
    Cell minimum = null;
    for (Cell vertex : Cell) {
      if (minimum == null) {
        minimum = vertex;
      } else {
        if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
          minimum = vertex;
        }
      }
    }
    return minimum;
  }

  private boolean isSettled(Cell vertex) {
    return settledNodes.contains(vertex);
  }

  private int getShortestDistance(Cell destination) {
    Integer d = distance.get(destination);
    if (d == null) {
      return Integer.MAX_VALUE;
    } else {
      return d;
    }
  }

  /*
   * This method returns the path from the source to the selected target and
   * NULL if no path exists
   */
  public LinkedList<Cell> getPath(Cell target) {
    LinkedList<Cell> path = new LinkedList<Cell>();
    Cell step = target;
    // check if a path exists
    if (predecessors.get(step) == null) {
      return null;
    }
    path.add(step);
    while (predecessors.get(step) != null) {
      step = predecessors.get(step);
      path.add(step);
    }
    // Put it into the correct order
    Collections.reverse(path);
    return path;
  }

} 


