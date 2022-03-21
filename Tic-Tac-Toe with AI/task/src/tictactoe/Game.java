package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Game extends GameRules {
    static Scanner scanner = new Scanner(System.in);

    public static void initializeBoard(Board board) {
        board.boardInit();
    }

    public static void playGame(Board board) {
        String[] options = Menu.getStartingInput();
        Menu.setP1(options[0]);
        Menu.setP2(options[1]);
        Missile missile = new Missile();
        board.printBoard();

        System.out.println();
        char turn = 'X';
        while (checkGameState(board) == GameState.GAME_NOT_FINISHED) {

            if (Menu.getP1().equals("user")) {
                movePlayer(board, turn, missile);
            } else {
                AI.determineAI(board, turn, Menu.getP1(), missile);
            }


            board.printBoard();


            switch (checkGameState(board)) {
                case XWINS:
                    System.out.println("X wins");
                    break;
                case OWINS:
                    System.out.println("O wins");
                    break;
                case DRAW:
                    System.out.println("Draw");
                    break;
                case GAME_NOT_FINISHED:
                    break;
            }

            if (checkGameState(board) != GameState.GAME_NOT_FINISHED) break;

            turn = 'O';

            if (Menu.getP2().equals("user")) {
                movePlayer(board, turn, missile);
            } else {
                AI.determineAI(board, turn, Menu.getP2(), missile);
            }

            turn = 'X';

            board.printBoard();
            switch (checkGameState(board)) {
                case XWINS:
                    System.out.println("X wins");
                    break;
                case OWINS:
                    System.out.println("O wins");
                    break;
                case DRAW:
                    System.out.println("Draw");
                    break;
                case GAME_NOT_FINISHED:
                    break;
            }
        }
        scanner.close();
    }

    public static void movePlayer(Board board, char turn, Missile missile) {
        while (true) {
            try {
                System.out.println("Enter the coordinates: \n");
                String input = scanner.nextLine();
                if(input.equals("exit")) System.exit(0);
                missile.setMissileCords(input);
                checkInputRules(board, missile.getMissileCords(), input);
                board.placeMissile(missile.getMissileCords(), turn);
                missile.setLastMissileCords(missile.getMissileCords());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
//                    .contains("Error") ? "\n" + e.getMessage() :
//                    new Exception(String.format("Error! %s. Try again: " + "\n",
//                            e.getLocalizedMessage())).getMessage());
            }
        }
    }
}
