import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.Delayed;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static Block[] LiveBlocks = {};
    public static boolean liveFall = false;
    public static Block[] dots = {new Block(120, 150), new Block(310, 240)};
    public static boolean pause = false;
    public static int tick = 0;

    public static void main(String[] args) throws InterruptedException {
        Window window = new Window();
        window.main(window);
    }

    public int getTick(){
        return(tick);
    }


    public static void SetLiveBlocks(Block[] Blocks){
        LiveBlocks = Blocks;
    }

    public static void Pause(){
        if(!pause){
            pause = true;
        }
        else{
            pause = false;
        }
    }



//    private void GameStart() throws InterruptedException {
//
//        while(!pause){
//
//            if (!liveFall) {
//                System.out.println("test");
//                Physics.NewBlocks();
//                System.out.printf(Arrays.toString(LiveBlocks));
//                liveFall = true;
//            }
//
//
//            tick++;
//            Thread.sleep(1000);
//            System.out.println(tick);
//            Physics.MoveDown(LiveBlocks);
//            window.update(window.getGraphics());
//        }
//    }







    }











