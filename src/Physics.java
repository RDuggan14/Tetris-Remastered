import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Physics {

    public static Grid[] GridLines = {new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),new Grid(),};
    public static int[] Xpixels = {20,40,60,80,100,120,140,160,180,200};
    public static int[] Ypixels = {0,20,40,60,80,100,120,140,160,180,200,220,240,260,280,300,320,340,360,380,400};
    private static int height = 20;
    private static int width = 10;
    private static Block[] Xarray = {new Block(),new Block(),new Block(),new Block(),new Block(),new Block(),new Block(),new Block(),new Block(),new Block(),};
    private static Block[][] Yarray = {Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray,Xarray};



    public static void NewField(){

        for(int i = 0; i < 10;i++){
            System.out.println((Xpixels[i]));
            GridLines[i] = new Grid(Xpixels[i], Ypixels[1], Ypixels[20]);
        }
        System.out.println(Arrays.toString(GridLines));
        System.out.println(Arrays.toString(Yarray[1]));

    }


    public static void NewBlocks(){
        //Random random = new Random();
        //int rand = random.nextInt(7);
        Main.SetLiveBlocks(Fourlong());

    }





    public static void MoveDown(Block[] LiveBlocks){
        for(int i = 0; i < LiveBlocks.length; i++){
            LiveBlocks[i].ychange((LiveBlocks[i].ycord()+1));
        }
    }

    public static Block[] Fourlong(){
        Block[] Blocks = {new Block(5, 1), new Block(6,1),new Block(7,1),new Block(8,1)};
        return(Blocks);
    }


   /* public boolean CheckDown(Block[] LiveBlocks){
        for(int i = 0; i < LiveBlocks.length; i++){
            //make check at block Under
            //Think about retruning NULL
        }
        return();
   */ }







