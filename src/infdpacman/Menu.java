
package infdpacman;

import infdpacman.enums.Actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Menu implements ActionListener {
    
    
    
public void init(){

  JFrame menu = new JFrame();
                JButton start = new JButton("start");
            start.setActionCommand(Actions.START.name());
            start.addActionListener(this);
        JButton settings = new JButton("settings");
            settings.setActionCommand(Actions.SETTINGS.name());
            settings.addActionListener(this);
        JButton quit= new JButton("quit");
            quit.setActionCommand(Actions.QUIT.name());
            quit.addActionListener(this);
            
           JPanel panel = new JPanel(); 
            panel.add(start);
            panel.add(settings);
            panel.add(quit);
            menu.add(panel);
        menu.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
if (e.getActionCommand().equals(Actions.START.name())) {
        Game game = new Game();
        game.initFrame();
        }else if(e.getActionCommand().equals(Actions.SETTINGS.name())){            

        } else if(e.getActionCommand().equals(Actions.QUIT.name())){

        }
    }
    
    
    
}
