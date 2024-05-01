import java.util.Random;
import java.util.random.RandomGenerator;

public class Physics {

    private int height = 15;
    private int width = 10;
    private Block[][] Yarray = {};
    private Block[] Xarray = {};

    public void NewField(){

        for(int i = 0; i < width;i++){
            Xarray[i] = new Block();
        }
         for(int i = 0; i < height;i++){
             Yarray[i] = Xarray;
         }

    }


    public void NewBlocks(){
        int choice = RandomGenerator;


    }


    public void MoveDown(Block[] LiveBlocks){
        for(int i = 0; i < LiveBlocks.length; i++){
            LiveBlocks[i].ychange((LiveBlocks[i].ycord()+1));
        }
    }

   /* public boolean CheckDown(Block[] LiveBlocks){
        for(int i = 0; i < LiveBlocks.length; i++){
            //make check at block Under
            //Think about retruning NULL
        }
        return();
   */ }







