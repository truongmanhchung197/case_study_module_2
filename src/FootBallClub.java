import java.util.HashMap;
import java.util.LinkedHashMap;

public class FootBallClub {
    private String name;
    private String nameOfStadium;
    private int foundedYear;
    private int budgetOfClub;
    private LinkedHashMap<String, FootballPlayer> listFootballPlayer;

    public FootBallClub() {
    }

    public FootBallClub(String name, String nameOfStadium, int foundedYear, int budgetOfClub, LinkedHashMap<String, FootballPlayer> listFootballPlayer) {
        this.name = name;
        this.nameOfStadium = nameOfStadium;
        this.foundedYear = foundedYear;
        this.budgetOfClub = budgetOfClub;
        this.listFootballPlayer = listFootballPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfStadium() {
        return nameOfStadium;
    }

    public void setNameOfStadium(String nameOfStadium) {
        this.nameOfStadium = nameOfStadium;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public int getBudgetOfClub() {
        return budgetOfClub;
    }

    public void setBudgetOfClub(int budgetOfClub) {
        this.budgetOfClub = budgetOfClub;
    }

    public LinkedHashMap<String, FootballPlayer> getListFootballPlayer() {
        return listFootballPlayer;
    }

    public void setListFootballPlayer(LinkedHashMap<String, FootballPlayer> listFootballPlayer) {
        this.listFootballPlayer = listFootballPlayer;
    }

    public void displayInformationClub(){
        System.out.println("Name: "+name+", Stadium: "+nameOfStadium+", Founded year: "+foundedYear+", Budget of Club: "+budgetOfClub);
        System.out.println("List football player of the club " + getName());
        System.out.printf("|%-25s|%-15s|%-12s|%-20s|%-20s|%-15s|", "Name", "Date of birth", "Country", "Playing position", "Football Club", "Transfer Value");
        for (HashMap.Entry<String, FootballPlayer> entry : listFootballPlayer.entrySet()) {
            System.out.printf("\n|%-25s|%15s|%-12s|%-20s|%-20s|%15d|",
                    entry.getValue().getName(),
                    entry.getValue().getDateOfBirth(),
                    entry.getValue().getCountry(),
                    entry.getValue().getPlayingPosition(),
                    entry.getValue().getFootballClub(),
                    entry.getValue().getTransferValue());
        }
        System.out.println("");
    }
}
