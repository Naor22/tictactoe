
public class SelfGame extends Game {
    SelfPlayer pc;
    SelfPlayer pc2;

    public SelfGame(Player one, Player two) { // Constructor
        super();
        pc = (SelfPlayer)one;
        pc2 = (SelfPlayer)two;
    }

 
}