package infdpacman.view;

import infdpacman.Menu;
import infdpacman.enums.Actions;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author CVD
 */
public class Settings implements ActionListener {
    
 private JFrame frame;
 private JTextField tfLC;
 private JComboBox cbLC;
 private JComboBox livesbox;
 private Menu menu;
 
 public Settings(Menu menu){
 
 this.menu = menu;
 
 
 }

    
    
 public void init(){
 
    frame = new JFrame();
   frame.setLayout(new GridLayout(3,2));
   frame.setSize(200, 200);
   JLabel levelchooser = new JLabel("choose level");
   cbLC = new JComboBox();
   cbLC.addItem("Maak een keuze");
   for(int i = 0; i < menu.game.getLevels().size(); i++){
       if(i - 1 < 0){
        cbLC.addItem(new ComboItem("level: " + (i+1), null));  
       }else{
   cbLC.addItem(new ComboItem("level: " + (i+1), menu.game.getLevels().get(i - 1)));}
   }

   JLabel liveslabel = new JLabel("selecteer aantal levens");
   livesbox = new JComboBox();
     livesbox.addItem("Maak een keuze");
   for(int i = 0; i < 5; i++){ 
   livesbox.addItem(i + 1);
   }
   
   JButton save = new JButton("save changes");  
            save.addActionListener(this);
    JPanel levelselect = new JPanel();
    levelselect.setLayout(new GridLayout(0, 2));
    levelselect.add(levelchooser);
    levelselect.add(cbLC);
       JPanel livesselect = new JPanel();
       livesselect.add(liveslabel);
       livesselect.add(livesbox);
    frame.add(levelselect);   
    frame.add(livesselect);
    frame.add(save);
    frame.setVisible(true);          
 }   
 
     

    @Override
    public void actionPerformed(ActionEvent e) {
  
 // -2 omdat 1: bij size altijd -1 en 2: Setlevel in Game laadt level na board 
     // if(level < 0 || level > 3){
       //menu.game.setBoard(null);    
      //}else{    
      if(cbLC.getSelectedItem() != cbLC.getItemAt(0)){
      menu.game.setBoard(((ComboItem)cbLC.getSelectedItem()).getValue());//}
      }
      
          if(livesbox.getSelectedItem() != livesbox.getItemAt(0)){
          menu.game.setPrefLives((Integer)livesbox.getSelectedItem());
          }
      
      
       frame.dispose();
       menu.init();
        
    }
    
}
