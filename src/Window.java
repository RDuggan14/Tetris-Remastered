import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Window extends JFrame implements KeyListener {
//    private int dotX = 100;
//    private int dotY = 100;
    private BufferStrategy bs;
    public Graphics gt;
    public static Window window;
    private int TickUpdate = 10;
    public static int lastCheck;
    private boolean stopall = false;
    private static int Score = 0;
    public static int Level = 1;
    private static boolean tracer = false;
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
                Physics.SpeedDrop();
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
            case KeyEvent.VK_L:
                if(tracer){
                    tracer = false;
                }else{
                    tracer = true;
                }
                break;
        }
    }


    public void GridMaker() {
        Physics.CreateSavedBlock();
        Physics.CreateQueue();
        Physics.NewField();
    }

    public static void ChangeScore(int change){
        Score = Score + change;
    }

    public void pauseMenu(){
        JLabel Pause = new JLabel("paused",JLabel.CENTER);
        Pause.setBounds(20, 10, 20 ,20);
        //JLabel Keys = new JLabel("    L  --  Activate Outlines");
        add(Pause);
        setVisible(true);
        //add(Keys);
        setVisible(true);
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



    @Override
    public void paint(Graphics g) {
        Graphics g2;
        if(!Main.pause) {
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

                            g2.setColor(xarry[x].getColor());
                            g2.fillRect(xarry[x].xcord(), xarry[x].ycord() + 40, 19, 19);
                        }

                    }

                    for (int y = 0; y < Physics.Queue.length; y++) {
                        for (int z = 0; z < 5; z++) {
                            for (int x = 0; x < 6; x++) {
                                g2.setColor(Physics.Queue[y][z][x].getColor());
                                g2.fillRect(Physics.Queue[y][z][x].xcord(), Physics.Queue[y][z][x].ycord() + 40, 19, 19);

                            }

                        }
                    }

                    for (int i = 0; i < Main.LiveBlocks.length; i++) {
                        g2.setColor(Main.LiveBlocks[i].getColor());
                        g2.fillRect(Physics.Xpixels[(Main.LiveBlocks[i].xcord())] + 1, Physics.Ypixels[Main.LiveBlocks[i].ycord()] + 1, 19, 19);
                    }


                    if(tracer){

                        Block[] tracercords = Physics.Tracer();
                            for (int i = 0; i < tracercords.length; i++) {
                                if(tracercords[i].ycord() != Main.LiveBlocks[i].ycord()) {
                                    g2.setColor(Main.LiveBlocks[i].getColor());
                                    g2.fillRect(Physics.Xpixels[(tracercords[i].xcord())] + 1, Physics.Ypixels[tracercords[i].ycord()] + 1, 19, 19);
                                    g2.setColor(Color.WHITE);
                                    g2.fillRect(Physics.Xpixels[(tracercords[i].xcord())] + 2, Physics.Ypixels[tracercords[i].ycord()] + 3, 16, 15);
                                }
                            }
                    }

                    File file = new File("HighScore.Txt");
                    g2.setColor(Color.white);
                    g2.fillRect(620, 10, 150, 100);
                    g2.setFont(new Font("Arial", Font.BOLD, 14));
                    g2.setColor(Color.BLACK);
                    g2.drawString("Current Score", 650, 50);
                    g2.drawString(String.valueOf(Score), 670, 70);
                    //g2.drawString("HighScore", 690, 50);
//                    FileReader fileCode = (new FileReader(file));
//                    String high = fileCode.toString();
//                    g2.drawString(high,690, 80);

                } finally {
                    bs.show();
                    g2.dispose();
                }
            } while (bs.contentsLost()) ;

        }
        else{
            pauseMenu();
        }
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
        Physics.UpdateQueue();
    }



    public void GameStart() throws InterruptedException {
        gt = getGraphics();
        GridMaker();
        GridDraw();

        while (!stopall) {
            Main.tick++;
            Thread.sleep(1000 / 60);


            if (!Main.pause) {

                if (!Main.liveFall) {
                    Physics.NewBlocks();
                    Physics.UpdateQueue();
                    Main.liveFall = true;
                }


                if ((Main.tick & 5) == 0) {
                    paint(gt);
                    //Physics.GridChecker();
                    Physics.SavedChecker();
                    System.out.println("Break");
                }


                if (Main.tick % 40 == 0) {

                    if (Physics.CheckDown(Main.LiveBlocks)) {
                        Physics.MoveDown(Main.LiveBlocks);


                    } else {
                        if (lastCheck > 2) {
                            BlockDropUpdater();
                        } else {
                            lastCheck++;
                        }

                    }

                }
            }
            else{
                pauseMenu();
            }


        }
    }
}