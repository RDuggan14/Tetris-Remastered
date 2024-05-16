import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.sql.Time;
import java.util.Arrays;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

public class Window extends JFrame implements KeyListener {
//    private int dotX = 100;
//    private int dotY = 100;
    private BufferStrategy bs;
    public Graphics gt;
    public static Window window;
    private int TickUpdate = 10;
    public static int lastCheck;

    public Window() {
        setTitle("Tetris Remastered");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();
    }


    @Override
    public void keyTyped(KeyEvent e) {

        // Your keyTyped method implementation
    }

    @Override
    public void keyPressed(KeyEvent e) {

        moveDot(e);
        // Your keyPressed method implementation
    }

    @Override
    public void keyReleased(KeyEvent e) {

        // Your keyReleased method implementation
    }

public static void ChangeCheck(int x){
        lastCheck = x;
}

    private void moveDot(KeyEvent e) {

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                Physics.rotate();
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                MoveL();
                break;
            case KeyEvent.VK_RIGHT:
                MoveR();
                break;
            case KeyEvent.VK_SPACE:
                Physics.HardDrop();
                break;
            case KeyEvent.VK_ESCAPE:
                Main.Pause();
                break;
            case KeyEvent.VK_Z:
                Physics.SaveBlock();
                break;
        }
    }


    public void GridMaker() {
        Physics.CreateSavedBlock();
        Physics.NewField();
    }


    private void GridDraw(){
        for (int y = 0; y < Physics.Yarray.length; y++) {
            for (int x = 0; x < Physics.Xarray.length; x++){
                Color color = Physics.Yarray[y][x].Color;
                gt.setColor(color);
                gt.fillRect(Physics.Xpixels[x]+1, Physics.Ypixels[y]+1, 19, 19 );
            }
        }

        for (int y = 0; y < Physics.SavedY.length; y++) {
            Block[] xarry = Physics.SavedY[y];
            for(int x = 0; x < xarry.length;x++){

                gt.setColor(Color.white);
                gt.fillRect(xarry[x].xcord(), xarry[x].ycord()+40, 19, 19);
            }

        }
        for (int i = 0; i < Main.LiveBlocks.length; i++) {
            gt.setColor(Main.LiveBlocks[i].getColor());
            gt.fillRect(Physics.Xpixels[(Main.LiveBlocks[i].xcord())]+1, Physics.Ypixels[Main.LiveBlocks[i].ycord()]+1, 19, 19);
        }
    }

    public void MoveR() {
        if (Physics.CheckRight(Main.LiveBlocks)) {
            Physics.MoveRight(Main.LiveBlocks);
        }
    }

    public void MoveL(){
        if(Physics.CheckLeft(Main.LiveBlocks)){
            Physics.MoveLeft(Main.LiveBlocks);

        }
    }

    public void DrawLive(){

        for (int i = 0; i < Main.LiveBlocks.length; i++) {
            System.out.println(Main.LiveBlocks[i].Color);
            gt.setColor(Main.LiveBlocks[i].getColor());
            gt.fillRect(Physics.Xpixels[(Main.LiveBlocks[i].xcord())]+1, Physics.Ypixels[Main.LiveBlocks[i].ycord()]+1, 19, 19);
        }

    }


    @Override
    public void paint(Graphics g) {

        Graphics g2;

        do {
            g2 = (Graphics2D) bs.getDrawGraphics();
            try {
                g2 = (Graphics2D) bs.getDrawGraphics();
                for (int y = 0; y < Physics.Yarray.length; y++) {
                    for (int x = 0; x < Physics.Xarray.length; x++) {
                        Color color = Physics.Yarray[y][x].Color;
                        g2.setColor(color);
                        g2.fillRect(Physics.Xpixels[x] + 1, Physics.Ypixels[y] + 1, 19, 19);
                    }
                }

                for (int y = 0; y < Physics.SavedY.length; y++) {
                    Block[] xarry = Physics.SavedY[y];
                    for (int x = 0; x < xarry.length; x++) {

                        g2.setColor(Color.white);
                        g2.fillRect(xarry[x].xcord(), xarry[x].ycord() + 40, 19, 19);
                    }

                }
                for (int i = 0; i < Main.LiveBlocks.length; i++) {
                    g2.setColor(Main.LiveBlocks[i].getColor());
                    g2.fillRect(Physics.Xpixels[(Main.LiveBlocks[i].xcord())] + 1, Physics.Ypixels[Main.LiveBlocks[i].ycord()] + 1, 19, 19);
                }
            } finally {
                g2.dispose();

            }
            bs.show();
        }while(bs.contentsLost());
        //GridDraw();
        //Super.Paint is needed to run the Paint function // idk why just Java required
        //super.paint(gt);




//        for (int i = 0; i < Physics.GridLines.length; i++) {
//            g.setColor(Color.GRAY);
//            Grid current = Physics.GridLines[i];
//            g.drawLine(current.xcord, current.ycord, current.xxcord, current.yycord);
//        }
//        for (int i = 0; i < Physics.yGridlines.length; i++) {
//            g.setColor(Color.GRAY);
//            Grid current = Physics.yGridlines[i];
//            g.drawLine(current.xcord, current.ycord, current.xxcord, current.yycord);
//        }
    }

    public void main(Window window) {

            addKeyListener(this.window);
            window.setVisible(true);
            createBufferStrategy(2);
        bs = this.getBufferStrategy();

        window.getGraphics();
            this.window = window;
            try {
                GameStart();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }


    public static void BlockDropUpdater(){
        lastCheck = 0;
        Main.liveFall = false;
        Physics.SetBlocks();
        Physics.LineClear();
    }

    public void GameStart() throws InterruptedException {
        gt = getGraphics();
        GridMaker();
        GridDraw();




        while (!Main.pause) {

            if (!Main.liveFall) {
                Physics.NewBlocks();
                Main.liveFall = true;
            }


            Main.tick++;
            Thread.sleep(1000/60);
            if((Main.tick & 5) == 0){
                paint(gt);
                Physics.GridChecker();
                System.out.println("Break");
            }


            if(Main.tick % 20 == 0) {

                if (Physics.CheckDown(Main.LiveBlocks)) {
                    Physics.MoveDown(Main.LiveBlocks);


                } else {
                    if(lastCheck > 2){
                        BlockDropUpdater();
                    }
                    else{
                        lastCheck++;
                    }

                }

            }
        }


    }

}