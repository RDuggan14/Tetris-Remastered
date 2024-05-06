import java.awt.*;

public class Block {
    private int xcord;
    private int ycord;
    public boolean Occupied;
    public Color Color;
    //Constructor to create the block
    public Block(int xcord, int ycord, Color Color){
        this.Occupied = true;
        this.xcord = xcord;
        this.ycord = ycord;
        this.Color = Color;
    }

    public Color getColor(){
        return(this.Color);
    }

    public Block(){
        this.Occupied = false;
        this.Color = Color.WHITE;
    }

    public int xcord(){
        return(this.xcord);
    }
    public int ycord(){
        return(this.ycord);
    }


    public void UpdateBlock(boolean Occupied, Color Color){
        this.Occupied = Occupied;
        this.Color = Color;
    }

    /* @return */
    public void xchange(int xval){
        this.xcord = xval;
    }

    public void ychange(int yval){
        this.ycord = yval;
    }
}
