package pstate;

import java.util.Scanner;

public class MainMenu implements Menu {
    @Override
    public Menu run() {
        System.out.println("--- Main Menu ---");
        System.out.println("1: Publisher menu");
        System.out.println("2. Book menu");
        System.out.println("3. Exit");
        System.out.println("-----------------");

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        if (choice.equals("1")) {
            return new PublisherMenu();
        } else if (choice.equals("2")){
            return new BookMenu();
        } else {
            return null;
        }
    }
}
