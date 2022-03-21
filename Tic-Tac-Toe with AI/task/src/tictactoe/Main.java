package tictactoe;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Game.initializeBoard(board);
        Game.playGame(board);
    }
}
