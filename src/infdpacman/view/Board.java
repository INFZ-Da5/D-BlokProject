package infdpacman.view;

import infdpacman.cell.Cell;
import infdpacman.cell.EmptyCell;
import infdpacman.cell.Wall;
import infdpacman.enums.Direction;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
import infdpacman.gameelement.character.SmartGhost;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Banana;
import infdpacman.gameelement.item.Cherry;
import infdpacman.gameelement.item.Item;
import infdpacman.gameelement.item.Pill;
import infdpacman.gameelement.item.SuperPill;
import infdpacman.utilities.FindClassTypeFromList;
import infdpacman.utilities.dijkstra.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public abstract class Board extends JPanel  {
    private Pacman pacman;
    private DrunkGhost dg1;
    private SmartGhost g1;
    private Cell[][] cellgrid;
    private ArrayList<GameCharacter> ghosts;
    private ArrayList<Edge> edges;
    private List<Cell> nodes;

    private int amountOfPills;
    private int amountOfPillsInGame;
    private boolean cherrySpawned;
    private boolean bananaSpawned;
    private boolean stopTimer;

    public Board(){
        ghosts = new ArrayList();
        stopTimer = false;
        this.requestFocusInWindow();
        this.repaint();
    }

    public Cell[][] getCellgrid() {
        return cellgrid;
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
                        case 1: inhoud.add(pacman = new Pacman(cell)); this.addKeyListener(pacman);break;
                        case 2: inhoud.add(g1 = new SmartGhost(cell, this)); ghosts.add(g1);break;
                        case 3: inhoud.add(dg1 = new DrunkGhost(cell, this)); ghosts.add(dg1);break;
                        case 4: inhoud.add(new Pill());break;
                        case 5: inhoud.add(new SuperPill()); break;
                        case 6: inhoud.add(new Banana()); break;
                    }
                }
                this.add(cellgrid[row][col]);
            }
        }
        setNeigborsOfCell();
        setNodes();
        setEdgesOfCell();
        countPills();
    }  

    public void setNeigborsOfCell() {
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
    
    public void setEdgesOfCell(){
        edges = new ArrayList<>();
        for(Cell c : nodes){
            if(c instanceof EmptyCell){
                Map<Direction, Cell> cellNeighbors = c.getNeighbors();
                Iterator it = cellNeighbors.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    if((Cell)pair.getValue() instanceof EmptyCell){
                        Edge e = new Edge(c, (Cell)pair.getValue());
                        edges.add(e);
                    }
                }
            }
        }
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
    
    public List<Cell> getNodes() {
        return nodes;
    }
    
    public <T> List<T> twoDArrayToList(T[][] twoDArray) {
        List<T> list = new ArrayList<T>();
        for (T[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }
    
    public Pacman getPacman(){ return pacman;}
    
    public void countPills(){
        for (Cell[] cellgrid1 : cellgrid) {
            for (int col = 0; col < cellgrid[0].length; col++) {
                if (cellgrid1[col] instanceof EmptyCell) {
                    if (FindClassTypeFromList.containsInstance(((EmptyCell) cellgrid1[col]).getInhoud(), Item.class)) {
                        amountOfPillsInGame++;
                    }
                }
            }
        }
        amountOfPills = amountOfPillsInGame;
    }

    public boolean isCherrySpawned() {
        return cherrySpawned;
    }

    public void setCherrySpawned(boolean cherrySpawned) {
        this.cherrySpawned = cherrySpawned;
    }

    public boolean isBananaSpawned() {
        return bananaSpawned;
    }

    public void setBananaSpawned(boolean bananaSpawned) {
        this.bananaSpawned = bananaSpawned;
    }
    
    public int getAmountOfPills() {
        return amountOfPills;
    }

    public void setAmountOfPills(int amountofPills) {
        this.amountOfPills = amountofPills;
    }

    public ArrayList<GameCharacter> getGhosts() {
        return ghosts;
    }

    public int getAmountofPillsInGame() {
        return amountOfPillsInGame;
    }

    public boolean isStopTimer() {
        return stopTimer;
    }

    public void setStopTimer(boolean stopTimer) {
        this.stopTimer = stopTimer;
    }
          
    public void checkForExtraItemSpawn() {
        if(amountOfPillsInGame%2 ==0){
            if(amountOfPills == (amountOfPillsInGame/2) && !cherrySpawned){
                cherrySpawned = spawnExtraItem(new Cherry());
            }
            else if(amountOfPills == (amountOfPillsInGame/4) && !bananaSpawned){
                bananaSpawned = spawnExtraItem(new Banana());
            }
        }
        else{
            amountOfPillsInGame-=1;
        }
    }
    
    public boolean spawnExtraItem(Item item){
        Random rd = new Random();

        double row = rd.nextInt(cellgrid.length-1);
        double col = rd.nextInt(cellgrid[0].length-1);
        
        if(cellgrid[(int)row][(int)col] instanceof EmptyCell){
            EmptyCell c = (EmptyCell)cellgrid[(int)row][(int)col];
            LinkedList<GameElement> l = (LinkedList<GameElement>) c.getInhoud();
            if(l.isEmpty()){
                l.add(item);
                c.setInhoud(l);
                amountOfPills++;
                extraItemTimer(new Timer(),c, item);
            }
            else{
                spawnExtraItem(item);
            }
        }else{   
            spawnExtraItem(item);
        }
        return true;
    }
    
    private void extraItemTimer(Timer t, EmptyCell c, Item item){
        TimerTask task = new TimerTask(){
            public void run(){ 
                if(stopTimer){
                    t.cancel();
                }
                else{
                    if(c.getInhoud().contains(item)){
                        c.getInhoud().remove(item);
                        amountOfPills--;
                    }
                    t.cancel();
                }
            }
        };
        t.scheduleAtFixedRate(task, 10000, 1);
    }

    private void setNodes() {
        nodes = twoDArrayToList(cellgrid);
    }
    
    public void ghostRespawnTimer(Timer t, GameElement g) {
        TimerTask task = new TimerTask(){
            public void run(){
                t.cancel();
                ((Ghost)g).attack();
                ((Ghost)g).setCell(((Ghost)g).getSpawnCell());
                ghosts.add((GameCharacter)g);
                ((Ghost)g).setEaten(false);
            }
        };
        t.scheduleAtFixedRate(task, 5000, 1);
    }
    
}






