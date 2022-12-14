// Naor Ben Azra - 318544939 && Osher Ben Hamo - 209264076

import java.util.ArrayList;
import java.util.Scanner;

public class UserGame extends Game {
    Scanner sc = new Scanner(System.in);
    SelfPlayer pc;
    UserPlayer user;

    public UserGame(Player one, Player two) { // Constructor
        super();
        pc = (SelfPlayer)one;
        user = (UserPlayer)two;
    }

    public synchronized void checkTurn(UserPlayer p) { // Overides the 'Game' class CheckTurn function 
            while (this.getTurn() != p.getPlayerType()) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        if (!this.getStatus()) {
            System.out.println("Please choose a coordinates from below [FORMAT: I -> space/enter -> J]: "); // Asks the user to choose cell
            System.out.println(this.getFreeCells().toString());
            Cell temp = new Cell(0, 0);
            temp.setI(sc.nextInt());
            temp.setJ(sc.nextInt());
            while(!checkCell(temp)){ // Checks if the cell entered is empty, if not asks again.
                System.out.println("Please choose a coordinates from below [FORMAT: I -> space/enter -> J]: ");
                System.out.println(this.getFreeCells().toString());
                temp.setI(sc.nextInt());
                temp.setJ(sc.nextInt());
            }
            // Plays its turn
            System.out.println();
            this.playTurn(temp.getI(), temp.getJ(), p.getPlayerType());
            if (this.getFreeCells().size() == 0 && !this.getStatus()) {
                this.gameOver('F');
            } else {
                this.flipTurn(); // Flips the turn and notifying the other thread
                notify();
            }
        }
    }
    // Checks if the cell is empty or not
    public boolean checkCell(Cell tempCell){
        ArrayList<Cell> temp = this.getFreeCells();
        for (int i = 0; i < temp.size(); i++) {
            if(tempCell.getI() == temp.get(i).getI() && tempCell.getJ() == temp.get(i).getJ()){
                return true;
            }
        }
        return false;
    }
}