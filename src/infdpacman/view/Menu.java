
package infdpacman.view;

import infdpacman.Game;
import infdpacman.enums.Actions;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Menu implements ActionListener {
public JFrame menu;
public Game game;


public Menu(){
    game = new Game(this);
}
    
    
public void init(){
   menu = new JFrame();
   menu.setLayout(new GridLayout(4,0));
   menu.setSize(200, 200);
                JButton start = new JButton("start");
            start.setActionCommand(Actions.START.name());
            start.addActionListener(this);
        JButton settings = new JButton("settings");
            settings.setActionCommand(Actions.SETTINGS.name());
            settings.addActionListener(this);
        JButton Highscore= new JButton("Highscores");
            Highscore.setActionCommand(Actions.SCORE.name());
            Highscore.addActionListener(this);
        JButton quit= new JButton("quit");
            quit.setActionCommand(Actions.QUIT.name());
            quit.addActionListener(this);
            

        menu.add(start);
        menu.add(settings);
        menu.add(Highscore);
        menu.add(quit);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
if (e.getActionCommand().equals(Actions.START.name())) {
        
        game.initFrame();
        menu.dispose();
        }else if(e.getActionCommand().equals(Actions.SETTINGS.name())){
            
            Settings settings = new Settings(this);
            settings.init();
            menu.dispose();
            
        }else if(e.getActionCommand().equals(Actions.SCORE.name())){ 
             String message = "Highscores:\n";
                for ( Map.Entry<String, Integer> entry : game.gethScores().entrySet()) {
                    String name = entry.getKey();
                    Integer score = entry.getValue();
                    message = message + name + ": " + score + "\n";
                }
           JOptionPane.showMessageDialog(null, message, "scorelist", JOptionPane.ERROR_MESSAGE);  
            

        } else if(e.getActionCommand().equals(Actions.QUIT.name())){
           menu.dispose();
        }
    }
    
    
    
}
