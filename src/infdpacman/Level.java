package infdpacman;

import infdpacman.item.Pill;
import infdpacman.poppetje.DrunkGhost;
import infdpacman.poppetje.Pacman;
import infdpacman.poppetje.Character;
import infdpacman.poppetje.Ghost;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Level extends JPanel  {
    int height = 8;
    int length = 8;
    EmptyCell[][] vakjes = new EmptyCell[length][height];
    EmptyCell currentVakje;
    Pacman p = new Pacman(this);

    public Level(){
        this.requestFocusInWindow();
        this.addKeyListener(p);
        this.repaint();
        this.setLayout(new GridLayout(length,height));
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < length; col++) {
                vakjes[row][col] = new EmptyCell();
                this.add(vakjes[row][col]);
            }
        }
        initLevel();
    }
    
    public void getCurrentVakje(Character poppetje){
        for (int i = 0; i<vakjes[0].length; i++){
            for (int j = 0; j<vakjes.length; j++){
                if(vakjes[i][j].getInhoud().contains(poppetje)){
                    currentVakje = vakjes[i][j];
                }
            }
        }
    }

    public EmptyCell getCurrentVakje() {
        return currentVakje;
    }

    public void setCurrentVakje(Direction direction) {
        switch (direction){
            case NORTH:
                currentVakje = vakjes[getGridPlace().getRow()-1][getGridPlace().getColumn()];
                break;
            case SOUTH:
                currentVakje = vakjes[getGridPlace().getRow()+1][getGridPlace().getColumn()];
                break;
            case WEST:
                currentVakje = vakjes[getGridPlace().getRow()][getGridPlace().getColumn()-1];
                break;
            case EAST:
                currentVakje = vakjes[getGridPlace().getRow()][getGridPlace().getColumn()+1];
                break;
        }
    }
    
    public GridPlace getGridPlace(){
        int row = 0;
        int column = 0;
        for (int j = 0; j<vakjes[0].length; j++){
            for (int i = 0; i<vakjes.length; i++){
                if(vakjes[i][j].equals(currentVakje)){
                    row = i;
                    column = j;
                }
            }
        }
        return new GridPlace(row, column);
    }
    
    public ArrayList getNeighbors(){
        int row = getGridPlace().getRow();
        int column = getGridPlace().getColumn();
        ArrayList neighbors = new ArrayList();
        
        neighbors.add(vakjes[row][column-1]);
        neighbors.add(vakjes[row][column+1]);
        neighbors.add(vakjes[row+1][column]);
        neighbors.add(vakjes[row-1][column]);
       
        return neighbors;
    }
    
    private void initLevel() {
        Ghost sp = new Ghost();
        DrunkGhost dg = new DrunkGhost();
        Wall m = new Wall();
        Pill b = new Pill();
        currentVakje = vakjes[2][4];
      
        currentVakje.getInhoud().add(p);
        vakjes[4][3].getInhoud().add(sp);
        vakjes[4][4].getInhoud().add(sp);
        vakjes[5][2].getInhoud().add(dg);
        vakjes[6][6].getInhoud().add(dg);
        vakjes[5][1].getInhoud().add(b);
        vakjes[2][5].getInhoud().add(m);
        
        for (int row = 0; row < length; row++) {
            vakjes[row][0].getInhoud().add(m);
            vakjes[row][length-1].getInhoud().add(m);
            for (int col = 0; col < height; col++) {
                vakjes[0][col].getInhoud().add(m);
                vakjes[height-1][col].getInhoud().add(m);
            }
        }
        fillVakjes();
    }
    
    public void fillVakjes(){
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < height; col++) {
                vakjes[row][col].vulVakje();
            }
        }
        this.repaint();
    }
}
