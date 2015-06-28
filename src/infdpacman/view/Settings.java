package infdpacman.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author CVD
 */
public class Settings implements ActionListener {
    
 private JFrame frame;
 private JComboBox cbLC;
 private JComboBox livesbox;
 private final Menu menu;
 private JRadioButton arrows;
 private JRadioButton wasd;
 
 public Settings(Menu menu){
 
 this.menu = menu; 
 }

 public void init(){
 
    frame = new JFrame();
   frame.setLayout(new GridLayout(4,2));
   frame.setSize(400, 400);
   JLabel levelchooser = new JLabel("choose level");
   cbLC = new JComboBox();
   cbLC.addItem("Maak een keuze");
   //ComboItem class aangemaakt om de level namen in de boxview neer te zetten ipv de levels zelf
   for(int i = 0; i < menu.game.getLevels().size(); i++){
       if(i - 1 < 0){
        cbLC.addItem(new ComboItem("level: " + (i+1), null));  
       }else{
   cbLC.addItem(new ComboItem("level: " + (i+1), menu.game.getLevels().get(i - 1)));} //-1 vanwege setLevel in game
   }

   JLabel liveslabel = new JLabel("selecteer aantal levens");
   livesbox = new JComboBox();
     livesbox.addItem("Maak een keuze");
   for(int i = 0; i < 5; i++){ 
   livesbox.addItem(i + 1);
   }
   
     ButtonGroup controls = new ButtonGroup();
     arrows = new JRadioButton("pijltjes toetsen");
     wasd = new JRadioButton("wasd");
    JLabel Cuitleg = new JLabel("selecteer controls");
    controls.add(arrows);
    controls.add(wasd);
    JPanel Pcontrols = new JPanel();
    Pcontrols.add(Cuitleg);
    Pcontrols.add(arrows);
    Pcontrols.add(wasd);
   
   JButton save = new JButton("save changes");  
            save.addActionListener(this);
    JPanel levelselect = new JPanel();
    levelselect.add(levelchooser);
    levelselect.add(cbLC);
       JPanel livesselect = new JPanel();
       livesselect.add(liveslabel);
       livesselect.add(livesbox);
    frame.add(levelselect);   
    frame.add(livesselect);
    frame.add(Pcontrols);
    JPanel sPanel = new JPanel();
    sPanel.add(save);
    frame.add(sPanel);
    frame.setVisible(true);   
 }   

    @Override
    public void actionPerformed(ActionEvent e) {  
      if(cbLC.getSelectedItem() != cbLC.getItemAt(0)){
      menu.game.setBoard(((ComboItem)cbLC.getSelectedItem()).getValue());//}
      }
      
      if(livesbox.getSelectedItem() != livesbox.getItemAt(0)){
      menu.game.setPrefLives((Integer)livesbox.getSelectedItem());
      }
      else if(livesbox.getSelectedItem() == null){
        menu.game.setPrefLives(3);
      }
      
      if(arrows.isSelected()){     
      menu.game.setKeys(true);
      }
      
      if(wasd.isSelected()){
        menu.game.setKeys(false);
      }
      
      else if(!(wasd.isSelected() && arrows.isSelected())){
            menu.game.setKeys(true);
      }
       frame.dispose();
       menu.init();  
    }
    
}
