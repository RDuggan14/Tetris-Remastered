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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillOval(dotX, dotY, DOT_SIZE, DOT_SIZE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DotWindow window = new DotWindow();
            window.setVisible(true);
        });
    }
}
