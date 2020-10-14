import java.io.*;
import java.util.*;
import java.util.jar.Manifest;

public class Main {
    public static void main(String[] args) {

        LinkedHashMap<String, FootballPlayer> juventus = new LinkedHashMap<>();
        LinkedHashMap<String, FootballPlayer> manchester = new LinkedHashMap<>();
        LinkedHashMap<String, FootballPlayer> realMadrid = new LinkedHashMap<>();

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("fifa.dat"));
            LinkedHashMap<String, LinkedHashMap<String, FootballPlayer>> list = (LinkedHashMap) input.readObject();
            for (String key : list.keySet()) {
                switch (key) {
                    case "Juventus":
                        juventus = list.get(key);
                        break;
                    case "Manchester United":
                        manchester = list.get(key);
                        break;
                    case "Real Madrid":
                        realMadrid = list.get(key);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FootBallClub juventusFC = new FootBallClub("Juventus", "Turin", 1900, 500, juventus);

        FootBallClub manUFC = new FootBallClub("Manchester United", "Old Trafford", 1888, 500, manchester);

        FootBallClub real = new FootBallClub("Real Madrid", "Santiago Bernabeu", 1890, 500, realMadrid);

        do {
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("Mời chọn tính năng: ");
            System.out.println("1. Hiển thị danh sách các cầu thủ trong hệ thống");
            System.out.println("2. Hiển thị thông tin của đội bóng");
            System.out.println("3. Sắp xếp cầu thủ theo giá trị chuyển nhượng");
            System.out.println("4. Tìm kiếm thông tin cầu thủ theo tên");
            System.out.println("5. Tìm kiếm thông tin cầu thủ theo vị trí");
            System.out.println("6. Quản lý đội bóng của bạn");
            System.out.println("0. Exit");
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.println("Nhập lựa chọn của bạn: ");

            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    displayFootballPlayerOfFifa();
                    break;
                case 2:
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("1. Juventus");
                    System.out.println("2. Manchester United");
                    System.out.println("3. Real Madrid");
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("Chọn đội bóng bạn muốn: ");
                    int num = input.nextInt();
                    input.nextLine();
                    switch (num) {
                        case 1:
                            juventusFC.displayInformationClub();
                            break;
                        case 2:
                            manUFC.displayInformationClub();
                            break;
                        case 3:
                            real.displayInformationClub();
                            break;
                        default:
                            System.out.println("Không có đội bóng này");
                    }
                    break;
                case 3:
                    displayByTransferValue();
                    break;
                case 4:
                    searchFootballPlayerByName();
                    break;
                case 5:
                    searchFootballPlayerByPos();
                    break;
                case 6:
                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
                    System.out.println("1. Juventus");
                    System.out.println("2. Manchester United");
                    System.out.println("3. Real Madrid");
                    System.out.println("0. Exit");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Chọn đội bóng muốn quản lí: ");
                    int number = input.nextInt();
                    Manager coach = null;
                    input.nextLine();
                    switch (number) {
                        case 1:
                            coach = new Manager(juventusFC);
                            break;
                        case 2:
                            coach = new Manager(manUFC);
                            break;
                        case 3:
                            coach = new Manager(real);
                            break;
                        case 0:
                            System.exit(-1);
                            break;
                    }
                    int choiceClub;
                    do {
                        System.out.println("---------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Chọn hành động bạn muốn thực hiện");
                        System.out.println("1. Mua cầu thủ cho CLB " + coach.getFootBallClub().getName());
                        System.out.println("2. Bán cầu thủ của CLB " + coach.getFootBallClub().getName());
                        System.out.println("0. Exit");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------");
                        choiceClub = input.nextInt();
                        input.nextLine();
                        String name;
                        switch (choiceClub) {
                            case 1:
                                System.out.println("Nhập vào tên cầu thủ cần mua: ");
                                name = input.nextLine();
                                coach.buyFootballPlayer(name);
                                break;
                            case 2:
                                System.out.println("Nhập vào tên cầu thủ cần bán: ");
                                name = input.nextLine();
                                coach.sellFootballPlayer(name);
                                break;
                        }
                    } while (choiceClub != 0);
                    break;
                case 0:
                    System.exit(-1);
                    break;

            }
        } while (true);

    }

    public static void displayFootballPlayerOfFifa() {
        System.out.printf("|%-25s|%-15s|%-12s|%-20s|%-20s|%-15s|", "Name", "Date of birth", "Country", "Playing position", "Football Club", "Transfer Value");
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("database.dat"));
            LinkedHashMap<String, FootballPlayer> list = (LinkedHashMap) input.readObject();
            for (String key : list.keySet()) {
                list.get(key).displayInformation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    public static void displayByTransferValue() {
        System.out.printf("|%-25s|%-15s|%-12s|%-20s|%-20s|%-15s|", "Name", "Date of birth", "Country", "Playing position", "Football Club", "Transfer Value");
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("database.dat"));
            LinkedHashMap<String, FootballPlayer> list = (LinkedHashMap) input.readObject();
            Collection<FootballPlayer> values = list.values();
            List<FootballPlayer> listOfFootballPlayer = new ArrayList<FootballPlayer>(values);
            Collections.sort(listOfFootballPlayer, new Comparator<FootballPlayer>() {
                @Override
                public int compare(FootballPlayer o1, FootballPlayer o2) {
                    return o2.getTransferValue() - o1.getTransferValue();
                }
            });
            for (int i = 0; i < list.size(); i++) {
                listOfFootballPlayer.get(i).displayInformation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchFootballPlayerByName() {
        boolean check = false;
        System.out.println("Nhập vào tên cầu thủ cần tìm: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.printf("|%-25s|%-15s|%-12s|%-20s|%-20s|%-15s|", "Name", "Date of birth", "Country", "Playing position", "Football Club", "Transfer Value");
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("database.dat"));
            LinkedHashMap<String, FootballPlayer> list = (LinkedHashMap) input.readObject();
            for (String key : list.keySet()) {
                if (name.equalsIgnoreCase(key)) {
                    check = true;
                    list.get(key).displayInformation();
                    break;
                }
            }
            if (!check) {
                System.out.println("Không tìm thấy cầu thủ này");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    public static void searchFootballPlayerByPos() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("1. Thủ môn");
        System.out.println("2. Hậu vệ");
        System.out.println("3. Tiền vệ");
        System.out.println("4. Tiền đạo");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Nhập vào lựa chọn của bạn: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("|%-25s|%-15s|%-12s|%-20s|%-20s|%-15s|", "Name", "Date of birth", "Country", "Playing position", "Football Club", "Transfer Value");
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("database.dat"));
            LinkedHashMap<String, FootballPlayer> list = (LinkedHashMap) input.readObject();
            switch (choice) {
                case 1:
                    for (String key : list.keySet()) {
                        if (list.get(key).getPlayingPosition().equals("GoalKeeper")) {
                            list.get(key).displayInformation();
                        }
                    }
                    break;
                case 2:
                    for (String key : list.keySet()) {
                        if (list.get(key).getPlayingPosition().equals("Defender")) {
                            list.get(key).displayInformation();
                        }
                    }
                    break;
                case 3:
                    for (String key : list.keySet()) {
                        if (list.get(key).getPlayingPosition().equals("Midfielder")) {
                            list.get(key).displayInformation();
                        }
                    }
                    break;
                case 4:
                    for (String key : list.keySet()) {
                        if (list.get(key).getPlayingPosition().equals("Striker")) {
                            list.get(key).displayInformation();
                        }
                    }
                    break;
                default:
                    System.out.println("MỜi chọn lại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

}
