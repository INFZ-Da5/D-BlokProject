
package infdpacman.character;
import infdpacman.Cell;
/**
 *
 * @author Lenovo
 */
public class Cellconnection {
    
  private final String id; 
  private final Cell source;
  private final Cell destination;
  private final int weight; 
  
  public Cellconnection(String id, Cell source, Cell destination, int weight) {
    this.id = id;
    this.source = source;
    this.destination = destination;
    this.weight = weight;
  }
  
  public String getId() {
    return id;
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

