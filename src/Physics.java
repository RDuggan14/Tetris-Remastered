import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Physics {


    public static int[] Xpixels = {200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420};
    public static int[] Ypixels = {0, 20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400};
    public static Block[] Xarray = {new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block()};
    public static Block[][] Yarray = new Block[20][];
    private static int CurrentBlock;
    private static int P1CurrentBlock;
    private static int P2CurrentBlock;
    private  static int RotatePOS;
    private static int P1rotate;
    private static int P2rotate;
    public static int TSLC = 0;
    public static int SavedID;
    public static Block[][] SavedY = new Block[5][];
    public static Block[][][] Queue = new Block[3][6][];
    private static int[] BlockQueue = {generateRandomNumber(),generateRandomNumber(),generateRandomNumber()};
    //INT TO BLOCK
//    1 - 4Long
//    2 - TPiece
//    3 - ZigRight
//    4 - ZigLeft
//    5 - Sqaure
//    6 - LRifht
//    7 - LLeft



        public static void CreateSavedBlock(){
            for(int i = 0; i < 5; i++){
                Color color = Color.WHITE;
                Block[] xarray = {new Block(20, 20*i, color),new Block(40, 20*i,color),new Block(60, 20*i,color),new Block(80, 20*i,color),new Block(100, 20*i,color),new Block(120, 20*i,color)};
                SavedY[i] = xarray;
            }
        }

        public static void CreateQueue(){
            for(int i = 0; i < 3; i++){
                for(int y = 0; y < 5; y++){
                    Color color = Color.WHITE;
                    Block[] temp = {new Block(440, 20*y + (100*i), color),new Block(460, 20*y + (100*i),color),new Block(480, 20*y + (100*i),color),new Block(500, 20*y + (100*i),color),new Block(520, 20*y + (100*i),color),new Block(540, 20*y + (100*i),color)};
                    Queue[i][y] = temp;
                }

            }
        }


        public static void NewField() {
        for(int y = 0; y < 20;y++) {
            Block[] Xarray = new Block[12];
            for (int i = 0; i < 12; i++) {
                Xarray[i] = new Block();
            }
            Yarray[y] = Xarray;
        }


    }

    public static void rotate(int Player){
            Block[] NewCords = {new Block(), new Block(), new Block(), new Block()};
            Block Updated;
            if(Player == 2){
                if (P2CurrentBlock == 1) {

                    if (P2rotate == 0) {
                        Block[] Pattern = {new Block(0, 1), new Block(0, 2), new Block(0, 3), new Block(0, 4)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 1) {
                        Block[] Pattern = {new Block(0, 0), new Block(1, 0), new Block(2, 0), new Block(3, 0)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate = 0;

                    }
                }
                if (P2CurrentBlock == 2) {
                    if (P2rotate == 0) {
                        Block[] Pattern = {new Block(0, 1), new Block(0, -1), new Block(0, 0), new Block(1, 0)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 1) {
                        Block[] Pattern = {new Block(1, 0), new Block(-1, 0), new Block(0, 0), new Block(0, 1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 2) {
                        Block[] Pattern = {new Block(0, 1), new Block(0, -1), new Block(0, 0), new Block(-1, 0)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 3) {
                        Block[] Pattern = {new Block(1, 0), new Block(-1, 0), new Block(0, 0), new Block(0, -1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate = 0;

                    }

                }
                if (P2CurrentBlock == 3) {
                    if (P2rotate == 0) {
                        Block[] Pattern = {new Block(0, 0), new Block(1, 1), new Block(1, 0), new Block(0, -1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 1) {
                        Block[] Pattern = {new Block(1, -1), new Block(0, -1), new Block(0, 0), new Block(-1, 0)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate = 0;

                    }

                }
                if (P2CurrentBlock == 4) {
                    if (P2rotate == 0) {
                        Block[] Pattern = {new Block(0, 0), new Block(-1, 1), new Block(-1, 0), new Block(0, -1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 1) {
                        Block[] Pattern = {new Block(0, 0), new Block(-1, 0), new Block(0, 1), new Block(1, 1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate = 0;

                    }

                }
                if (P2CurrentBlock == 6) {
                    if (P2rotate == 0) {
                        Block[] Pattern = {new Block(0, 0), new Block(0, 1), new Block(0, -1), new Block(1, 1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 1) {
                        Block[] Pattern = {new Block(0, 0), new Block(1, 0), new Block(-1, 0), new Block(-1, 1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 2) {
                        Block[] Pattern = {new Block(0, 0), new Block(0, 1), new Block(0, -1), new Block(-1, -1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 3) {
                        Block[] Pattern = {new Block(0, 0), new Block(1, 0), new Block(-1, 0), new Block(1, -1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate = 0;

                    }

                }
                if (P2CurrentBlock == 7) {
                    if (P2rotate == 0) {
                        Block[] Pattern = {new Block(0, 0), new Block(0, 1), new Block(0, -1), new Block(1, -1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 1) {
                        Block[] Pattern = {new Block(0, 0), new Block(-1, 0), new Block(1, 0), new Block(1, 1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 2) {
                        Block[] Pattern = {new Block(0, 0), new Block(0, 1), new Block(0, -1), new Block(-1, 1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate++;

                    } else if (P2rotate == 3) {
                        Block[] Pattern = {new Block(0, 0), new Block(-1, 0), new Block(1, 0), new Block(-1, -1)};
                        rotationPattern(Pattern, 2, Main.P1Blocks);
                        P2rotate = 0;

                    }

                }
            }
            if(Player == 1) {
                if (P1CurrentBlock == 1) {
                    if (P1rotate == 0) {
                        Block[] Pattern = {new Block(0, 1), new Block(0, 2), new Block(0, 3), new Block(0, 4)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (RotatePOS == 1) {
                        Block[] Pattern = {new Block(0, 0), new Block(1, 0), new Block(2, 0), new Block(3, 0)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate = 0;

                    }
                }
                if (P1CurrentBlock == 2) {
                    if (P1rotate == 0) {
                        Block[] Pattern = {new Block(0, 1), new Block(0, -1), new Block(0, 0), new Block(1, 0)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 1) {
                        Block[] Pattern = {new Block(1, 0), new Block(-1, 0), new Block(0, 0), new Block(0, 1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 2) {
                        Block[] Pattern = {new Block(0, 1), new Block(0, -1), new Block(0, 0), new Block(-1, 0)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 3) {
                        Block[] Pattern = {new Block(1, 0), new Block(-1, 0), new Block(0, 0), new Block(0, -1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate = 0;

                    }

                }
                if (P1CurrentBlock == 3) {
                    if (P1rotate == 0) {
                        Block[] Pattern = {new Block(0, 0), new Block(1, 1), new Block(1, 0), new Block(0, -1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 1) {
                        Block[] Pattern = {new Block(1, -1), new Block(0, -1), new Block(0, 0), new Block(-1, 0)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate = 0;

                    }

                }
                if (P1CurrentBlock == 4) {
                    if (P1rotate == 0) {
                        Block[] Pattern = {new Block(0, 0), new Block(-1, 1), new Block(-1, 0), new Block(0, -1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 1) {
                        Block[] Pattern = {new Block(0, 0), new Block(-1, 0), new Block(0, 1), new Block(1, 1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate = 0;

                    }

                }
                if (P1CurrentBlock == 6) {
                    if (P1rotate == 0) {
                        Block[] Pattern = {new Block(0, 0), new Block(0, 1), new Block(0, -1), new Block(1, 1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 1) {
                        Block[] Pattern = {new Block(0, 0), new Block(1, 0), new Block(-1, 0), new Block(-1, 1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 2) {
                        Block[] Pattern = {new Block(0, 0), new Block(0, 1), new Block(0, -1), new Block(-1, -1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 3) {
                        Block[] Pattern = {new Block(0, 0), new Block(1, 0), new Block(-1, 0), new Block(1, -1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate = 0;

                    }

                }
                if (P1CurrentBlock == 7) {
                    if (P1rotate == 0) {
                        Block[] Pattern = {new Block(0, 0), new Block(0, 1), new Block(0, -1), new Block(1, -1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 1) {
                        Block[] Pattern = {new Block(0, 0), new Block(-1, 0), new Block(1, 0), new Block(1, 1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 2) {
                        Block[] Pattern = {new Block(0, 0), new Block(0, 1), new Block(0, -1), new Block(-1, 1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate++;

                    } else if (P1rotate == 3) {
                        Block[] Pattern = {new Block(0, 0), new Block(-1, 0), new Block(1, 0), new Block(-1, -1)};
                        rotationPattern(Pattern, 1, Main.P1Blocks);
                        P1rotate = 0;

                    }

                }
            }
    }

        public static int generateRandomNumber() {
            Random random = new Random();
            return random.nextInt(7); // generates a random integer between 0 (inclusive) and 8 (exclusive)
        }

public static void LineClear(){
            int[] ClearLines = {99,99,99,99};
            int LineClears = 0;
            int totalLines = 0;
            Block[][] HoldingVal = new Block[4][10];
    Block[] WhiteArray  = {new Block(),new Block(),new Block(), new Block(),new Block(),new Block(), new Block(),new Block(),new Block(),new Block()};

            for(int i = 0; i < Yarray.length;i++){
                int LineOccu = 0;
                Block[] CurrentYLine = Yarray[i];
                System.out.println(Arrays.toString(CurrentYLine));
                 for(int x = 0; x < CurrentYLine.length;x++){
                     if(CurrentYLine[x].Occupied){
                         LineOccu++;
                     }

                 }
                 if(LineOccu == CurrentYLine.length){
                     ClearLines[LineClears] = i;
                     LineClears++;
                 }
            }
    for(int i = 0; i < ClearLines.length; i++){
        if(ClearLines[i] != 99) {
            Window.ChangeAnimation(true);
        HoldingVal[i] = Yarray[ClearLines[i]];
        Yarray[i] = WhiteArray;
        }
    }
    if(Window.animation) {
        int x = Main.tick;
        int z = x + 25;
        while (x < z) {
            System.out.println(x);
            System.out.println(z);
            x = Main.tick;
            if (x % 5 == 0) {
                for(int i = 0; i < ClearLines.length; i++){
                    if(ClearLines[i] != 99){
                        Yarray[ClearLines[i]] = WhiteArray;
                    }
                }
            }
            if (x % 10 == 0) {
                for(int i = 0; i < ClearLines.length; i++){
                    if(ClearLines[i] != 99){
                        Yarray[ClearLines[i]] = HoldingVal[i];
                    }
                }
            }
            Window.update();
        }
        for(int i = 0; i < ClearLines.length; i++){
            if(ClearLines[i] != 99){
                Yarray[ClearLines[i]] = HoldingVal[i];
            }
        }
    }
            for(int i = 0; i < ClearLines.length; i++){
                Block[] tempx = {new Block(),new Block(),new Block(), new Block(),new Block(),new Block(), new Block(),new Block(),new Block(),new Block()};
                for (int z = 0; z < 10; z++) {
                    tempx[z] = new Block();
                }
                Yarray[0] = tempx;
                System.out.println("NEW LINE " + Arrays.toString(Yarray));
                if(ClearLines[i] != 99){
                    totalLines++;
                    for(int y = 0; y < ClearLines[i];y++){
                        int r = ClearLines[i] - y;
                            Yarray[r] = Yarray[(r-1)];
                    }

                }
                Window.ChangeScore(100 * totalLines * Window.Level);
                Window.ChangeAnimation(false);
            }
}




    public static void UpdateQueue(){
        CreateQueue();
            for(int i = 0; i < 3; i++){
                int number = BlockQueue[i];
                if (number == 0) {
                    Queue[i][2][1].UpdateBlock(true, Color.BLUE);
                    Queue[i][2][2].UpdateBlock(true, Color.BLUE);
                    Queue[i][2][3].UpdateBlock(true, Color.BLUE);
                    Queue[i][2][4].UpdateBlock(true, Color.BLUE);
                }
                if (number == 1) {
                    Queue[i][1][3].UpdateBlock(true, Color.PINK);
                    Queue[i][1][2].UpdateBlock(true, Color.PINK);
                    Queue[i][2][2].UpdateBlock(true, Color.PINK);
                    Queue[i][2][1].UpdateBlock(true, Color.PINK);
                }
                if (number == 2) {
                    Queue[i][1][1].UpdateBlock(true, Color.red);
                    Queue[i][1][2].UpdateBlock(true, Color.red);
                    Queue[i][2][2].UpdateBlock(true, Color.red);
                    Queue[i][2][3].UpdateBlock(true, Color.red);
                }
                if (number == 3) {
                    Queue[i][2][1].UpdateBlock(true, Color.MAGENTA);
                    Queue[i][2][2].UpdateBlock(true, Color.MAGENTA);
                    Queue[i][2][3].UpdateBlock(true, Color.MAGENTA);
                    Queue[i][1][2].UpdateBlock(true, Color.MAGENTA);
                }
                if (number == 4) {
                    Queue[i][1][1].UpdateBlock(true, Color.ORANGE);
                    Queue[i][1][2].UpdateBlock(true, Color.ORANGE);
                    Queue[i][2][1].UpdateBlock(true, Color.ORANGE);
                    Queue[i][2][2].UpdateBlock(true, Color.ORANGE);
                }
                if (number == 5) {
                    Queue[i][2][1].UpdateBlock(true, Color.CYAN);
                    Queue[i][2][2].UpdateBlock(true, Color.CYAN);
                    Queue[i][2][3].UpdateBlock(true, Color.CYAN);
                    Queue[i][1][1].UpdateBlock(true, Color.CYAN);
                }
                if (number == 6) {
                    Queue[i][2][1].UpdateBlock(true, Color.BLUE);
                    Queue[i][2][2].UpdateBlock(true, Color.BLUE);
                    Queue[i][2][3].UpdateBlock(true, Color.BLUE);
                    Queue[i][1][3].UpdateBlock(true, Color.BLUE);
                }


            }
    }


    public static boolean CordChecker(Block[] Blocks){
            for(var i = 0; i < Blocks.length; i++){
                if (Blocks[i].ycord() < 0 || Blocks[i].ycord() > 19){
                    return(false);
                }
                if (Blocks[i].xcord() < 0 || Blocks[i].xcord() > 9){
                    return(false);
                }
                if(Yarray[Blocks[i].ycord()][Blocks[i].xcord()].Occupied){
                    return(false);
                }
            }
            return(true);
    }


    public static void rotationPattern(Block[] BlockPattern, int Player, Block[] Blocks){


            for(var i = 0; i < 4; i++){
                Block[] CheckerList = {new Block(),new Block(),new Block(),new Block(),};
                for(var x = 0; x < 4; x++){
                    int xdift = BlockPattern[x].xcord();
                    int ydift = BlockPattern[x].ycord();

                    CheckerList[x] = new Block(Blocks[i].xcord()+xdift, Blocks[i].ycord()+ydift, Blocks[x].getColor());
                    System.out.println("CORDS " + CheckerList[x].xcord() + " " + CheckerList[x].ycord());
                }

                if(CordChecker(CheckerList)){
                    if(Player == 1){
                        Main.P1Blocks = CheckerList;
                    }
                    if(Player == 2){
                        Main.P2Blocks = CheckerList;
                    }
                    return;
                }
            }
        for(var i = 0; i < 4; i++){
            Block[] CheckerList = {new Block(),new Block(),new Block(),new Block(),};
            for(var x = 0; x < 4; x++){
                int xdift = -BlockPattern[x].xcord();
                int ydift = -BlockPattern[x].ycord();

                CheckerList[x] = new Block(Blocks[i].xcord()+xdift, Blocks[i].ycord()+ydift, Blocks[x].getColor());
                System.out.println("CORDS " + CheckerList[x].xcord() + " " + CheckerList[x].ycord());
            }

            if(CordChecker(CheckerList)){
                if(Player == 1){
                    Main.P1Blocks = CheckerList;
                }
                if(Player == 2){
                    Main.P2Blocks = CheckerList;
                }
                return;
            }
        }

    }

    private static void BulkX(Block[] Array, int Change){
            for(int i = 0; i < Array.length; i++){
                Array[i].xchange(Array[i].xcord()+Change);
            }
    }

    public static void SpeedDrop(int Player){
        if(Player == 1) {
            if (Physics.CheckDown(Main.P1Blocks)) {
                Physics.MoveDown(Main.P1Blocks);

            }
        }
            if(Player == 2) {
                if (Physics.CheckDown(Main.P2Blocks)) {
                    Physics.MoveDown(Main.P2Blocks);
                }
            }
    }

    public static void NewBlocks(int Player) {
            if(Player == 1){
                P1rotate = 0;
            }
            else{
                P2rotate = 0;
            }

        int randomNumber = BlockQueue[0];

        BlockQueue[0] = BlockQueue[1];
        BlockQueue[1] = BlockQueue[2];
        BlockQueue[2] = generateRandomNumber();


        if(randomNumber == 0){
            Main.SetLiveBlocks(Fourlong(), Player);
            if(Player == 1){
                BulkX(Main.P1Blocks, 2);
            }
            else{
                BulkX(Main.P2Blocks, -2);
            }
        }
        if(randomNumber == 1){
            Main.SetLiveBlocks(ZigRight(), Player);
            if(Player == 1){
                BulkX(Main.P1Blocks, 2);
            }
            else{
                BulkX(Main.P2Blocks, -2);
            }
        }
        if(randomNumber == 2){
            Main.SetLiveBlocks(ZigLeft(), Player);
            if(Player == 1){
                BulkX(Main.P1Blocks, 2);
            }
            else{
                BulkX(Main.P2Blocks, -2);
            }
        }
        if(randomNumber == 3){
            Main.SetLiveBlocks(TPeice(), Player);
            if(Player == 1){
                BulkX(Main.P1Blocks, 2);
            }
            else{
                BulkX(Main.P2Blocks, -2);
            }
        }
        if(randomNumber == 4){
            Main.SetLiveBlocks(Square(), Player);
            if(Player == 1){
                BulkX(Main.P1Blocks, 2);
            }
            else{
                BulkX(Main.P2Blocks, -2);
            }
        }
        if(randomNumber == 5){
            Main.SetLiveBlocks(LRight(), Player);
            if(Player == 1){
                BulkX(Main.P1Blocks, 2);
            }
            else{
                BulkX(Main.P2Blocks, -2);
            }
        }
        if(randomNumber == 6){
            Main.SetLiveBlocks(LLeft(), Player);
            if(Player == 1){
                BulkX(Main.P1Blocks, 2);
            }
            else{
                BulkX(Main.P2Blocks, -2);
            }
        }
    if(Player == 1){
    P1CurrentBlock = CurrentBlock;
    }
        if(Player == 2){
            P2CurrentBlock = CurrentBlock;
        }
    }

    public static Block[] Tracer(){
        Block[] Tracer = {new Block(), new Block(), new Block(), new Block()};
        for(int i = 0; i < Main.LiveBlocks.length; i++){
            Block temp = new Block();
            temp.ychange(Main.LiveBlocks[i].ycord());
            temp.xchange(Main.LiveBlocks[i].xcord());
            Tracer[i] = temp;
        }
        if (!CheckDown(Tracer)) {
            return Tracer;
        } else {
            while (CheckDown(Tracer)) {
                for (int i = 0; i < Tracer.length; i++) {
                    Tracer[i].ychange(Tracer[i].ycord() + 1);
                }
            }
        }
        return Tracer;
    }




    public static void HardDrop(int Player) {
        Block[] TempChords = Main.P1Blocks;
            if(Player == 1){
                 TempChords = Main.P1Blocks;
            }
            else{
                 TempChords = Main.P2Blocks;
            }
        if (!CheckDown(TempChords)) {
            Window.BlockDropUpdater(Player);

        } else {
            int totalDrop = 0;
            while (CheckDown(TempChords)) {
                for (int i = 0; i < TempChords.length; i++) {
                    TempChords[i].ychange(TempChords[i].ycord() + 1);
                }
                totalDrop++;
            }
            if(Player == 1){
                Main.P1Blocks = TempChords;
            }
            else{
                Main.P2Blocks = TempChords;
            }
            Window.BlockDropUpdater(Player);
            Window.ChangeScore(5 * totalDrop * Window.Level);
        }
    }

    public static void MoveDown(Block[] LiveBlocks) {
        for (int i = 0; i < LiveBlocks.length; i++) {
            LiveBlocks[i].ychange((LiveBlocks[i].ycord() + 1));
        }
    }

    public static Block[] Fourlong() {
            CurrentBlock = 1;
        Block[] Blocks = {new Block(5, 1, Color.BLUE), new Block(6, 1, Color.BLUE), new Block(7, 1, Color.BLUE), new Block(8, 1,Color.BLUE)};
        return (Blocks);
    }

    public static Block[] ZigRight(){
        CurrentBlock = 3;
            Color color = Color.PINK;
        Block[] Blocks = {new Block(4, 2, color), new Block(5, 2, color), new Block(5, 1, color), new Block(6, 1,color)};
        return (Blocks);
    }

    public static Block[] ZigLeft(){
        CurrentBlock = 4;
        Color color = Color.RED;
        Block[] Blocks = {new Block(4, 1, color), new Block(5, 1, color), new Block(5, 2, color), new Block(6, 2,color)};
        return (Blocks);
    }

    public static Block[] TPeice(){
        CurrentBlock = 2;
        Color color = Color.MAGENTA;
        Block[] Blocks = {new Block(4, 2, color), new Block(5, 2, color), new Block(5, 1, color), new Block(6, 2,color)};
        return (Blocks);
    }

    public static Block[] Square(){
        CurrentBlock = 5;
        Color color = Color.ORANGE;
        Block[] Blocks = {new Block(4, 2, color), new Block(4, 1, color), new Block(5, 1, color), new Block(5, 2,color)};
        return (Blocks);
    }

    public static Block[] LRight(){
        CurrentBlock = 6;
        Color color = Color.CYAN;
        Block[] Blocks = {new Block(4, 2, color), new Block(5, 2, color), new Block(6, 2, color), new Block(6, 1,color)};
        return (Blocks);
    }

    public static Block[] LLeft(){
        CurrentBlock = 7;
        Color color = Color.BLUE;
        Block[] Blocks = {new Block(4, 2, color), new Block(4, 1, color), new Block(5, 2, color), new Block(6, 2,color)};
        return (Blocks);
    }


    public static Block GetBlock(int x, int y) {
        Block current = Yarray[y][x];
        return (current);
    }


    public static boolean CheckRight(Block[] Blocks){
        for(int i = 0; i < Blocks.length; i++){
            Block current = Blocks[i];
            if(current.xcord() > Yarray[0].length){
                return(false);
            }
            if(GetBlock(current.xcord()+1, current.ycord()).Occupied){
                return(false);
            }
        }
       return(true);
    }

    public static boolean CheckLeft(int Player){
            System.out.println("CHECK LEFT");
        Block[] Blocks = Main.P1Blocks;
            if(Player == 1){
                Blocks = Main.P1Blocks;
            }
        if(Player == 2){
           Blocks = Main.P2Blocks;
        }

        for(int i = 0; i < 4; i++){
            Block current = Blocks[i];
            System.out.println((current.xcord() + "AHHHHHH"));
            if(current.xcord() < 1){
                return(false);
            }
            if(GetBlock(current.xcord()-1, current.ycord()).Occupied){
                return(false);
            }
        }
        return(true);
    }

    public static void MoveRight(int Player){
            if(Player == 1){
                for(int i = 0; i < Main.P1Blocks.length; i++) {
                    Main.P1Blocks[i].xchange(Main.P1Blocks[i].xcord()+1);
                }
            }
        if(Player == 2){
            for(int i = 0; i < Main.P2Blocks.length; i++) {
                Main.P2Blocks[i].xchange(Main.P2Blocks[i].xcord()+1);
            }
        }

    }

    public static void MoveLeft(int Player){
        if(Player == 1){
            for(int i = 0; i < Main.P1Blocks.length; i++) {
                Main.P1Blocks[i].xchange(Main.P1Blocks[i].xcord()-1);
            }
        }
        if(Player == 2){
            for(int i = 0; i < Main.P2Blocks.length; i++) {
                Main.P2Blocks[i].xchange(Main.P2Blocks[i].xcord()-1);
            }
        }
    }


    //returns a true if there if the space is openGetBlock
    public static boolean CheckDown(Block[] LiveBlocks) {
        for (int i = 0; i < LiveBlocks.length; i++) {
            Block current = LiveBlocks[i];
            if((current.ycord() + 1) == 20  ){
                return (false);
            }
            if (GetBlock(current.xcord(), (current.ycord() + 1)).Occupied == true) {
                return (false);
            }


        }
        return(true);
    }

    public static void SetBlocks(int Player){
            if(Player == 1){
                for(int i = 0; i < Main.P1Blocks.length; i++){
                    Yarray[Main.P1Blocks[i].ycord()][ Main.P1Blocks[i].xcord()] = Main.P1Blocks[i];
                }
            }
        if(Player == 2){
            for(int i = 0; i < Main.P2Blocks.length; i++){
                Yarray[Main.P2Blocks[i].ycord()][ Main.P2Blocks[i].xcord()] = Main.P2Blocks[i];
            }
        }
    }

    public static void GridChecker(){
        for(int i = 0; i < Yarray.length; i++) {
            for (int x = 0; x < Xarray.length; x++) {
                System.out.print(Yarray[i][x].Occupied + " ( " + Yarray[i][x] + " ) " + Yarray[i][x].Color + " ,");
            }
            System.out.println("New");
        }
    }

    public static void SavedChecker(){
        for(int i = 0; i < SavedY.length; i++) {
            for (int x = 0; x < 6; x++) {
                System.out.print(SavedY[i][x].Occupied + " ( " + SavedY[i][x] + " ) " + SavedY[i][x].Color + " ,");
            }
            System.out.println("New");
        }
    }



}

