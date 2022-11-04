package snakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public final class Gamepanel extends JPanel implements ActionListener {
   static final int screen_width=600;
   static final int screen_height=600;
   static final int unit_size=25;
   static final int game_units =(screen_width*screen_height)/unit_size ;
   static final int delay=80;
   final int x[]=new int[game_units];
   final int y[]=new int[game_units];
   int bodyParts =6;
   int applesEaten;
   int appleX;
   int appleY;
   char direction = 'R';
   boolean running=false;
   Timer timer;
   Random random;
    Gamepanel(){
        random =new Random();
        this.setPreferredSize(new Dimension(screen_width,screen_width));
        this.setBackground(Color.lightGray);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
public void startGame(){
        newApple();
        running=true;
        timer=new Timer(delay,this);
        timer.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
       /* for(int i=0;i<screen_height/unit_size;i++)
        {
            g.drawLine(i*unit_size,0,i*unit_size,screen_height);
            g.drawLine(0,i*unit_size, screen_width,i*unit_size);
        }*/
        g.setColor(Color.RED);
        g.fillOval(appleX,appleY,unit_size,unit_size);
        for(int i = 0; i< bodyParts; i++){
            if(i==0){
                g.setColor(Color.green);
                g.fillRect(x[i],y[i],unit_size,unit_size);
            }
            else
            {
                g.setColor(new Color(45,180,0));
               // g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                g.fillRect(x[i],y[i],unit_size,unit_size);
            }
        }
            g.setColor(Color.red);
            g.setFont(new Font("arcade",Font.BOLD,30));
            FontMetrics metrics=getFontMetrics(g.getFont());
            g.drawString("Score : "+applesEaten,(screen_width-metrics.stringWidth(("Score : "+applesEaten)))/2,g.getFont().getSize());

        }
        else
            gameOver(g);
    }
    public void newApple(){
        appleX= random.nextInt((int)(screen_width/unit_size))*unit_size;
        appleY= random.nextInt((int)(screen_height/unit_size))*unit_size;
    }
    public void move(){
        for(int i = bodyParts; i>0; i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (direction){
            case 'U':y[0]=y[0]-unit_size; break;
            case 'D':y[0]=y[0]+unit_size; break;
            case 'L':
                x[0]=x[0]-unit_size; break ;
            case 'R':
                x[0]=x[0]+unit_size; break;
        }
    }
    public void check(){
        if((x[0]==appleX) && (y[0]==appleY))
        {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisions(){
        //todhrb f rohk
        for(int i = bodyParts; i>0; i--){
            if ((x[0] == x[i]) && (y[0]==y[i]))  running=false;
        }
        //ken dkhalt f hyt
        if(x[0]<0) running=false;
        if(x[0]>screen_width) running=false;
        if(y[0]>screen_height) running=false;
        if(y[0]<0) running=false;
        if(!running) timer.stop();
    }
    public void gameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("arcade",Font.BOLD,60));
        FontMetrics metrics=getFontMetrics(g.getFont());
        g.drawString("game over",(screen_width-metrics.stringWidth(("game over")))/2,screen_height/2);
  //score
        g.setColor(Color.red);
        g.setFont(new Font("arcade",Font.BOLD,30));
        FontMetrics metrics2=getFontMetrics(g.getFont());
        g.drawString("Score : "+applesEaten,(screen_width-metrics2.stringWidth(("Score : "+applesEaten)))/2,g.getFont().getSize());
        //restart
        g.setColor(Color.red);
        g.setFont(new Font("arcade",Font.BOLD,45));
        FontMetrics metrics3=getFontMetrics(g.getFont());
        g.drawString("press space to restart",75,500);
    }
public void restart(){
        new Gameframe();
}
@Override
    public void actionPerformed(ActionEvent e) {
if(running){
    move();
    check();
    checkCollisions();
}
repaint();
    }


    public class MyKeyAdapter extends KeyAdapter{

        public void keyPressed (KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT : if(direction!='R')direction='L' ; break;
                case KeyEvent.VK_RIGHT:  if(direction!='L')direction='R' ; break;
                case KeyEvent.VK_DOWN:  if(direction!='U')direction='D' ; break;
                case KeyEvent.VK_UP : if(direction!='D')direction='U' ; break;
            }
            if(!running) {
               if( e.getKeyCode()==KeyEvent.VK_SPACE ) restart();
            }
        }
    }
}
