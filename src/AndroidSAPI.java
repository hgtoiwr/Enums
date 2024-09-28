import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AndroidSAPI {

    ArrayList<Integer> listapi = new ArrayList<>();

    public void AndroidSAPI() {
        this.listapi = listapi;
    }

    public void androidCINTS() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите числа. Введите -1 для завершения ввода.");

        while (true) {
            int input = scanner.nextInt();
            if (input == -1) {
                androidSortAPI();
                break;
            }
            listapi.add(input);
        }
    }

    public void androidSortAPI() {
        Optional<Integer> androidS = listapi
                .stream()
                .findFirst();

        System.out.println("Первое введенное число:");
        androidS.ifPresent(System.out::println);
    }
}
