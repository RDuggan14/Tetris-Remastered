import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Physics {

    public static Grid[] GridLines = {new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid()};
    public static Grid[] yGridlines = {new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid()};
    public static int[] Xpixels = {200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420};
    public static int[] Ypixels = {0, 20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400};
    public static Block[] Xarray = {new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block()};
    public static Block[][] Yarray = new Block[20][];
    private static int CurrentBlock;
    private  static int RotatePOS;
    private static int TSLC;
    //INT TO BLOCK
//    1 - 4Long
//    2 - TPiece
//    3 - ZigRight
//    4 - ZigLeft
//    5 - Sqaure
//    6 - LRifht
//    7 - LLeft

        public static void NewField() {

        for(int y = 0; y < 20;y++) {
            Block[] Xarray = new Block[10];
            for (int i = 0; i < 10; i++) {
                Xarray[i] = new Block();
            }
            Yarray[y] = Xarray;
        }

        for (int i = 0; i < Xpixels.length-1; i++) {
            System.out.println((Xpixels[i]));
            GridLines[i] = new Grid(Xpixels[i], Xpixels[i], Ypixels[1], Ypixels[20]);
        }
        for (int i = 0; i < Ypixels.length;i++){
            yGridlines[i] = new Grid(Xpixels[0], Xpixels[10], Ypixels[i], Ypixels[i]);
        }

    }

    public static void rotate(){
            Block[] NewCords = {new Block(), new Block(), new Block(), new Block()};
            Block Updated;
            if(CurrentBlock == 1){
                if(RotatePOS == 0) {
                    Block[] Pattern = {new Block(0,1), new Block(0,2), new Block(0,3), new Block(0,4)};
                    rotationPattern(Pattern, Main.LiveBlocks);
//                    NewCords[0] = new Block(Main.LiveBlocks[2].xcord(), Main.LiveBlocks[2].ycord() + 2, Main.LiveBlocks[2].getColor());
//                    NewCords[1] = new Block(Main.LiveBlocks[2].xcord(), Main.LiveBlocks[2].ycord() + 1, Main.LiveBlocks[2].getColor());
//                    NewCords[2] = new Block(Main.LiveBlocks[2].xcord(), Main.LiveBlocks[2].ycord(), Main.LiveBlocks[2].getColor());
//                    NewCords[3] = new Block(Main.LiveBlocks[2].xcord(), Main.LiveBlocks[2].ycord() - 1, Main.LiveBlocks[2].getColor());
//
//                    if (CordChecker(NewCords)) {
//                        RotatePOS++;
//                        Main.LiveBlocks = NewCords;
//                    }
                                       RotatePOS++;

                }
                else if(RotatePOS == 1) {
                    Block[] Pattern = {new Block(0,0), new Block(1,0), new Block(2,0), new Block(3,0)};
                    rotationPattern(Pattern, Main.LiveBlocks);
//                    NewCords[0] = new Block(Main.LiveBlocks[2].xcord()-2, Main.LiveBlocks[2].ycord(), Main.LiveBlocks[2].getColor());
//                    NewCords[1] = new Block(Main.LiveBlocks[2].xcord()-1, Main.LiveBlocks[2].ycord(), Main.LiveBlocks[2].getColor());
//                    NewCords[2] = new Block(Main.LiveBlocks[2].xcord(), Main.LiveBlocks[2].ycord(), Main.LiveBlocks[2].getColor());
//                    NewCords[3] = new Block(Main.LiveBlocks[2].xcord()+1, Main.LiveBlocks[2].ycord(), Main.LiveBlocks[2].getColor());
//
//                    if (CordChecker(NewCords)) {
//                    RotatePOS = 0;
//                        Main.LiveBlocks = NewCords;
//                    }
                    RotatePOS = 0;

                }
            }
    }


    public static boolean CordChecker(Block[] Blocks){
            for(var i = 0; i < Blocks.length; i++){
                if(Yarray[Blocks[i].ycord()][Blocks[i].xcord()].Occupied){
                    return(false);
                }
            }
            return(true);
    }


    public static void rotationPattern(Block[] BlockPattern, Block[] LiveBlocks){
            Block[] CheckerList = {new Block(),new Block(),new Block(),new Block(),};
            for(var i = 0; i < Main.LiveBlocks.length; i++){
                for(var x = 0; x < BlockPattern.length; x++){
                    int xdift = BlockPattern[x].xcord();
                    int ydift = BlockPattern[x].ycord();

                    CheckerList[x] = new Block(LiveBlocks[i].xcord()+xdift, LiveBlocks[i].ycord()+ydift, LiveBlocks[x].getColor());
                }
                if(CordChecker(CheckerList)){
                    Main.LiveBlocks = CheckerList;
return;
                }
            }

    }

    public static void NewBlocks() {
            RotatePOS = 0;
        //Random random = new Random();
        //int rand = random.nextInt(7);
        Main.SetLiveBlocks(Fourlong());

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
            System.out.println(current.ycord());
            if((current.ycord() + 1) == 20  ){
                System.out.printf("out of bounds");
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
           Block Block = Yarray[Main.LiveBlocks[i].ycord()][Main.LiveBlocks[i].xcord()];
            Block.UpdateBlock(true, Main.LiveBlocks[i].Color);
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



}

