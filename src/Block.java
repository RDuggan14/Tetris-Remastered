public class Block {
    private int xcord;
    private int ycord;
    public boolean Occupied;
    //Constructor to create the block
    public Block(int xcord, int ycord){
        this.Occupied = true;
        this.xcord = xcord;
        this.ycord = ycord;
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


    /* @return */
    public void xchange(int xval){
        this.xcord = xval;
    }

    public void ychange(int yval){
        this.ycord = yval;
    }
}
