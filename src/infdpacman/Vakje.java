package infdpacman;

import infdpacman.Muur;
import infdpacman.item.Item;
import infdpacman.poppetje.Poppetje;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Vakje extends JPanel{
    private LinkedList inhoud = new LinkedList();      
    ArrayList<Muur> muren = new ArrayList();
    ArrayList<Poppetje> poppetjes = new ArrayList();
    ArrayList<Item> items = new ArrayList();
    
    public Vakje(){
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    private void draw(Graphics g){
        for(Muur muur: muren){
            muur.draw(g,this.getWidth(),this.getHeight());
        }
        for(Poppetje poppetje: poppetjes){
            poppetje.draw(g,this.getWidth(),this.getHeight());
        }
        for(Item item: items){
            item.draw(g,this.getWidth(),this.getHeight());
        }
    }
    
    public LinkedList getInhoud() {
        return inhoud;
    }

    public void setInhoud(LinkedList inhoud) {
        this.inhoud = inhoud;
    } 
    
    public void vulVakje(){
        for(Object obj : inhoud){
            if(obj instanceof Muur){
                muren.add((Muur)obj);
            }
            else if(obj instanceof Poppetje){
                poppetjes.add((Poppetje)obj);
            }
            else{
                items.add((Item)obj);
            }
        }
    }
    
    
    
}
