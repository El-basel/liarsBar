public class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[1;31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[1;38;5;220m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[1;36m";
    public static final String WHITE = "\u001B[37m";
    public static final String ORANGE = "\u001B[38;5;208m";
    public static final String LIGHT_BLUE = "\u001B[38;5;39m";

    public static void print(String text, String color) {
        System.out.print(color + text + RESET);
    }

    public static void println(String text, String color) {
        System.out.println(color + text + RESET);
    }
}
