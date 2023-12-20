import ui.AppMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppMenu menu = new AppMenu();

        menu.start(scanner);


    }
}
