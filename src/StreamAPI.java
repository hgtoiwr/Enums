import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StreamAPI {

    ArrayList <Integer> listapi = new ArrayList<>();

    public void StreamAPI() {
        this.listapi = listapi;
    }

    public void collectInts() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите числа. Введите -1 для завершения ввода.");

        while (true) {
            int input = scanner.nextInt();
            if (input == -1) {
                sorterAPI();
                break;
            }
            listapi.add(input);
        }
    }

    public void sorterAPI() {
        List<Integer> sorter = listapi
                .stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Отсортированный список чисел: " + sorter);
    }
}
