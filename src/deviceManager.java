public class deviceManager<T extends Enum<T>, U extends Enum<U>> {
    private T operatingSystem;
    private U device;

    public deviceManager(T operatingSystem, U device) {
        this.operatingSystem = operatingSystem;
        this.device = device;
    }

    public T getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(T operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public U getDevice() {
        return device;
    }

    public void setDevice(U device) {
        this.device = device;
    }
}