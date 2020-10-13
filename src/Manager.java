import java.io.*;
import java.util.*;

public class Manager {
    public static LinkedHashMap<String, FootballPlayer> market = new LinkedHashMap<>();

    private FootBallClub footBallClub;

    public Manager(FootBallClub footBallClub) {
        this.footBallClub = footBallClub;
    }

    public FootBallClub getFootBallClub() {
        return footBallClub;
    }

    public void setFootBallClub(FootBallClub footBallClub) {
        this.footBallClub = footBallClub;
    }

    public void updateDatabase(LinkedHashMap listFootballPlayer){
        try {
            ObjectInputStream input=new ObjectInputStream(new FileInputStream("fifa.dat"));
            LinkedHashMap<String, LinkedHashMap<String, FootballPlayer>> list = (LinkedHashMap) input.readObject();
            list.put(this.footBallClub.getName(),listFootballPlayer);
            ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("fifa.dat"));
            output.writeObject(list);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateFifa(FootballPlayer footballPlayer){
        try {
            ObjectInputStream input=new ObjectInputStream(new FileInputStream("database.dat"));
            LinkedHashMap<String, FootballPlayer> list = (LinkedHashMap) input.readObject();
            list.put(footballPlayer.getName(),footballPlayer);
            ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("database.dat"));
            output.writeObject(list);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addFootballPlayer(FootballPlayer footballPlayer) {
        // đổi lại tên clb cũ của cầu thủ thành tên clb mới
        footballPlayer.setFootballClub(getFootBallClub().getName());
        LinkedHashMap<String, FootballPlayer> newListFootballPlayer = getFootBallClub().getListFootballPlayer();
        newListFootballPlayer.put(footballPlayer.getName(), footballPlayer);
        // cập nhật lại danh sách cầu thủ của clb
        this.footBallClub.setListFootballPlayer(newListFootballPlayer);
        updateDatabase(newListFootballPlayer);
        updateFifa(footballPlayer);
    }

    public void buyFootballPlayer(String name) {
        int newBudget = this.footBallClub.getBudgetOfClub();
        Set<String> keySet = market.keySet();
        for (String key : keySet) {
            FootballPlayer footballPlayer = market.get(key);
            if (name.equalsIgnoreCase(footballPlayer.getName())) {
                addFootballPlayer(footballPlayer);
                market.remove(footballPlayer.getName());
                // cập nhật lại ngân sách của clb sau khi mua cầu thủ
                newBudget -= footballPlayer.getTransferValue();
                this.footBallClub.setBudgetOfClub(newBudget);
                break;
            }
        }
    }

    public void deleteFootballPlayer(FootballPlayer footballPlayer) {
        LinkedHashMap<String, FootballPlayer> newListFootballPlayer = getFootBallClub().getListFootballPlayer();
        newListFootballPlayer.remove(footballPlayer.getName());
        getFootBallClub().setListFootballPlayer(newListFootballPlayer);
        updateDatabase(newListFootballPlayer);
    }

    public void sellFootballPlayer(String name) {
        int newBudget = this.footBallClub.getBudgetOfClub();
        LinkedHashMap<String, FootballPlayer> listFootballPlayer = getFootBallClub().getListFootballPlayer();
        Set<String> keySet = listFootballPlayer.keySet();
        for (String key : keySet) {
            FootballPlayer footballPlayer = listFootballPlayer.get(key);
            if (name.equalsIgnoreCase(footballPlayer.getName())) {
                deleteFootballPlayer(footballPlayer);
                market.put(footballPlayer.getName(),footballPlayer);
                newBudget+=footballPlayer.getTransferValue();
                this.footBallClub.setBudgetOfClub(newBudget);
                break;
            }
        }
    }

}
