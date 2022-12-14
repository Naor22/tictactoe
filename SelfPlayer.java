
public class SelfPlayer extends Player {
    SelfGame s;

    public SelfPlayer(char playerType) {
        super(playerType);

    }

    public void run() {
        while (!Runner.game.getStatus()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Runner.game.getFreeCells().size() > 0) {
                Runner.game.checkTurn(this);
            } else {
                Runner.game.gameOver('F');
            }
        }
    }
}
