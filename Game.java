import java.util.ArrayList;

public abstract class Game {
    private char[][] GameBoard;
    private Player turn;
    private boolean done = false;

    public Game() {
        GameBoard = new char[3][3];
        for (int i = 0; i < GameBoard.length; i++) {
            for (int j = 0; j < GameBoard.length; j++) {
                GameBoard[i][j] = 'N';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < GameBoard.length; i++) {
            for (int j = 0; j < GameBoard.length; j++) {
                if (j % 3 == 0) {
                    System.out.println();
                }
                System.out.print(GameBoard[i][j] + "  |  ");
            }
        }
        System.out.println();
    }

    public Player getTurn() {
        return turn;
    }

    public ArrayList<Cell> getFreeCells(){
        ArrayList<Cell> freeCells = new ArrayList<Cell>;
        for (int i = 0; i < GameBoard.length; i++) {
            for (int j = 0; j < GameBoard.length; j++) {
                if (GameBoard[i][j] == 'N'){
                    Cell temp = new Cell(i,j);
                    freeCells.add(temp);
                }
                }
    }
    return freeCells;
}
}