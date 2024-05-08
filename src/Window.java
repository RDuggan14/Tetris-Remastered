import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.Arrays;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

public class Window extends JFrame implements KeyListener {
//    private int dotX = 100;
//    private int dotY = 100;

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
        System.out.println("test");
        // Your keyTyped method implementation
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("test");
        moveDot(e);
        // Your keyPressed method implementation
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("test");

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
                System.out.println("Pressed");
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


    public void ChangeTick(int NewTick){
        TickUpdate = NewTick;
    }

    public void WhiteOut(Block[] Blocks){
        for(int i = 0; i < Blocks.length;i++){
            gt.setColor(Color.WHITE);
            gt.fillRect(Physics.Xpixels[(Blocks[i].xcord())]+1, Physics.Ypixels[Blocks[i].ycord()]+1, 19, 19);
        }
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
        for (int i = 0; i < Physics.SavedBlockGridLines.length; i++) {
            Grid current = Physics.SavedBlockGridLines[i];
            gt.drawLine(current.xcord(), current.ycord(), current.xcord(), current.yycord);

        }

    }

    public void Redraw(Block[] Blocks){
        for(int i = 0; i < Blocks.length;i++){
            gt.setColor(Blocks[i].getColor());
            gt.fillRect(Physics.Xpixels[(Blocks[i].xcord())]+1, Physics.Ypixels[Blocks[i].ycord()]+1, 19, 19);
        }
    }




    public void MoveR() {
        if (Physics.CheckRight(Main.LiveBlocks)) {
            WhiteOut(Main.LiveBlocks);
            Physics.MoveRight(Main.LiveBlocks);
            Redraw(Main.LiveBlocks);
        }
    }

    public void MoveL(){
        if(Physics.CheckLeft(Main.LiveBlocks)){
            WhiteOut(Main.LiveBlocks);
            Physics.MoveLeft(Main.LiveBlocks);
            Redraw(Main.LiveBlocks);
        }
    }

    private void GridDraw(){
        for (int i = 0; i < Physics.GridLines.length; i++) {
            gt.setColor(Color.GRAY);
            Grid current = Physics.GridLines[i];
            gt.drawLine(current.xcord, current.ycord, current.xxcord, current.yycord);
        }
        for (int i = 0; i < Physics.yGridlines.length; i++) {
            gt.setColor(Color.GRAY);
            Grid current = Physics.yGridlines[i];
            gt.drawLine(current.xcord, current.ycord, current.xxcord, current.yycord);
        }
        for (int i = 0; i < Physics.SavedBlockGridLines.length; i++) {
            gt.setColor(Color.GRAY);
            Grid current = Physics.SavedBlockGridLines[i];
            gt.drawLine(current.xcord, current.ycord, current.xxcord, current.yycord);
        }
    }

    @Override
    public void paint(Graphics g) {
        //Super.Paint is needed to run the Paint function // idk why just Java required
        super.paint(g);

        for (int y = 0; y < Physics.Yarray.length; y++) {
            for (int x = 0; x < Physics.Xarray.length; x++){
                Color color = Physics.Yarray[y][x].Color;
                g.setColor(color);
                g.fillRect(Physics.Xpixels[x]+1, Physics.Ypixels[y]+1, 19, 19 );
            }
        }

        for (int i = 0; i < Physics.SavedBlocks.length; i++) {
            g.setColor(Physics.SavedBlocks[i].getColor());
            g.fillRect(Physics.SavedBlocks[i].xcord(), Physics.SavedBlocks[i].ycord(), 19, 19);
        }

        for (int i = 0; i < Main.LiveBlocks.length; i++) {
            System.out.println(Main.LiveBlocks[i].Color);
            g.setColor(Main.LiveBlocks[i].getColor());
            g.fillRect(Physics.Xpixels[(Main.LiveBlocks[i].xcord())]+1, Physics.Ypixels[Main.LiveBlocks[i].ycord()]+1, 19, 19);
        }
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
            window.getGraphics();
            this.window = window;
            try {
                GameStart();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }


    public void GameStart() throws InterruptedException {
        gt = getGraphics();
        GridMaker();
        GridDraw();



        while (!Main.pause) {

            if (!Main.liveFall) {
                System.out.println("test");
                Physics.NewBlocks();
                System.out.printf(Arrays.toString(Main.LiveBlocks));
                Main.liveFall = true;
            }


            Main.tick++;
            Thread.sleep(1000/60);

            //Physics.GridChecker();
            if(Main.tick % 10 == 0) {
                if (Physics.CheckDown(Main.LiveBlocks)) {
                    Physics.MoveDown(Main.LiveBlocks);
                } else {
                    if(lastCheck > 2){
                        Physics.SetBlocks();
                        Physics.LineClear();
                        Main.liveFall = false;
                        lastCheck = 0;
                    }
                    else{
                        lastCheck++;
                    }

                }

                Updater();
            }
        }


    }

}