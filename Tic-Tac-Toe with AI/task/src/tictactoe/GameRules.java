package tictactoe;


public class GameRules {

    public static void checkInputRules(Board board, int[] missileCords, String input) throws Exception {

        if (input.matches("\\D+")) {
            throw new Exception("You should enter numbers!\n");
        } else if(missileCords[0] > 2 || missileCords[1] > 2) {
            throw new Exception("Coordinates should be from 1 to 3!\n");
        } else if (board.getGameboard()[missileCords[0]][missileCords[1]] == 'O' ||
                board.getGameboard()[missileCords[0]][missileCords[1]] == 'X') {
            throw new Exception("This cell is occupied! Choose another one!");
        }
    }

    public static GameState checkGameState(Board board) {
        char[][] gameboard = board.getGameboard();

        char[] state = {'X', 'O'};
        for (int k = 0; k < 2; k++) {
            // check column
            for(int j = 0; j < 3; j++){
                int count = 0;
                for (int i = 0; i < 3; i++) {
                    if (gameboard[i][j] == state[k]) count++;
                    if (count == 3) return GameState.getState(state[k]);

                }
            }
            // check row
            for(int j = 0; j < 3; j++){
                int count = 0;
                for (int i = 0; i < 3; i++) {
                    if (gameboard[j][i] == state[k]) count++;
                    if (count == 3) return GameState.getState(state[k]);
                }
            }
            // check diagonal
            int countDiagonal = 0;
            for(int j = 0; j < 3; j++){
                if (gameboard[j][j] == state[k]) countDiagonal++;
                if (countDiagonal == 3) return GameState.getState(state[k]);
            }
            // check counter diagonal
            int countCounterDiagonal = 0;
            int c = 2;
            for(int j = 0; j < 3; j++){
                if (gameboard[j][c] == state[k]) countCounterDiagonal++;
                if (countCounterDiagonal == 3) return GameState.getState(state[k]);
                c--;
            }
        }

        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameboard[i][j] != ' ') counter++;
            }
        }
        if (counter == 9) {
            return GameState.DRAW;
        };

        return GameState.GAME_NOT_FINISHED;
    }

    public static void checkStartingInput(String[] input) throws Exception{
        if (input.length != 3) throw new Exception("Bad parameters!\n");
        if (!input[0].equals("start")) throw new Exception("Bad parameters!\n");
        if (!(input[1].equals("hard") ||
                input[1].equals("medium") ||
                input[1].equals("easy") ||
                input[1].equals("user"))) throw new Exception("Bad " +
                "parameters!\n");
        if (!(input[2].equals("hard") ||
                input[2].equals("medium") ||
                input[2].equals("easy") ||
                input[2].equals("user"))) throw new Exception("Bad " +
                "parameters!\n");
    }
}
