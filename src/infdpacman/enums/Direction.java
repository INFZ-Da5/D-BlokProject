package infdpacman.enums;

/**
 *
 * @author CVD
 */

public enum Direction {
    NORTH, SOUTH, WEST, EAST;
    
    public static Direction getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
