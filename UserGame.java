import java.util.ArrayList;
import java.util.Scanner;

public class UserGame extends Game {
    Scanner sc = new Scanner(System.in);
    SelfPlayer pc;
    UserPlayer user;

    public UserGame(Player one, Player two) {
        super();
        pc = (SelfPlayer)one;
        user = (UserPlayer)two;
    }

    public synchronized void checkTurn(UserPlayer p) {
        while (this.getTurn() != p.getPlayerType()) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        if (!this.getStatus()) {
            System.out.println("Please choose a coordinates from below [FORMAT: I -> space/enter -> J]: ");
            System.out.println(this.getFreeCells().toString());
            Cell temp = new Cell(0, 0);
            temp.setI(sc.nextInt());
            temp.setJ(sc.nextInt());
            while(!checkCell(temp)){
                System.out.println("Please choose a coordinates from below [FORMAT: I -> space/enter -> J]: ");
                System.out.println(this.getFreeCells().toString());
                temp.setI(sc.nextInt());
                temp.setJ(sc.nextInt());
            }
            System.out.println();
            this.playTurn(temp.getI(), temp.getJ(), p.getPlayerType());
            if (this.getFreeCells().size() == 0 && !this.getStatus()) {
                this.gameOver('F');
            } else {
                this.flipTurn();
                notify();
            }
        }
    }
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