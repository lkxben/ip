import java.util.Scanner;

public class Cate {
    public static void main(String[] args) {
        String line = "    _______________________________________ \n";
        String startup = line
                + "    Hello! I'm Cate \n"
                + "    What can I do for you? \n"
                + line;
        System.out.println(startup);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(line + "    " + userInput + "\n" + line);
        }
        System.out.println(line + "    Bye. Hope to see you again soon! \n" + line);
    }
}
