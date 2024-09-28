import java.util.ArrayList;

public record TestRecord(int x, double y) {

    public TestRecord {
        System.out.println(x + " " + y);
    }

    public TestRecord(int x) {
        this(x, 7.5);
    }

}
