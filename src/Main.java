import java.io.*;
import java.util.*;

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
        Manager pirlo = new Manager(juventusFC);

        FootBallClub manUFC = new FootBallClub("Manchester United", "Old Trafford", 1888, 500, manchester);
        Manager alex = new Manager(manUFC);

        FootBallClub real = new FootBallClub("Real Madrid", "Santiago Bernabeu", 1890, 500, realMadrid);
        Manager zidane = new Manager(manUFC);

        System.out.println("Mời chọn tính năng: ");
        System.out.println("1. Hiển thị danh sách các cầu thủ trong hệ thống");
        System.out.println("2. Hiển thị thông tin của đội bóng");
        System.out.println("3. Top 10 cầu thủ có giá trị chuyển nhượng cao nhất thế giới");
        System.out.println("4. Tìm kiếm thông tin cầu thủ theo tên");
        System.out.println("5. Tìm kiếm thông tin cầu thủ theo vị trí");
        System.out.println("6. Quản lý đội bóng của bạn");
        System.out.println("0. Exit");
        System.out.println("Nhập lựa chọn của bạn: ");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                displayFootballPlayerOfFifa();
                break;
            case 2:
                System.out.println("Chọn đội bóng bạn muốn: ");
                System.out.println("1. Juventus");
                System.out.println("2. Manchester United");
                System.out.println("3. Real Madrid");
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
                displayTop10();
                break;
            case 4:
                searchFootballPlayerByName();
                break;
            case 5:
                searchFootballPlayerByPos();
            case 6:
                System.out.println("Chọn huấn luyện viên: ");
                System.out.println("1. Pirlo");
                System.out.println("2. Alex");
                System.out.println("3. Zidane");
                int mount = input.nextInt();
                input.nextLine();
                switch (mount) {

                }

        }

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
    }

    public static void displayTop10() {
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
            for (int i = 0; i < 10; i++) {
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
    }

    public static void searchFootballPlayerByPos() {
        boolean check = false;
        System.out.println("Nhập vào vị trí cầu thủ: ");
        Scanner scanner = new Scanner(System.in);
        String playingPosition = scanner.nextLine();
        System.out.printf("|%-25s|%-15s|%-12s|%-20s|%-20s|%-15s|", "Name", "Date of birth", "Country", "Playing position", "Football Club", "Transfer Value");
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("database.dat"));
            LinkedHashMap<String, FootballPlayer> list = (LinkedHashMap) input.readObject();
            for (String key : list.keySet()) {
                if (playingPosition.equalsIgnoreCase(key)) {
                    check = true;
                    list.get(key).displayInformation();
                    break;
                }
            }
            if (!check) {
                System.out.println("Không tìm thấy cầu thủ ở vị trí này");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
