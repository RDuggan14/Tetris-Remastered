import java.awt.*;
import java.util.concurrent.Delayed;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static Block[] LiveBlocks = {};

    public static Block[] dots = {new Block(120, 150), new Block(310, 240)};
    public static boolean pause = false;
    private static int tick = 0;

    public static void main(String[] args) throws InterruptedException {
        Window.main();
        GameStart();


    }

    public int getTick(){
        return(tick);
    }


    public static void Pause(){
        if(!pause){
            pause = true;
        }
        else{
            pause = false;
        }
    }


    private static void GameStart() throws InterruptedException {

        while(!pause){

            tick++;
            Thread.sleep(1000);
            System.out.println(tick);
        }
    }
    }











