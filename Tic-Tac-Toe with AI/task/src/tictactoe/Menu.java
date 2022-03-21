package tictactoe;

import java.util.Scanner;

public class Menu{
    static Scanner scanner = new Scanner(System.in);

    private static String P1 = "";
    private static String P2 = "";

    public static void setP1(String in) {
        P1 = in;
    }
    public static void setP2(String in) {
        P2 = in;
    }

    public static String getP1() {
        return P1;
    }

    public static String getP2() {
        return P2;
    }

    public static String[] getStartingInput() {
        while (true) {
            try {
                System.out.print("Input command: ");
                String[] input = scanner.nextLine().split(" ");
                if (input[0].equals("exit")) System.exit(0);
                GameRules.checkStartingInput(input);
                return new String[]{input[1], input[2]};
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
