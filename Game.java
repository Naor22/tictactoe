public abstract class Game {
    char[][] GameBoard;

    public Game() {
        GameBoard = new char[3][3];

    }
    public void printBoard(){
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
}