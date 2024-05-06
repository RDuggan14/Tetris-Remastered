import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame {
//    private int dotX = 100;
//    private int dotY = 100;

    public Graphics gt;
    public static Window window;

    public Window() {
        setTitle("Tetris Remastered");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        setFocusable(true);
        requestFocusInWindow();
    }


    public void key(){
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moveDot(e);
            }
        });
    }

    private void moveDot(KeyEvent e) {
        System.out.println("Pressed");
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
                MoveR();
                break;
            case KeyEvent.VK_ESCAPE:
                Main.Pause();
                break;
        }
        paint(gt);
    }


    public void Updater() {
        System.out.println("update");
        super.paint(gt);
        paint(gt);
    }

    public void GridMaker() {
        Physics.NewField();
        for (int i = 0; i < Physics.GridLines.length; i++) {
            Grid current = Physics.GridLines[i];
            gt.drawLine(current.xcord(), current.ycord(), current.xcord(), current.yycord);

        }
    }

    public void MoveR() {
        System.out.println("moveR");
        if (Physics.CheckRight(Main.LiveBlocks)) {
            System.out.println("CheckedR");

            Physics.MoveRight(Main.LiveBlocks);
        }
    }

    @Override
    public void paint(Graphics g) {
        //Super.Paint is needed to run the Paint function // idk why just Java required
        super.paint(g);

        for (int i = 0; i < Physics.Yarray.length; i++) {

        }

        for (int i = 0; i < Main.LiveBlocks.length; i++) {
            System.out.println(Main.LiveBlocks[i].Color);
            String color = Main.LiveBlocks[i].Color;
            g.setColor(Color.getColor(color));
            g.fillRect(Physics.Xpixels[(Main.LiveBlocks[i].xcord())], Physics.Ypixels[Main.LiveBlocks[i].ycord()], 20, 20);
        }
        for (int i = 0; i < Physics.GridLines.length; i++) {
            g.setColor(Color.GRAY);
            Grid current = Physics.GridLines[i];
            g.drawLine(current.xcord, current.ycord, current.xxcord, current.yycord);
        }
        for (int i = 0; i < Physics.yGridlines.length; i++) {
            g.setColor(Color.GRAY);
            Grid current = Physics.yGridlines[i];
            g.drawLine(current.xcord, current.ycord, current.xxcord, current.yycord);
        }
    }

    public void main(Window window) {
        SwingUtilities.invokeLater(() -> {
            window.setVisible(true);
            window.getGraphics();
            this.window = window;
            try {
                GameStart();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void GameStart() throws InterruptedException {
        gt = getGraphics();
        GridMaker();



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
            Updater();
            Physics.GridChecker();
            if (Physics.CheckDown(Main.LiveBlocks)) {
                Physics.MoveDown(Main.LiveBlocks);
            } else {
                Physics.SetBlocks();
                Main.liveFall = false;
            }

        }


    }

}