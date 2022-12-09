import java.util.ArrayList;
import java.util.Random;

public abstract class Game {
    private char[][] GameBoard;
    private char turn;
    private boolean done = false;

    public Game() {
        Random rand = new Random();
        int rndplayer = rand.nextInt(3 - 1) + 1;
        switch (rndplayer) {
            case 1:
                turn = 'X';
                break;
            case 2:
                turn = 'O';
        }
        GameBoard = new char[3][3];
        for (int i = 0; i < GameBoard.length; i++) {
            for (int j = 0; j < GameBoard.length; j++) {
                GameBoard[i][j] = ' ';
            }
        }
        printBoard();
    }

    public boolean getStatus() {
        return done;
    }

    public void gameOver(char winner) {
        done = true;
        if (winner == 'F') {
            System.out.println("Its a draw, Board is full!");
        } else {
            System.out.println(winner + " is the winner!");
        }
        
    }

    public synchronized void playTurn(int i, int j, char playertype) {
        GameBoard[i][j] = playertype;
        printBoard();
        checkWinner(i, j, playertype);
    }

    public void checkWinner(int i, int j, char player) {
        if (GameBoard[i][0] == player
                && GameBoard[i][1] == player
                && GameBoard[i][2] == player
                || GameBoard[0][j] == player
                        && GameBoard[1][j] == player
                        && GameBoard[2][j] == player
                || i == j
                        && GameBoard[0][0] == player
                        && GameBoard[1][1] == player
                        && GameBoard[2][2] == player
                || i + j == 2
                        && GameBoard[0][2] == player
                        && GameBoard[1][1] == player
                        && GameBoard[2][0] == player) {
            gameOver(player);
        }
    }

    public void printBoard() {
        for (int i = 0; i < GameBoard.length; i++) {
            for (int j = 0; j < GameBoard.length; j++) {
                if (j % 2 == 0 && j != 0) {
                    System.out.print("[ " + GameBoard[i][j] + " ]");
                    System.out.println();
                } else {
                    System.out.print("[ " + GameBoard[i][j] + " ] | ");
                }
            }
        }
        System.out.println();
    }

    public synchronized void checkTurn(SelfPlayer p) {
        while (this.getTurn() != p.getPlayerType()) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        if (!this.getStatus()) {
            Random rand = new Random();
            int cellIndex = rand.nextInt((this.getFreeCells().size()) - 0) + 0;
            Cell temp = this.getFreeCells().get(cellIndex);
            this.playTurn(temp.getI(), temp.getJ(), p.getPlayerType());
            if (this.getFreeCells().size() == 0 && !this.getStatus()) {
                this.gameOver('F');
            } else {
                this.flipTurn();
                notify();
            }
        }
    }

    public synchronized void flipTurn() {
        if (turn == 'X') {
            turn = 'O';
        } else {
            turn = 'X';
        }
    }

    public char getTurn() {
        return turn;
    }

    public ArrayList<Cell> getFreeCells() {
        ArrayList<Cell> freeCells = new ArrayList<Cell>();
        for (int i = 0; i < GameBoard.length; i++) {
            for (int j = 0; j < GameBoard.length; j++) {
                if (GameBoard[i][j] == ' ') {
                    Cell temp = new Cell(i, j);
                    freeCells.add(temp);
                }
            }
        }
        return freeCells;
    }
    public void printFreeCells(){
        ArrayList<Cell> temp = getFreeCells();
        for (int i = 0; i < temp.size(); i++) {
            System.out.print("");
        }
    }
}