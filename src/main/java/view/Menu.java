package view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu {
    protected static Scanner scanner;
    protected int nextMenu;
    public int run(Scanner scanner) {
        Menu.scanner = scanner;
        String command;
        while (true) {
            command = scanner.nextLine();
            if (commands(command) || gameOver())
                break;
        }
        return nextMenu;
    }

    protected Matcher getMatcher(String regex, String command) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        matcher.find();
        return matcher;
    }
    protected static int getCommandNumber(String input, String[] commands) {
        for (int i = 0; i < commands.length; i++)
            if (Pattern.compile(commands[i]).matcher(input).matches())
                return i;
        return -1;
    }

    protected static String[] regexes;
    protected static String[] fieldRegexes;
    protected int command_number;
    abstract protected boolean commands(String command);
    protected boolean gameOver()
    {
        return false;
    }
}
