public class Block {
    private int xcord;
    private int ycord;
    public boolean Occupied;
    public String Color;
    //Constructor to create the block
    public Block(int xcord, int ycord, String Color){
        this.Occupied = true;
        this.xcord = xcord;
        this.ycord = ycord;
        this.Color = Color;
    }

    public Block(){
        this.Occupied = false;
    }

    public int xcord(){
        return(this.xcord);
    }
    public int ycord(){
        return(this.ycord);
    }


    public void UpdateBlock(boolean Occupied, String Color){
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
