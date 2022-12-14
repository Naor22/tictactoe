public abstract class Player extends Thread{
    private char playerType;

    public Player(char playerType) { // Constructor
        if(playerType != 'X' && playerType != 'O'){
            System.out.println("ERROR: Player type must be X or O");
        }
        this.playerType = playerType;
    }
    public char getPlayerType(){
        return playerType;
    }
}
