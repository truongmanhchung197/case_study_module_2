import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        FootballPlayer ronaldo = new FootballPlayer("Cristiano Ronaldo", "05/02/1985", "Portugal", "Striker", "Juventus", 100);
        FootballPlayer ronaldoJr = new FootballPlayer("Cristiano Ronaldo Junior", "05/02/2000", "Portugal", "Striker", "Juventus", 50);
        LinkedHashMap<String, FootballPlayer> juventus = new LinkedHashMap<>();
        juventus.put(ronaldo.getName(), ronaldo);
        juventus.put(ronaldoJr.getName(), ronaldoJr);
        FootBallClub juventusFC = new FootBallClub("Juventus", "Turin", 1900, 100, juventus);
        LinkedHashMap<String, FootballPlayer> manchester = new LinkedHashMap<>();
//        HashMap<String,FootBallClub> stringFootBallClubHashMap=new HashMap<>();
//        stringFootBallClubHashMap.put(juventusFC.getName(),juventusFC);
        Manager zidane = new Manager(juventusFC);


    }
}
