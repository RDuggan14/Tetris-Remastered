import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Window extends JFrame {
//    private int dotX = 100;
//    private int dotY = 100;

    public Window() {
        setTitle("Tetris Remastered");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moveDot(e);
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }




    private void moveDot(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
               // dotY -= 10;

                break;
            case KeyEvent.VK_DOWN:
               // dotY += 10;

                break;
            case KeyEvent.VK_LEFT:
               // dotX -= 10;
                break;
            case KeyEvent.VK_RIGHT:
              //  dotX += 10;
                break;
        }
        repaint();
        System.out.println(Main.getWindow());
    }


    @Override
    public void paint(Graphics g) {
        //Super.Paint is needed to run the Paint function // idk why just Java required
        super.paint(g);

//        for(int i = 0; i < Main.dots.length; i++){
//            System.out.println(i);
//            g.setColor(Color.BLUE);
//            g.fillOval(Main.dots[i].xcord(), Main.dots[i].ycord(), DOT_SIZE, DOT_SIZE);
//        }
    }






}
