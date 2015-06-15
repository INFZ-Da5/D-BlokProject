/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infdpacman.utilities.dijkstra;

import infdpacman.cell.Cell;

/**
 *
 * @author CVD
 */
public class Edge  {
  private final Cell source;
  private final Cell destination;
  private final int weight; 
  
  public Edge(Cell source, Cell destination) {
    this.source = source;
    this.destination = destination;
    this.weight = 1;
  }
  
  public Cell getDestination() {
    return destination;
  }

  public Cell getSource() {
    return source;
  }
  public int getWeight() {
    return weight;
  }
  
  @Override
  public String toString() {
    return source + " " + destination;
  }  
} 