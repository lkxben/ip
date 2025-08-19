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
        String[] list = new String[100];
        int index = 0;

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                System.out.print(line);
                printList(list);
                System.out.print(line);
            } else if (userInput.equals("bye")) {
                break;
            } else {
                System.out.println(line + "    added: " + userInput + "\n" + line);
                list[index++] = userInput;
            }
        }
        System.out.println(line + "    Bye. Hope to see you again soon! \n" + line);
    }

    private static void printList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] ==  null) return;
            System.out.println(String.format("    %d. %s", i + 1, list[i]));
        }
    }
}
