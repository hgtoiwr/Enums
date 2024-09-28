public class Software implements Comparable<Software> {

    public String name;
    public String type;

    public Software(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public int compareTo(Software o) {
        return this.name.compareTo(o.name);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
