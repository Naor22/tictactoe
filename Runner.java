public class Runner {
    public static void main(String[] args) {
        char[][] GameBoard = new char[3][3];
       
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
