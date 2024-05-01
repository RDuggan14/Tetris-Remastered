import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static Block[] dots = {new Block(120, 150), new Block(310, 240)};
    public static DotWindow g = new DotWindow();

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println(dots.length);
        System.out.println(dots[1]);
        System.out.println(dots[0]);
        System.out.println(dots[1].xcord());
        System.out.println("Hello and welcome!");


       System.out.println(g);
       DotWindow.main();


        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }

    public static DotWindow getWindow(){
        return(g);
    }


    }











