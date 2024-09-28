public class LinuxComp implements Comparable<LinuxComp> {

    public int popular;
    public String name;

    public LinuxComp(int popular, String name) {
        this.popular = popular;
        this.name = name;
    }

    public int getPopular() {
        return popular;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(LinuxComp o) {
        return this.popular - o.popular;
    }

    @Override
    public String toString() {
        return this.popular + ". " + this.name;
    }
}
