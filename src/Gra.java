import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gra extends JPanel implements KeyListener, ActionListener {
    private int x = 300;
    private int punkty = 0;
    private Timer time;
    private int speed = 20;
    private Random liczba = new Random();
    private int WrogX1 = liczba.nextInt(6)*100;
    private int WrogX2 = liczba.nextInt(6)*100;
    private int ey = 10;
    public Gra(){
        time = new Timer(10, this);
        time.start();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,700,600);
        //Gracz
        g.setColor(Color.CYAN);
        g.fillRect(x,475,75,75);
        //Wróg
        g.setColor(Color.red);
        g.fillRect(WrogX1,ey,100,100);
        g.fillRect(WrogX2,ey,100,100);
        //Punkty
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Punkty: " + punkty, 570, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        ey+=2;
        if(ey == 580){
            punkty++;
            ey = 10;
            WrogX1 = liczba.nextInt(6)*100;
            WrogX2 = liczba.nextInt(6)*100;
        }
        Rectangle gracz = new Rectangle(x,475,75,75);
        Rectangle wrog1 = new Rectangle(WrogX1,ey,100,100);
        Rectangle wrog2 = new Rectangle(WrogX2,ey,100,100);
        if(gracz.intersects(wrog1) || gracz.intersects(wrog2)){
            time.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            if(x == 0) {
                x = 0;
            }else {
                x -= speed;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            if(x == 600) {
                x = 600;
            }else {
                x += speed;
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            reset();
        }
    }

    private void reset() {
        ey = 10;
        WrogX1 = liczba.nextInt(6)*100;
        WrogX2 = liczba.nextInt(6)*100;
        x = 300;
        time.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
