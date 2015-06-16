
package infdpacman;

import infdpacman.enums.Actions;
import java.awt.GridLayout;
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
public JFrame menu;
    
    
public void init(){

   menu = new JFrame();
   menu.setLayout(new GridLayout(3,0));
   menu.setSize(200, 200);
                JButton start = new JButton("start");
            start.setActionCommand(Actions.START.name());
            start.addActionListener(this);
        JButton settings = new JButton("settings");
            settings.setActionCommand(Actions.SETTINGS.name());
            settings.addActionListener(this);
        JButton quit= new JButton("quit");
            quit.setActionCommand(Actions.QUIT.name());
            quit.addActionListener(this);
            

            menu.add(start);
            menu.add(settings);
            menu.add(quit);
           
        menu.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
if (e.getActionCommand().equals(Actions.START.name())) {
        Game game = new Game();
        game.initFrame();
        menu.dispose();
        }else if(e.getActionCommand().equals(Actions.SETTINGS.name())){            

        } else if(e.getActionCommand().equals(Actions.QUIT.name())){
           menu.dispose();
        }
    }
    
    
    
}
