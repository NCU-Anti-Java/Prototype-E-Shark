package main;

import javax.swing.JFrame;
/**
 * Created by Zheng-Yuan on 10/24/2015.
 */
public class main {

    public static void main(String[] args){
        JFrame window = new JFrame("Fuck Test");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

}
