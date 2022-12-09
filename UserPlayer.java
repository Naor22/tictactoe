public class UserPlayer extends Player{
    UserGame s;

    public UserPlayer(char playerType){
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
                if(Runner.game instanceof UserGame)
                     s = (UserGame)Runner.game;
                    s.checkTurn(this);
            } else {
                if(!Runner.game.getStatus())
                Runner.game.gameOver('F');
            }
        }
    }
}
