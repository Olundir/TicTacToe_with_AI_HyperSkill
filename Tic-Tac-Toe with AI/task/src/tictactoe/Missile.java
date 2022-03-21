package tictactoe;

public class Missile {
    private int[] missileCords;
    private int[] lastMissileCords;

    public int[] getMissileCords() {
        return missileCords;
    }

    public int[] getLastMissileCords() { return lastMissileCords; }

    // convert to the board cords that start with 0
    public void setMissileCords(String missileCords) {
        missileCords = missileCords.replaceAll(" ", "");
        this.missileCords = new int[2];
        this.missileCords[0] = (int) missileCords.charAt(0) - 49;
        this.missileCords[1] = (int) missileCords.charAt(1) - 49;
    }

    public void setLastMissileCords(int[] missileCords) {
        this.lastMissileCords = missileCords;
    }

}
