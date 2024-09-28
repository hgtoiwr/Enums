import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class WindowSAPI {

    ArrayList<Integer> listapi = new ArrayList<>();

    public void WindowSAPI() {
        this.listapi = listapi;
    }

    public void windowCINTS() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите числа. Введите -1 для завершения ввода.");

        while (true) {
            int input = scanner.nextInt();
            if (input == -1) {
                windowSortAPI();
                break;
            }
            listapi.add(input);
        }
    }

    public void windowSortAPI() {
        Integer windowS = listapi
                .stream()
                .reduce(0, (a, b) -> {
                    if (a > b) {
                        return a;
                    } else {
                        return b;
                    }
                });

        System.out.println("Наибольшее число: " + windowS);
    }
}
