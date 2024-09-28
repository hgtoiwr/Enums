import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PC extends Thread {

    public deviceManager<OC, Device> deviceManager;
    ArrayList<LinuxComp> linuxcomps = new ArrayList<>();
    ArrayList<Software> softwares = new ArrayList<>();
    File file = new File("ios.txt");
    StreamAPI streamAPI = new StreamAPI();
    AndroidSAPI androidSAPI = new AndroidSAPI();
    WindowSAPI windowSAPI = new WindowSAPI();

    public PC() {
        deviceManager = new deviceManager<>(OC.WINDOWS, Device.PC);

        LinuxComp ubuntu = new LinuxComp(1, "Ubuntu");
        LinuxComp fedora = new LinuxComp(2, "Fedora");
        LinuxComp mint = new LinuxComp(3, "LinuxMint");
        LinuxComp arch = new LinuxComp(4, "ArchLinux");
        LinuxComp debian = new LinuxComp(5, "Debian");

        linuxcomps.add(ubuntu);
        linuxcomps.add(fedora);
        linuxcomps.add(mint);
        linuxcomps.add(arch);
        linuxcomps.add(debian);

        softwares.add(new Software("VSCodium", "IDE"));
        softwares.add(new Software("FireFox", "Browser"));
        softwares.add(new Software("Webcord", "Communication"));
        softwares.add(new Software("IntelliJ IDEA", "IDE"));
        softwares.add(new Software("Postman", "API Testing"));
    }

    public void iosSave() {
        Thread iosThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    System.out.println("Exception : "+e.getMessage());
                    break;
                }

                try {
                    FileWriter fwriter = new FileWriter(file, true);
                    BufferedWriter bwriter = new BufferedWriter(fwriter);
                    bwriter.write("Ошибок не обнаружено. Система в идеальном состоянии.");
                    bwriter.newLine();
                    bwriter.close();
                } catch (IOException i) {
                    System.out.println("IOException : "+i.getMessage());
                    break;
                }

                System.out.println("System data has been saved.");
            }
        });
        iosThread.start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(30000);
            } catch(InterruptedException e) {
                System.out.println("Exception : "+e.getMessage());
                break;
            }
            System.out.println("Saving data...");
        }
    }

    public void sortLinuxComp() {
        Collections.sort(linuxcomps);
        for (LinuxComp popular:linuxcomps) {
            System.out.println(popular);
        }
    }

    public void selectDevice() {
        System.out.println("Выберите устройство:");
        System.out.println("1. PC");
        System.out.println("2. Phone");
        System.out.println("3. Tablet");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                deviceManager.setDevice(Device.PC);
                manageOperatingSystem();
                break;
            case 2:
                deviceManager.setDevice(Device.PHONE);
                manageOperatingSystem();
                break;
            case 3:
                deviceManager.setDevice(Device.TABLET);
                manageOperatingSystem();
                break;
            default:
                System.out.println("Неизвестный выбор. По умолчанию выбран PC.");
                deviceManager.setDevice(Device.PC);
                manageOperatingSystem();
        }
    }

    public void manageOperatingSystem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 'i', чтобы установить операционную систему, 'r', чтобы переустановить, или 'q' для выхода:");

        while (scanner.hasNext()) {
            char command = scanner.next().charAt(0);

            switch (command) {
                case 'i':
                    setOperatingSystem();
                    break;
                case 'r':
                    reinstallOperatingSystem();
                    break;
                case 'q':
                    System.out.println("Выключение");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }
    }

    public void setOperatingSystem() {
        if (deviceManager.getOperatingSystem() != OC.NONE) {
            System.out.println("Операционная система уже установлена, хотите переустановить? (y/n)");
            Scanner scanner = new Scanner(System.in);
            char choice = scanner.next().toLowerCase().charAt(0);
            if (choice == 'y') {
                reinstallOperatingSystem();
            } else {
                System.out.println("Установка отменена.");
            }
            return;
        }

        chooseOperatingSystem();
    }

    private void chooseOperatingSystem() {
        Scanner scanner = new Scanner(System.in);
        if (deviceManager.getDevice() == Device.PC) {
            System.out.println("Выберите операционную систему:");
            System.out.println("1. Windows");
            System.out.println("2. MacOS");
            System.out.println("3. Linux");

            int choice = scanner.nextInt();
            start();
            switch (choice) {
                case 1:
                    deviceManager.setOperatingSystem(OC.WINDOWS);
                    System.out.println("Устанавливаем Windows...");
                    System.out.println("Желаете запустить поиск наибольшего числа y/n:");
                    char choose2 = scanner.next().toLowerCase().charAt(0);
                    switch (choose2) {
                        case 'y':
                            windowSAPI.windowCINTS();
                            break;
                        default:
                            System.out.println("Выключение поиска...");
                            break;
                    }
                    break;
                case 2:
                    this.interrupt();
                    deviceManager.setOperatingSystem(OC.MACOS);
                    System.out.println("Устанавливаем MacOS...");
                    break;
                case 3:
                    chooseLinuxDistribution();
                    break;
                default:
                    System.out.println("Неизвестный выбор. Установка отменена.");
            }
        } else {
            System.out.println("Выберите операционную систему:");
            System.out.println("1. Android");
            System.out.println("2. IOS");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    deviceManager.setOperatingSystem(OC.ANDROID);
                    System.out.println("Устанавливаем Android...");
                    System.out.println("Желаете запустить поиск первого введенного вами числа y/n:");
                    char choose2 = scanner.next().toLowerCase().charAt(0);
                    switch (choose2) {
                        case 'y':
                            androidSAPI.androidCINTS();
                            break;
                        default:
                            System.out.println("Выключение поиска...");
                            break;
                    }
                    break;
                case 2:
                    deviceManager.setOperatingSystem(OC.IOS);
                    System.out.println("Устанавливаем IOS...");
                    iosSave();
                    break;
                default:
                    System.out.println("Неизвестный выбор. Установка отменена.");
            }
        }
    }

    public void chooseLinuxDistribution() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите дистрибутив Linux:");
        sortLinuxComp();

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Устанавливаем " + linuxcomps.get(0).getName() + "...");
                deviceManager.setOperatingSystem(OC.LINUX);
                chooseSoftware();
                break;
            case 2:
                System.out.println("Устанавливаем " + linuxcomps.get(1).getName() + "...");
                deviceManager.setOperatingSystem(OC.LINUX);
                chooseSoftware();
                break;
            case 3:
                System.out.println("Устанавливаем " + linuxcomps.get(2).getName() + "...");
                deviceManager.setOperatingSystem(OC.LINUX);
                chooseSoftware();
                break;
            case 4:
                System.out.println("Устанавливаем " + linuxcomps.get(3).getName() + "...");
                deviceManager.setOperatingSystem(OC.LINUX);
                chooseSoftware();
                break;
            case 5:
                System.out.println("Устанавливаем " + linuxcomps.get(4).getName() + "...");
                deviceManager.setOperatingSystem(OC.LINUX);
                chooseSoftware();
                break;
            default:
                System.out.println("Неизвестный выбор. Установка отменена.");
        }
    }

    public void chooseSoftware() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите предустановленный софт:");
        for (int i = 0; i < softwares.size(); i++) {
            Collections.sort(softwares, new SoftwareComp());
            System.out.println((i + 1) + ". " + softwares.get(i));
        }

        ArrayList<Software> selectedSoftware = new ArrayList<>();
        while (true) {
            int choice = scanner.nextInt();
            if (choice == 0) {
                selectedSoftware.addAll(softwares);
                break;
            } else if (choice > 0 && choice <= softwares.size()) {
                selectedSoftware.add(softwares.get(choice - 1));
            } else {
                System.out.println("Неизвестный выбор. Попробуйте снова.");
            }
            System.out.println("Выберите ещё или введите -1 для завершения:");
            if (choice == -1) break;
        }

        System.out.println("Вы выбрали для установки:");
        for (Software software : selectedSoftware) {
            System.out.println(software);
        }

        System.out.println("Установить y/n:");
        char choose = scanner.next().toLowerCase().charAt(0);
        if (choose == 'y') {
            System.out.println("Установка завершена!");
        } else {
            System.out.println("Установка отменена.");
        }

        System.out.println("Желаете запустить сортировщик чисел y/n:");
        char choose2 = scanner.next().toLowerCase().charAt(0);
        switch (choose2) {
            case 'y':
                streamAPI.collectInts();
                break;
            default:
                System.out.println("Выключение сортировщика...");
                break;
        }
    }

    public void reinstallOperatingSystem() {
        System.out.println("Переустанавливаем операционную систему");
        deviceManager.setOperatingSystem(OC.NONE);
        chooseOperatingSystem();
    }
}