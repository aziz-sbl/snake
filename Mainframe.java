package snakeGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.*;

public class Mainframe implements ActionListener {

    JFrame f=new JFrame();
    JPanel p=new JPanel();
    JButton b=new JButton("start game");
    JLabel jl = new JLabel();
    JLabel l=new JLabel("snake");
    public Mainframe(){
        p.setBackground(Color.lightGray);

        jl.setIcon(new ImageIcon("C:\\Users\\Med Aziz\\Desktop\\a1.png"));
        jl.validate();
        f.setContentPane(p);
        f.setSize(600,600);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        p.setBorder(new EmptyBorder(10,10,10,10));
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        l.setAlignmentY(0);
        l.setFont(new Font("arcade", CENTER_BASELINE,50));
        l.setSize(200,200);
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        p.add(l,gbc);
        p.add(new JLabel(""),gbc);
        p.add(jl,gbc);
        p.add(new JLabel(""),gbc);
        p.add(b,gbc);


        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Gameframe();
        f.setVisible(false);
    }
}
