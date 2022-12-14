// Naor Ben Azra - 318544939 && Osher Ben Hamo - 209264076

import java.util.ArrayList;
import java.util.Random;

public abstract class Game {
    private char[][] GameBoard;
    private char turn;
    private boolean done = false;


    public Game() { // Constructor that randomize the first turn and creates a new empty board
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

    // Returns the status of the game(still playing / finished)
    public boolean getStatus() {
        return done;
    }

    // Finishes the game with a message saying whos the winner or draw depends on the parameter the function gets
    public void gameOver(char winner) {
        done = true;
        if (winner == 'F') {
            System.out.println("Its a draw, Board is full!");
        } else {
            System.out.println(winner + " is the winner!");
        }
        
    }

    // Sets the player sign(X/O) on the board and check if he wins
    public synchronized void playTurn(int i, int j, char playertype) {
        GameBoard[i][j] = playertype;
        printBoard();
        checkWinner(i, j, playertype);
    }

    // Check all the wining variations available
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
    
    // Prints the board
    public void printBoard() {
        System.out.println("====== " + turn + " Played =====\n");
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
        System.out.println("=====================\n");
    }

    public synchronized void checkTurn(SelfPlayer p) {
        while (this.getTurn() != p.getPlayerType()) { // Checks if its the players turn, if not sets his thread to wait
            try {
                wait();
            } catch (Exception e) {
            }
        }
        // Plays its turn
        if (!this.getStatus()) { 
            Random rand = new Random();
            int cellIndex = rand.nextInt((this.getFreeCells().size()) - 0) + 0;
            Cell temp = this.getFreeCells().get(cellIndex);
            this.playTurn(temp.getI(), temp.getJ(), p.getPlayerType());
            if (this.getFreeCells().size() == 0 && !this.getStatus()) {
                this.gameOver('F');
            } else {
                this.flipTurn(); // Flips the turn attribute and notifying the other player's thread to wake up
                notify();
            }
        }
    }

    // Flips the turn
    public synchronized void flipTurn() { 
        if (turn == 'X') {
            turn = 'O';
        } else {
            turn = 'X';
        }
    }

    // Returns the current turn
    public char getTurn() {
        return turn;
    }

    // Returns the free cells
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

    // Prints the free cells
    public void printFreeCells(){
        ArrayList<Cell> temp = getFreeCells();
        for (int i = 0; i < temp.size(); i++) {
            System.out.print("");
        }
    }
}