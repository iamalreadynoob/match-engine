package general;

import java.util.ArrayList;

public class TeamData
{

    private ArrayList<String> players;
    private ArrayList<Integer> attack, defence;

    private double avgDefence, avgAttack;

    public TeamData(ArrayList<String> players, ArrayList<Integer> attack, ArrayList<Integer> defence)
    {
        this.players = players;
        this.attack = attack;
        this.defence = defence;

        average();
    }

    public double getAvgDefence() {return avgDefence;}
    public double getAvgAttack() {return avgAttack;}
    public ArrayList<String> getPlayers() {return players;}
    public ArrayList<Integer> getAttack() {return attack;}

    private void average()
    {
        double total = 0;
        for (Integer p: defence) total += p;
        avgDefence = total / defence.size();

        total = 0;
        for (Integer p: attack) total += p;
        avgAttack = total / attack.size();
    }

}
