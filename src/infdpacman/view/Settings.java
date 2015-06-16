package infdpacman.view;

import infdpacman.Game;
import infdpacman.Menu;
import infdpacman.enums.Actions;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author CVD
 */
public class Settings implements ActionListener {
    
 public JFrame frame;
 public JTextField tfLC;
 public Menu menu;
 public ArrayList<Board> levels;
 
 public Settings(Menu menu){
 
 this.menu = menu;
 
 
 }

    
    
 public void init(){
 
    frame = new JFrame();
   frame.setLayout(new GridLayout(3,2));
   frame.setSize(200, 200);
   
   JButton levelchooser = new JButton("choose level");
   tfLC = new JTextField();
            levelchooser.setActionCommand(Actions.LEVEL.name());
            levelchooser.addActionListener(this);
            
   JButton save = new JButton("save changes");  
            save.setActionCommand(Actions.STOP.name());
            save.addActionListener(this);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0, 2));
    panel.add(tfLC);
    panel.add(levelchooser);
    frame.add(panel);
    frame.add(save);
    frame.setVisible(true);
            
 }   
 
     

    @Override
    public void actionPerformed(ActionEvent e) {
  if (e.getActionCommand().equals(Actions.LEVEL.name())) {
      int level = (Integer.parseInt(tfLC.getText()) - 2);
      if(level < 0 || level > 3){
       menu.game.setBoard(null);    
      }else{      
      menu.game.setBoard(menu.game.getLevels().get(level));}
        }else if (e.getActionCommand().equals(Actions.STOP.name())) {
       frame.dispose();
       menu.init();
        }
    }
    
}
