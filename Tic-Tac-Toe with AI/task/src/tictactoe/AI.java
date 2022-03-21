package tictactoe;

import java.util.Arrays;
import java.util.Random;

public class AI {

    public static void determineAI(Board board, char turn, String ai, Missile missile) {
        switch (ai) {
            case "easy":
                generateEasyMove(board, turn, missile);
                break;
            case "medium":
                generateMediumMove(board, turn, missile);
                break;
            case "hard":
                generateHardMove(board, turn, missile);
                break;
        }
    }

    private static boolean checkIfOpponentCanWin(Board board, char turn, Missile missile) {
        Board checkBoard = new Board();
        checkBoard.setBoard(board);

        char determineTurn;

        if (turn == 'X') {
            determineTurn = 'O';
        } else {
            determineTurn = 'X';
        }

        while(true) {
            int[] cordsNext = checkBoard.getNextFreeSpot();
            checkBoard.placeMissile(cordsNext, turn);
            if (GameRules.checkGameState(checkBoard) == GameState.XWINS ||
                    GameRules.checkGameState(checkBoard) ==  GameState.OWINS) {
                board.placeMissile(cordsNext, determineTurn);
                missile.setLastMissileCords(cordsNext);
                return true;
            } else {
                checkBoard.placeFakeMissile(cordsNext);
            }
            if (checkBoard.checkIfContinue()) break;
        }
        return false;
    }


    private static boolean checkIfCanWin(Board board, char turn, Missile missile) {
        Board checkBoard = new Board();
        checkBoard.setBoard(board);

        while(true) {
            int[] cordsNext = checkBoard.getNextFreeSpot();
            checkBoard.placeMissile(cordsNext, turn);
            if (GameRules.checkGameState(checkBoard) == GameState.XWINS ||
                    GameRules.checkGameState(checkBoard) ==  GameState.OWINS) {
                board.placeMissile(cordsNext, turn);
                missile.setLastMissileCords(cordsNext);
                return true;
            } else {
                checkBoard.placeFakeMissile(cordsNext);
            }
            if (checkBoard.checkIfContinue()) break;
        }
        return false;
    }

    public static void generateHardMove(Board board, char turn, Missile missile) {
        System.out.println("Making move level \"hard\"");

        int[] pos = findBestMove(board, turn);
        board.placeMissile(pos, turn);
        missile.setLastMissileCords(pos);
    }

    public static int minimax(Board board, Boolean isMax, char ai, char player) {
        int score = 0;
        if(ai == 'X') {
            switch (GameRules.checkGameState(board)) {
                case XWINS:
                    score += 10;

                    break;
                case OWINS:
                    score -= 10;
                    break;
                default:
                    break;
            }
        } else {
            switch (GameRules.checkGameState(board)) {
                case OWINS:
                    score += 10;
                    break;
                case XWINS:
                    score -= 10;
                    break;
                default:
                    break;
            }
        }
        if (score == 10) return score;
        if (score == -10) return score;
        if (!anyMovesLeft(board)) return 0;


        int best;
        if (isMax) {
            best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board.getGameboard()[i][j]==' ')
                    {
                        // Make the move
                        board.getGameboard()[i][j] = ai;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,!isMax, ai, player));

                        // Undo the move
                        board.getGameboard()[i][j] = ' ';
                    }
                }
            }
        }

        // If this minimizer's move
        else {
            best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board.getGameboard()[i][j] == ' ')
                    {
                        // Make the move
                        board.getGameboard()[i][j] = player;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board, !isMax, ai, player));

                        // Undo the move
                        board.getGameboard()[i][j] = ' ';
                    }
                }
            }
        }
        return best;
    }

    public static int[] findBestMove(Board board, char turn) {
        char ai = turn;
        char player = 0;
        if (turn == 'X') player = 'O';
        else player = 'X';

        int bestVal = -1000;
        int row = -1;
        int col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board.getGameboard()[i][j] == ' ')
                {
                    // Make the move
                    board.getGameboard()[i][j] = ai;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, false, ai, player);

                    // Undo the move
                    board.getGameboard()[i][j] = ' ';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/

                    if (moveVal > bestVal)
                    {
                        row = i;
                        col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return new int[] {row, col};
    }

    public static Boolean anyMovesLeft(Board board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board.getGameboard()[i][j] == ' ')
                    return true;
        return false;
    }

    public static void generateMediumMove(Board board, char turn, Missile missile) {
        System.out.println("Making move level \"medium\"");
        Random random = new Random();
        char determineTurn;

        if (turn == 'X') {
            determineTurn = 'O';
        } else {
            determineTurn = 'X';
        }
        if (checkIfCanWin(board, turn, missile)) return;

        if (checkIfOpponentCanWin(board, determineTurn, missile)) {
            return;
        };

        while (true) {
            int[] pos = convertToCords(random.nextInt(9 - 1 + 1) + 1);
            if (board.getGameboard()[pos[0]][pos[1]] == ' '){
                board.placeMissile(pos, turn);
                missile.setLastMissileCords(pos);
                break;
            }
        }
    }



    public static void generateEasyMove(Board board, char turn, Missile missile) {
        System.out.println("Making move level \"easy\"");

        Random random = new Random();

        while (true) {
            int[] pos = convertToCords(random.nextInt(9 - 1 + 1) + 1);
            if (board.getGameboard()[pos[0]][pos[1]] == ' '){
                board.placeMissile(pos, turn);
                missile.setLastMissileCords(pos);
                break;
            }
        }

    }

    private static int[] convertToCords(int position) {
        switch (position) {
            case 1:
                return new int[]{1, 0};
            case 2:
                return new int[]{0, 1};
            case 3:
                return new int[]{2, 1};
            case 4:
                return new int[]{1, 1};
            case 5:
                return new int[]{0, 0};
            case 6:
                return new int[]{1, 2};
            case 7:
                return new int[]{2, 0};
            case 8:
                return new int[]{0, 2};
            case 9:
                return new int[]{2, 2};
        }
        return new int[]{0, 0};
    }
}
