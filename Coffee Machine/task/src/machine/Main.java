package machine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        String command;

        do {
            command = scanner.next();
            coffeeMachine.processState(command);

        } while (!command.equals("exit"));

    }
}
