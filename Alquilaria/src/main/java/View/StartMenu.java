package View;

import javax.swing.JFrame;
import java.awt.Color;


public class StartMenu {

    StartMenu(){

        JFrame frame = new JFrame();
        frame.setTitle("🇪🇸​/🇬🇧​");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit
        frame.setLocationRelativeTo(null);// center in the screen
        Color c = new Color(237,206,145);// a colour that I liked
        frame.getContentPane().setBackground(c); // colour set    

        



        frame.setVisible(true);

    }




}
