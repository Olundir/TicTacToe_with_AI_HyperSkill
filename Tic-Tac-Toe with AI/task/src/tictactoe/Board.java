package tictactoe;

public class Board {
    private final char[][] gameboard = new char[3][3];

    public char[][] getGameboard() {
        return gameboard;
    }

    public void boardInit() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                    gameboard[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(gameboard[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public void placeMissile(int[] cords, char turn) {
        gameboard[cords[0]][cords[1]] = turn;
    }

    public int[] getNextFreeSpot() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameboard[i][j] == ' ') return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    public boolean checkIfContinue() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameboard[i][j] == ' ') return false;
            }
        }
        return true;
    }

    public void placeFakeMissile(int[] cordsNext) {
        gameboard[cordsNext[0]][cordsNext[1]] = '_';
    }

    public void setBoard(Board board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameboard[i][j] = board.getGameboard()[i][j];
            }
        }
    }
}
