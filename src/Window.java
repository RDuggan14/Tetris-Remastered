import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class Window extends JFrame {
//    private int dotX = 100;
//    private int dotY = 100;

    public Graphics gt;

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
            case KeyEvent.VK_ESCAPE:
                Main.Pause();
                break;
        }
        repaint();
    }


    public void Updater() {
        System.out.println("update");
        repaint();
    }


    @Override
    public void paint(Graphics g) {
        //Super.Paint is needed to run the Paint function // idk why just Java required
        super.paint(g);

        for (int i = 0; i < Main.LiveBlocks.length; i++) {
            g.setColor(Color.BLUE);
            g.fillRect(Main.LiveBlocks[i].xcord(), Main.LiveBlocks[i].ycord(), 5, 5);
        }
    }

    public void main(Window window) {
        SwingUtilities.invokeLater(() -> {
            window.setVisible(true);
            window.getGraphics();
            try {
                GameStart();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void GameStart() throws InterruptedException {
        gt = getGraphics();
        while (!Main.pause) {

            if (!Main.liveFall) {
                System.out.println("test");
                Physics.NewBlocks();
                System.out.printf(Arrays.toString(Main.LiveBlocks));
                Main.liveFall = true;
            }


            Main.tick++;
            Thread.sleep(1000);
            System.out.println(Main.tick);
            System.out.println(Main.LiveBlocks);
            update(gt);
            Physics.MoveDown(Main.LiveBlocks);
        }


    }
}
