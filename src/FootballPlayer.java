import java.io.Serializable;

public class FootballPlayer implements Serializable {
    private String name;
    private String dateOfBirth;
    private String country;
    private String playingPosition;
    private String footballClub;
    private int transferValue;

    public FootballPlayer() {
    }

    public FootballPlayer(String name, String dateOfBirth, String country, String playingPosition, String footballClub, int transferValue) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.playingPosition = playingPosition;
        this.footballClub = footballClub;
        this.transferValue = transferValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlayingPosition() {
        return playingPosition;
    }

    public void setPlayingPosition(String playingPosition) {
        this.playingPosition = playingPosition;
    }

    public String getFootballClub() {
        return footballClub;
    }

    public void setFootballClub(String footballClub) {
        this.footballClub = footballClub;
    }

    public int getTransferValue() {
        return transferValue;
    }

    public void setTransferValue(int transferValue) {
        this.transferValue = transferValue;
    }

    public void displayInformation(){
        System.out.printf("\n|%-25s|%15s|%-12s|%-20s|%-20s|%15d|",name,dateOfBirth,country,playingPosition,footballClub,transferValue);
    }
}
