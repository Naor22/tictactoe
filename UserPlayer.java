// Naor Ben Azra - 318544939 && Osher Ben Hamo - 209264076

public class UserPlayer extends Player{
    UserGame s;

    public UserPlayer(char playerType){ // Constructor
        super(playerType);
    }
    public void run() { 
        while (!Runner.game.getStatus()) { // Runs as long as the game isnt finished
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Runner.game.getFreeCells().size() > 0) { // Checks if there are empty cells on the board
                if(Runner.game instanceof UserGame)
                     s = (UserGame)Runner.game;
                    s.checkTurn(this); // Calls the 'UserGame' checkTurn function
            } else {
                if(!Runner.game.getStatus()) // if the game finished, calls the gameOver function
                Runner.game.gameOver('F');
            }
        }
    }
}
