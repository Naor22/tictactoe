import java.util.Random;

public class SelfGame extends Game {
    Player pc;
    Player pc2;

    public SelfGame(Player one, Player two) {
        super();
        pc = one;
        pc2 = two;
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
            if (this.getFreeCells().size() == 0) {
                this.gameOver('F');
            } else {
                this.flipTurn();
                notify();
            }
        }
    }
}