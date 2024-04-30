import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DotWindow extends JFrame {
    private int dotX = 100;
    private int dotY = 100;
    private final int DOT_SIZE = 20;


    public DotWindow() {
        setTitle("Moving Dot");
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

    public static int getWindowX(){
        return(400);
    }

    public static int getWindowY(){
        return(600);
    }

   // public static void newPaint(int xcord, int ycord) {
   //     super.paint(g);
  //      g.setColor(Color.BLACK);
   //     g.drawOval(Xcords, Ycords, 10, 5);
   // }

    private void moveDot(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                dotY -= 10;
                break;
            case KeyEvent.VK_DOWN:
                dotY += 10;

                break;
            case KeyEvent.VK_LEFT:
                dotX -= 10;
                break;
            case KeyEvent.VK_RIGHT:
                dotX += 10;
                break;
        }
        repaint();
        System.out.println(Main.getWindow());
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillOval(dotX, dotY, DOT_SIZE, DOT_SIZE);
    }

    public void newPaint(int Xcords, int Ycords, Graphics g){
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawOval(Xcords, Ycords, 10, 5);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DotWindow window = new DotWindow();
            window.setVisible(true);
        });
    }
}
