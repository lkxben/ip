import java.util.Scanner;

public class Ui {
    private static final String line = "    _______________________________________\n";

    public Ui() {
        showStartupMessage();
    }

    public void showStartupMessage() {
        System.out.println(line
                + "    Hello! I'm Cate\n"
                + "    What can I do for you?\n"
                + line);
    }

    public void showLine() {
        System.out.print(line);
    }

    public void showMessage(String message) {
        System.out.println(line + "    " + message + "\n" + line);
    }

    public void showBye() {
        System.out.print(line + "    Bye. Hope to see you again soon!\n" + line);
    }

    public String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }
}
