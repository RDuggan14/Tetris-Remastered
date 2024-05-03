import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Physics {

    public static Grid[] GridLines = {new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid(), new Grid()};
    public static Grid[] yGridlines = {new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid()};
    public static int[] Xpixels = {200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420};
    public static int[] Ypixels = {0, 20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400};
    private static int height = 20;
    private static int width = 11;
    public static Block[] Xarray = {new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block(), new Block()};
    public static Block[][] Yarray = new Block[20][];


    public static void NewField() {

        for(int y = 0; y < 20;y++) {
            Block[] Xarray = new Block[10];
            for (int i = 0; i < 10; i++) {
                Xarray[i] = new Block();
            }
            Yarray[y] = Xarray;
        }

        for (int i = 0; i < Xpixels.length; i++) {
            System.out.println((Xpixels[i]));
            GridLines[i] = new Grid(Xpixels[i], Xpixels[i], Ypixels[1], Ypixels[20]);
        }
        for (int i = 0; i < Ypixels.length;i++){
            yGridlines[i] = new Grid(Xpixels[0], Xpixels[11], Ypixels[i], Ypixels[i]);
        }

    }


    public static void NewBlocks() {
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
        Block[] Blocks = {new Block(5, 1, "BLUE"), new Block(6, 1, "BLUE"), new Block(7, 1, "BLUE"), new Block(8, 1,"BLUE")};
        return (Blocks);
    }

    public static Block GetBlock(int x, int y) {
        Block current = Yarray[y][x];
        return (current);
    }


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

    public static void MoveRight(Block[] LiveBlocks){
        for(int i = 0; i < LiveBlocks.length; i++) {
            Main.LiveBlocks[i].xchange(LiveBlocks[i].xcord()+1);
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
                System.out.print(Yarray[i][x].Occupied + " ( " + Yarray[i][x] + " ),");
            }
            System.out.println("New");
        }
    }



}

