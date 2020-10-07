import java.util.*;

public class Manager {
    public static LinkedHashMap<String, FootballPlayer> fifa = new LinkedHashMap<>();
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

    public void addFootballPlayer(FootballPlayer footballPlayer) {
        footballPlayer.setFootballClub(getFootBallClub().getName());
        LinkedHashMap<String, FootballPlayer> newListFootballPlayer = getFootBallClub().getListFootballPlayer();
        newListFootballPlayer.put(footballPlayer.getName(), footballPlayer);
        this.footBallClub.setListFootballPlayer(newListFootballPlayer);
    }

    public void buyFootballPlayer(String name) {
        int newBudget = this.footBallClub.getBudgetOfClub();
        Set<String> keySet = market.keySet();
        for (String key : keySet) {
            FootballPlayer footballPlayer = market.get(key);
            if (name.equalsIgnoreCase(footballPlayer.getName())) {
                addFootballPlayer(footballPlayer);
                newBudget -= footballPlayer.getTransferValue();
                this.footBallClub.setBudgetOfClub(newBudget);
            }
        }
    }

    public void sellFootballPlayer(String name) {

    }

}
