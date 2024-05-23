import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Physics {


    public static int[] Xpixels = {200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420};
    public static int[] Ypixels = {0, 20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400};
    public static Block[] Xarray = {new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block()};
    public static Block[][] Yarray = new Block[20][];
    private static int CurrentBlock;
    private  static int RotatePOS;
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
            Block[] Xarray = new Block[10];
            for (int i = 0; i < 10; i++) {
                Xarray[i] = new Block();
            }
            Yarray[y] = Xarray;
        }


    }

    public static void rotate(){
            Block[] NewCords = {new Block(), new Block(), new Block(), new Block()};
            Block Updated;
            if(CurrentBlock == 1){
                if(RotatePOS == 0) {
                    Block[] Pattern = {new Block(0,1), new Block(0,2), new Block(0,3), new Block(0,4)};
                    rotationPattern(Pattern);
                    RotatePOS++;

                }
                else if(RotatePOS == 1) {
                    Block[] Pattern = {new Block(0,0), new Block(1,0), new Block(2,0), new Block(3,0)};
                    rotationPattern(Pattern);
                    RotatePOS = 0;

                }
            }
        if(CurrentBlock == 2){
            if(RotatePOS == 0) {
                Block[] Pattern = {new Block(0,1), new Block(0,-1), new Block(0,0), new Block(1,0)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 1) {
                Block[] Pattern = {new Block(1,0), new Block(-1,0), new Block(0,0), new Block(0,1)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 2) {
                Block[] Pattern = {new Block(0,1), new Block(0,-1), new Block(0,0), new Block(-1,0)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 3) {
                Block[] Pattern = {new Block(1,0), new Block(-1,0), new Block(0,0), new Block(0,-1)};
                rotationPattern(Pattern);
                RotatePOS = 0;

            }

        }
        if(CurrentBlock == 3){
            if(RotatePOS == 0) {
                Block[] Pattern = {new Block(0,0), new Block(1,1), new Block(1,0), new Block(0,-1)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 1) {
                Block[] Pattern = {new Block(1,-1), new Block(0,-1), new Block(0,0), new Block(-1,0)};
                rotationPattern(Pattern);
                RotatePOS = 0;

            }

        }
        if(CurrentBlock == 4){
            if(RotatePOS == 0) {
                Block[] Pattern = {new Block(0,0), new Block(-1,1), new Block(-1,0), new Block(0,-1)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 1) {
                Block[] Pattern = {new Block(0,0), new Block(-1,0), new Block(0,1), new Block(1,1)};
                rotationPattern(Pattern);
                RotatePOS = 0;

            }

        }
        if(CurrentBlock == 6){
            if(RotatePOS == 0) {
                Block[] Pattern = {new Block(0,0), new Block(0,1), new Block(0,-1), new Block(1,1)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 1) {
                Block[] Pattern = {new Block(0,0), new Block(1,0), new Block(-1,0), new Block(-1,1)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 2) {
                Block[] Pattern = {new Block(0,0), new Block(0,1), new Block(0,-1), new Block(-1,-1)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 3) {
                Block[] Pattern = {new Block(0,0), new Block(1,0), new Block(-1,0), new Block(1,-1)};
                rotationPattern(Pattern);
                RotatePOS = 0;

            }

        }
        if(CurrentBlock == 7){
            if(RotatePOS == 0) {
                Block[] Pattern = {new Block(0,0), new Block(0,1), new Block(0,-1), new Block(1,-1)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 1) {
                Block[] Pattern = {new Block(0,0), new Block(-1,0), new Block(1,0), new Block(1,1)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 2) {
                Block[] Pattern = {new Block(0,0), new Block(0,1), new Block(0,-1), new Block(-1,1)};
                rotationPattern(Pattern);
                RotatePOS++;

            }
            else if(RotatePOS == 3) {
                Block[] Pattern = {new Block(0,0), new Block(-1,0), new Block(1,0), new Block(-1,-1)};
                rotationPattern(Pattern);
                RotatePOS = 0;

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
                System.out.println("NEW LINE " + Arrays.toString(Yarray[0]));
                if(ClearLines[i] != 99){
                    totalLines++;
                    for(int y = 0; y < ClearLines[i];y++){
                        int r = ClearLines[i] - y;
                        for(int z = 0; z < Yarray[i].length; z++){
                            if(!(r-1 < 0)) {
                                Yarray[r][z].Color = Yarray[r - 1][z].getColor();
                                Yarray[r][z].Occupied = Yarray[r - 1][z].Occupied;
                            }
                        }
//                        int r = ClearLines[i] - y;
//                        int chang = r - 1;
//                        Block[] TempARRAY = Yarray[chang];
//                        Yarray[r] = TempARRAY;
//                        System.out.println("LineChange " + Arrays.toString(Yarray[r]) + "to " + Arrays.toString(TempARRAY));

                    }

                }
                Window.ChangeScore(100 * totalLines * Window.Level);

            }
    Window.ChangeAnimation(false);
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
                    Queue[i][1][3].UpdateBlock(true, Color.CYAN);
                }
                if (number == 6) {
                    Queue[i][2][1].UpdateBlock(true, Color.BLUE);
                    Queue[i][2][2].UpdateBlock(true, Color.BLUE);
                    Queue[i][2][3].UpdateBlock(true, Color.BLUE);
                    Queue[i][1][1].UpdateBlock(true, Color.BLUE);
                }


            }
    }


    public static void SaveBlock() {
            if(TSLC == 0) {
                RotatePOS = 0;
                TSLC = 1;
                int NewSave = CurrentBlock;
                CreateSavedBlock();
                if (NewSave == 1) {
                    SavedY[2][1].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][3].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][4].UpdateBlock(true, Main.LiveBlocks[0].Color);
                }
                if (NewSave == 3) {
                    SavedY[1][3].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[1][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][1].UpdateBlock(true, Main.LiveBlocks[0].Color);
                }
                if (NewSave == 4) {
                    SavedY[1][1].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[1][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][3].UpdateBlock(true, Main.LiveBlocks[0].Color);
                }
                if (NewSave == 2) {
                    SavedY[2][1].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][3].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[1][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                }
                if (NewSave == 5) {
                    SavedY[1][1].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[1][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][1].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                }
                if (NewSave == 6) {
                    SavedY[2][1].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][3].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[1][3].UpdateBlock(true, Main.LiveBlocks[0].Color);
                }
                if (NewSave == 7) {
                    SavedY[2][1].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][2].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[2][3].UpdateBlock(true, Main.LiveBlocks[0].Color);
                    SavedY[1][1].UpdateBlock(true, Main.LiveBlocks[0].Color);
                }

                if (SavedID != 0) {

                    if (SavedID == 1) {
                        Main.SetLiveBlocks(Fourlong());
                    }
                    if (SavedID == 3) {
                        Main.SetLiveBlocks(ZigRight());
                    }
                    if (SavedID == 4) {
                        Main.SetLiveBlocks(ZigLeft());
                    }
                    if (SavedID == 2) {
                        Main.SetLiveBlocks(TPeice());
                    }
                    if (SavedID == 5) {
                        Main.SetLiveBlocks(Square());
                    }
                    if (SavedID == 6) {
                        Main.SetLiveBlocks(LRight());
                    }
                    if (SavedID == 7) {
                        Main.SetLiveBlocks(LLeft());
                    }
                    SavedID = NewSave;
                } else {
                    SavedID = CurrentBlock;
                    NewBlocks();
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


    public static void rotationPattern(Block[] BlockPattern){
            for(var i = 0; i < 4; i++){
                Block[] CheckerList = {new Block(),new Block(),new Block(),new Block(),};
                for(var x = 0; x < 4; x++){
                    int xdift = BlockPattern[x].xcord();
                    int ydift = BlockPattern[x].ycord();

                    CheckerList[x] = new Block(Main.LiveBlocks[i].xcord()+xdift, Main.LiveBlocks[i].ycord()+ydift, Main.LiveBlocks[x].getColor());
                    System.out.println("CORDS " + CheckerList[x].xcord() + " " + CheckerList[x].ycord());
                }

                if(CordChecker(CheckerList)){
                    Main.LiveBlocks = CheckerList;
                    return;
                }
            }
        for(var i = 0; i < 4; i++){
            Block[] CheckerList = {new Block(),new Block(),new Block(),new Block(),};
            for(var x = 0; x < 4; x++){
                int xdift = -BlockPattern[x].xcord();
                int ydift = -BlockPattern[x].ycord();

                CheckerList[x] = new Block(Main.LiveBlocks[i].xcord()+xdift, Main.LiveBlocks[i].ycord()+ydift, Main.LiveBlocks[x].getColor());
                System.out.println("CORDS " + CheckerList[x].xcord() + " " + CheckerList[x].ycord());
            }

            if(CordChecker(CheckerList)){
                Main.LiveBlocks = CheckerList;
                return;
            }
        }

    }

    public static void SpeedDrop(){
        if (Physics.CheckDown(Main.LiveBlocks)) {
            Physics.MoveDown(Main.LiveBlocks);
            Window.ChangeScore((Window.Level * 2));
        }
    }

    public static void NewBlocks() {
        TSLC = 0;
            RotatePOS = 0;

        int randomNumber = BlockQueue[0];

        BlockQueue[0] = BlockQueue[1];
        BlockQueue[1] = BlockQueue[2];
        BlockQueue[2] = generateRandomNumber();


        if(randomNumber == 0){
            Main.SetLiveBlocks(Fourlong());
        }
        if(randomNumber == 1){
            Main.SetLiveBlocks(ZigRight());
        }
        if(randomNumber == 2){
            Main.SetLiveBlocks(ZigLeft());
        }
        if(randomNumber == 3){
            Main.SetLiveBlocks(TPeice());
        }
        if(randomNumber == 4){
            Main.SetLiveBlocks(Square());
        }
        if(randomNumber == 5){
            Main.SetLiveBlocks(LRight());
        }
        if(randomNumber == 6){
            Main.SetLiveBlocks(LLeft());
        }



        //Random random = new Random();
        //int rand = random.nextInt(7);
        //Main.SetLiveBlocks(LRight());

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




    public static void HardDrop() {
        Block[] TempChords = Main.LiveBlocks;
        if (!CheckDown(TempChords)) {
            Window.BlockDropUpdater();

        } else {
            int totalDrop = 0;
            while (CheckDown(TempChords)) {
                for (int i = 0; i < TempChords.length; i++) {
                    TempChords[i].ychange(TempChords[i].ycord() + 1);
                }
                totalDrop++;
            }
            Main.LiveBlocks = TempChords;
            Window.BlockDropUpdater();
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


    //public static boolean ChangeLimit(){
     //       if(TSLC < tick)
    //}

    public static boolean CheckRight(Block[] Blocks){
        for(int i = 0; i < Blocks.length; i++){
            Block current = Blocks[i];
            if(current.xcord()+1 < 0){
                return(false);
            }
            if(GetBlock(current.xcord()+1, current.ycord()).Occupied){
                return(false);
            }
        }
       return(true);
    }

    public static boolean CheckLeft(Block[] Blocks){
        for(int i = 0; i < Blocks.length; i++){
            Block current = Blocks[i];
            if(current.xcord()-1 < 0){
                return(false);
            }
            if(GetBlock(current.xcord()-1, current.ycord()).Occupied){
                return(false);
            }
        }
        return(true);
    }

    public static void MoveRight(Block[] LiveBlocks){
        for(int i = 0; i < LiveBlocks.length; i++) {
            Main.LiveBlocks[i].xchange(LiveBlocks[i].xcord()+1);
        }
    }

    public static void MoveLeft(Block[] LiveBlocks){
        for(int i = 0; i < LiveBlocks.length; i++) {
            Main.LiveBlocks[i].xchange(LiveBlocks[i].xcord()-1);
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

    public static void SetBlocks(){
        for(int i = 0; i < Main.LiveBlocks.length; i++){
            Yarray[Main.LiveBlocks[i].ycord()][ Main.LiveBlocks[i].xcord()] = Main.LiveBlocks[i];
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

