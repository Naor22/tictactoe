
public class SelfPlayer extends Player {
    SelfGame s;

    public SelfPlayer(char playerType) { // Constructor
        super(playerType);

    }

    public void run() { 
        while (!Runner.game.getStatus()) { // Runs as long as the game isnt finished
            try {
                Thread.sleep(500); // Sleeps for 500 milisecs to simulate a thinking time 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Runner.game.getFreeCells().size() > 0) { // checks if there are any free cells if so runs the checkTurn function
                Runner.game.checkTurn(this);
            } else {
                Runner.game.gameOver('F'); // if there are no free cells finishes the game 
            }
        }
    }
}
