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
        createBufferStrategy(2);
        bs = this.getBufferStrategy();
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

    public void GridMaker() {
        Physics.CreateSavedBlock();
        Physics.NewField();
    }

    public void Redraw(Block[] Blocks){
        for(int i = 0; i < Blocks.length;i++){
            gt.setColor(Blocks[i].getColor());
            gt.fillRect(Physics.Xpixels[(Blocks[i].xcord())]+1, Physics.Ypixels[Blocks[i].ycord()]+1, 19, 19);
        }
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
            System.out.println(Main.LiveBlocks[i].Color);
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
        super.paint(g);
        Graphics g2 = null;
        try{
            g2 = (Graphics) bs.getDrawGraphics();

        }finally{
            g2.dispose();

        }
        bs.show();

        GridDraw();
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
            window.getGraphics();
            this.window = window;
            try {
                GameStart();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }


    public static void BlockDropUpdater(){
        Physics.SetBlocks();
        Physics.LineClear();
        Main.liveFall = false;
        lastCheck = 0;
    }

    public void GameStart() throws InterruptedException {
        gt = getGraphics();
        GridMaker();
        GridDraw();
        java.awt.Window[] c = getWindows();
        c[0].setBackground(Color.BLUE);



        while (!Main.pause) {

            if (!Main.liveFall) {
                Physics.NewBlocks();
                Main.liveFall = true;
            }


            Main.tick++;
            Thread.sleep(1000/60);
            if((Main.tick & 5) == 0){
                GridDraw();
            }

            //Physics.GridChecker();
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