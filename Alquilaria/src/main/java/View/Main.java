
package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.util.Scanner;
import Controller.ConnectionDB;


import javax.swing.*;
import javax.swing.border.Border;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        //un border
        Color a = new Color(212, 172, 93);// a colour
        Border border = BorderFactory.createLineBorder(a,10);

        // texto centrado
        JLabel label1 = new JLabel();
        label1.setText("Choose a Languaje");
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.TOP);
        Font aFont = new Font("Carlito", Font.BOLD, 60);
        label1.setFont(aFont);
        label1.setBorder(border);

        // botones

        JButton benglish = new JButton();
        benglish.setBounds(100,50,50,200);

        JButton bspanish = new JButton();
        bspanish.setBounds(200,100,350,200);



        JFrame frame = new JFrame();
        frame.setTitle("🇪🇸​/🇬🇧​");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit
        frame.setLocationRelativeTo(null);// center in the screen
        Color c = new Color(237,206,145);// a colour that I liked
        frame.getContentPane().setBackground(c); // colour set    
        //frame.add(label1);// i must know how labels ,and pannels work
        frame.add(benglish);
        //frame.add(bspanish);




        frame.setVisible(true);








        
        
        
    }
}
