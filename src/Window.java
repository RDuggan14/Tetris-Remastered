import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame implements KeyListener {
//    private int dotX = 100;
//    private int dotY = 100;
    private BufferStrategy bs;
    public Graphics gt;
    public static Window window;
    private static Graphics g;
    private int TickUpdate = 10;
    public static int lastCheck;
    private boolean stopall = false;
    private static int Score = 0;
    public static int Level = 1;
    public static boolean animation = false;
    private static boolean tracer = false;
    private static String FilePath = "C:\\Users\\bacon\\IdeaProjects\\Tetris-Remastered\\HighScore.Txt";
    private static boolean gameover = true;



    public Window() {
        setTitle("Tetris Remastered");
        setSize(1200, 1000);
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
                if(gameover){
                    gameover = false;
                }
                else {
                    Main.Pause();
                }
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



private void GridAlign(Graphics g2){
    g2.setFont(new Font("Arial", Font.BOLD, 8));
    g2.setColor(Color.BLACK);
        for(int i = 0; i < 1200/25; i++){
            g2.drawLine(i*25, 0, i*25, 1000);
            g2.drawString(""+ i*25, i*25+3, 50);
        }
    for(int i = 0; i < 1000/50; i++){
        g2.drawLine(0, i*50, 1200, i*50);
        g2.drawString(""+ i*50, 10, i*50+10);
    }


    g2.drawString("Current Score", 650, 50);
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

    private void endgame(Graphics g2) throws IOException {
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 82));
        g2.drawString("Game Over", 375, 120);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        g2.drawString("Score", 500, 200);
        g2.drawString(String.valueOf(Score), 550, 250);

        DrawHighScores(g2);
    }

    private void DrawContollers(Graphics g2){
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("KeyBoard", 50, 100);
        g2.drawString("Hard Drop  --- SPACE", 50, 130);
        g2.drawString("Move Left   --- LEFT ARROW", 50, 160);
        g2.drawString("Move Right --- RIGHT ARROW", 50, 190);
        g2.drawString("Quick Drop --- DOWN ARROW", 50, 220);
        g2.drawString("Rotate        --- UP ARROW", 50, 250);
        g2.drawString("Save Block --- Z", 50, 280);
        g2.drawString("Turn On/Off Tracer Blocks --- L", 50, 310);

        g2.drawString("Controller", 50, 400);
        g2.drawString("Hard Drop  --- X", 50, 430);
        g2.drawString("Move Left   --- STICK LEFT", 50, 460);
        g2.drawString("Move Right --- STICK RIGHT", 50, 490);
        g2.drawString("Quick Drop --- Z", 50, 520);
        g2.drawString("Rotate        --- Y", 50, 550);
        g2.drawString("Save Block --- Right Bumper", 50, 580);
        g2.drawString("Toggle Tracer Blocks --- B", 50, 610);


    }

    private static void DrawHighScores(Graphics g2) throws IOException {
        g2.setColor(Color.BLACK);
        g2.fillRect(450, 300, 300, 300);
        g2.setColor(Color.WHITE);
        g2.fillRect(455, 305, 290, 300);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.drawString("High Scores", 500, 280);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        for(int i = 0; i < 20; i++){
            if(i % 2 == 0){
                g2.drawString(i/2 + 1 +".", 460, 325 + i*20);
                g2.drawString(FileHandler.readSpecificLine(FilePath, i+1), 485, 325 + i*20);
                g2.drawString(FileHandler.readSpecificLine(FilePath, i+2), 650, 325 + i*20);
            }
        }

//        g2.drawString("Name1", 460, 325);
//        g2.drawString("0", 700, 325);
//        g2.drawString("Name1223232", 460, 360);
//        g2.drawString("Tod3", 460, 395);
//        g2.drawString("BoooPraa4", 460, 425);
//        g2.drawString("Christapher5", 460, 460);
    }



    @Override
    public void paint(Graphics g) {
        Graphics g2;
        if(!Main.pause) {
            do {
                g2 = (Graphics2D) bs.getDrawGraphics();
                try {
                    g2 = (Graphics2D) bs.getDrawGraphics();

                    if(!Main.pause) {
                        super.paint(g2);
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
                        if (tracer) {

                            Block[] tracercords = Physics.Tracer();
                            for (int i = 0; i < tracercords.length; i++) {
                                if (tracercords[i].ycord() != Main.LiveBlocks[i].ycord()) {
                                    g2.setColor(Main.LiveBlocks[i].getColor());
                                    g2.fillRect(Physics.Xpixels[(tracercords[i].xcord())] + 1, Physics.Ypixels[tracercords[i].ycord()] + 1, 19, 19);
                                    g2.setColor(Color.WHITE);
                                    g2.fillRect(Physics.Xpixels[(tracercords[i].xcord())] + 2, Physics.Ypixels[tracercords[i].ycord()] + 3, 16, 15);
                                }
                            }
                        }

                        for (int i = 0; i < Main.LiveBlocks.length; i++) {
                            g2.setColor(Main.LiveBlocks[i].getColor());
                            g2.fillRect(Physics.Xpixels[(Main.LiveBlocks[i].xcord())] + 1, Physics.Ypixels[Main.LiveBlocks[i].ycord()] + 1, 19, 19);
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
                    }
                    else{
                        super.paint(g2);
                        g2.setColor(Color.lightGray);
                        g2.fillRect(0, 0, 400, 800);
                        g2.setColor(Color.white);
                        g2.fillRect(620, 10, 150, 100);
                        g2.setFont(new Font("Arial", Font.BOLD, 14));
                        g2.setColor(Color.BLACK);
                        g2.drawString("Current Score", 650, 50);
                        g2.drawString(String.valueOf(Score), 670, 70);

                    }
                } finally {
                    bs.show();
                    g2.dispose();
                }
            } while (bs.contentsLost()) ;

        }
        else{

            do {
                g2 = (Graphics2D) bs.getDrawGraphics();
                try {
                    g2 = (Graphics2D) bs.getDrawGraphics();
                    g2.setColor(Color.white);
                    if(!gameover){
                        g2.fillRect(0, 0, 1200, 1000);
                        g2.setColor(Color.BLACK);
                        g2.setFont(new Font("Arial", Font.BOLD, 82));
                        g2.drawString("Tetris", 500, 120);

                        g2.setFont(new Font("Arial", Font.BOLD, 26));
                        g2.setColor(Color.BLACK);
                        g2.drawString("Current Score", 975, 100);
                        g2.drawString(String.valueOf(Score), 1050, 135);
                        g2.drawString("High Score", 990, 175);
                        g2.drawString(String.valueOf(Score), 1050, 200);

                        g2.setFont(new Font("Arial", Font.ITALIC, 42));
                        g2.drawString("Resume", 530, 200);
                        DrawHighScores(g2);
                        GridAlign(g2);
                        DrawContollers(g2);
                    }
                    else{
                        endgame(g2);
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    g2.dispose();
                    bs.show();
                }
            } while (bs.contentsLost()) ;

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
        for(int i = 0; i < Main.LiveBlocks.length; i++){
            if(Main.LiveBlocks[i].ycord() == 2){
                Main.pause = true;
                gameover = true;
            }
        }
        Main.liveFall = false;
        Physics.SetBlocks();
        Physics.LineClear();
        Physics.UpdateQueue();
    }

    public static void ChangeAnimation(boolean change){
        animation = change;
    }

    public static void update(){
        window.paint(g);
    }

    public void GameStart() throws InterruptedException {
        gt = getGraphics();
        g = gt;
        GridMaker();
        GridDraw();

        while (!stopall) {



            if (!Main.pause) {
                Main.tick++;
                Thread.sleep(1000 / 60);


                    if (!Main.liveFall) {
                        Physics.NewBlocks();
                        Physics.UpdateQueue();
                        Main.liveFall = true;
                    }


                    if ((Main.tick & 5) == 0) {
                        paint(gt);
                        Physics.GridChecker();
                        System.out.println("Break");
                    }


                    if (Main.tick % 40 == 0) {
                        if (!animation) {

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

            }
            else{
                repaint();
                Thread.sleep(1000 / 60);

            }


        }
    }
}