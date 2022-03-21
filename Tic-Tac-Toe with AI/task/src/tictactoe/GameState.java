package tictactoe;

public enum GameState {
    GAME_NOT_FINISHED, DRAW, XWINS, OWINS, QUIT;


    public static GameState getState(char state) {
        if (state == 'X') return XWINS;
        if (state == 'O') return OWINS;
        else return GAME_NOT_FINISHED;
    }
}
