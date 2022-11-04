package snakeGame;

import javax.swing.*;

public class Gameframe extends JFrame {
    JButton b=new JButton("start");
    JButton b1=new JButton("bye");
    Gameframe(){
        this.add(new Gamepanel());
        setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
