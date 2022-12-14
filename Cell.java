public class Cell {
    private int i;
    private int j;

    public Cell(int i, int j){ // Constructor
        this.i = i;
        this.j = j;
    }
     
    public void setI(int i){
        this.i = i;
    }
    public void setJ(int j){
        this.j = j;
    }
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }
    public String toString(){
        return "(" + i + "," + j + ")";
    }
}
