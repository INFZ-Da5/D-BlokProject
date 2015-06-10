package infdpacman.view;

import infdpacman.Player;
import infdpacman.cell.Cell;
import infdpacman.cell.EmptyCell;
import infdpacman.cell.Wall;
import infdpacman.enums.Direction;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Banana;
import infdpacman.gameelement.item.Item;
import infdpacman.gameelement.item.Pill;
import infdpacman.gameelement.item.SuperPill;
import infdpacman.utilities.FindClassType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public abstract class Board extends JPanel  {
    Pacman pacman;
    DrunkGhost dg1;
    Ghost g1;
    Cell[][] cellgrid;
    public int total;
    private static Cell pacmanCell;
    public Player player;
    private int amountofPills;
    ArrayList<GameCharacter> ghosts;

    public static Cell getPacmanCell() {
        return pacmanCell;    
    }
    
    public Board(){
        initCharacters();
        this.requestFocusInWindow();
        this.addKeyListener(pacman);
        this.repaint();
        total = 0;
    }
    
    
    public void setPlayer(Player player){
    
    this.player = player;
    }
    
    
    public void fillCells(int [][] grid){
        cellgrid = new Cell[grid.length][grid[0].length];
        
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == 0){
                cellgrid[row][col] = new Wall();}
                
                if(grid[row][col] != 0){
                    LinkedList inhoud = new LinkedList();
                    EmptyCell cell = new EmptyCell(this);
                    cellgrid[row][col] = cell;
                    cell.setInhoud(inhoud);

                    switch(grid[row][col]){
                        case 1: inhoud.add(pacman); pacmanCell = cell; break;
                        case 2: inhoud.add(ghosts.get(0)); break;
                        case 3: inhoud.add(ghosts.get(1)); break;
                        case 4: inhoud.add(new Pill());break;
                        case 5: inhoud.add(new SuperPill()); break;
                        case 6: inhoud.add(new Banana()); break;
                    }
                }
                this.add(cellgrid[row][col]);
            }
        }
        setNeigbors();
    }  

    public void setNeigbors() {
        for(int row = 0; row < cellgrid.length; row++){
            for(int col = 0; col < cellgrid[0].length; col++){
                if(cellgrid[row][col] instanceof EmptyCell){
                    Map<Direction, Cell> neighbors = cellgrid[row][col].getNeighbors();
                    neighbors.put(Direction.WEST, cellgrid[row][col-1]);
                    neighbors.put(Direction.EAST, cellgrid[row][col+1]);
                    neighbors.put(Direction.SOUTH, cellgrid[row+1][col]);
                    neighbors.put(Direction.NORTH, cellgrid[row-1][col]);
                    cellgrid[row][col].setNeighbors(neighbors);  
                }
            }
        }
    }
    
    public Pacman getPacman(){ return pacman;}
    
    public void countPills(){
        for (Cell[] cellgrid1 : cellgrid) {
            for (int col = 0; col < cellgrid[0].length; col++) {
                if (cellgrid1[col] instanceof EmptyCell) {
                    if (FindClassType.containsInstance(((EmptyCell) cellgrid1[col]).getInhoud(), Item.class)) {
                        amountofPills++;
                    }
                }
            }
        }
    }

    public int getAmountofPills() {
        return amountofPills;
    }

    public void setAmountofPills(int amountofPills) {
        this.amountofPills = amountofPills;
    }

    public ArrayList getGhosts() {
        return ghosts;
    }

    private void initCharacters() {
        ghosts = new ArrayList();
        pacman = new Pacman();

        ghosts.add(g1 = new Ghost());
        //ghosts.add(g2 = new Ghost());
        ghosts.add(dg1 = new DrunkGhost());
        //ghosts.add(dg2 = new DrunkGhost());
    }
    
}






