public class Runner {
    static Game game;

    public static void main(String[] args) {
        // Player first = new SelfPlayer('X');
        // Player sec = new SelfPlayer('O');
        // game = new SelfGame(first,sec);
        Player first = new SelfPlayer('X');
        Player sec = new UserPlayer('O');
        game = new UserGame(first,sec);
        first.start();
        sec.start();
    }
}
