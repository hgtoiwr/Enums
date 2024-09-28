import java.util.Comparator;

public class SoftwareComp implements Comparator<Software> {

    @Override
    public int compare(Software o1, Software o2) {
        return o1.name.compareTo(o2.name);
    }
}
