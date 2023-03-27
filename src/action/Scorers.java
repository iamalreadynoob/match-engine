package action;

import general.TeamData;

import java.util.ArrayList;
import java.util.Random;

public class Scorers
{
    private TeamData home, away;
    private ArrayList<ArrayList<String>> scorerPlayers;
    private ArrayList<ArrayList<Integer>> minutes;

    public Scorers(int[] results, TeamData home, TeamData away)
    {
        this.home = home;
        this.away = away;

        scorerPlayers = new ArrayList<>();
        minutes = new ArrayList<>();

        scorerPlayers.add(new ArrayList<>());
        scorerPlayers.add(new ArrayList<>());
        minutes.add(new ArrayList<>());
        minutes.add(new ArrayList<>());

        if (results[0] != 0)
        {
            getGoalers(false, results[0]);
            setMinutes(false, results[0]);
        }
        if (results[1] != 0)
        {
            getGoalers(true, results[1]);
            setMinutes(true, results[1]);
        }
    }

    public ArrayList<ArrayList<String>> getScorers()
    {
        return scorerPlayers;
    }

    public ArrayList<ArrayList<Integer>> setMinutes()
    {
       return minutes;
    }

    private void getGoalers(boolean isAway, int score)
    {
        for (int i = 0; i < score; i++)
        {
            who(isAway);
        }
    }

    private void setMinutes(boolean isAway, int score)
    {
        ArrayList<Integer> goals = getOrderedMinutes(getRawMinutes(score));

        if (!isAway) for (Integer g: goals) minutes.get(0).add(g);
        else for (Integer g: goals) minutes.get(1).add(g);
    }

    private int getTotalPossibility(boolean isAway)
    {
        int total = 0;

        ArrayList<Integer> valueList;
        if (!isAway) valueList = home.getAttack();
        else valueList = away.getAttack();

        for (Integer p: valueList) total += p;

        return total;
    }

    private void who(boolean isAway)
    {
        int total = getTotalPossibility(isAway);

        ArrayList<String> playerList;
        ArrayList<Integer> attack;
        if (!isAway)
        {
            playerList = home.getPlayers();
            attack = home.getAttack();
        }
        else
        {
            playerList = away.getPlayers();
            attack = away.getAttack();
        }

        int point = new Random().nextInt(total) + 1;
        int ceil = 0;
        int floor = 0;
        for (int i = 0; i < attack.size(); i++)
        {
            ceil += attack.get(i);
            if (i != 0) floor += attack.get(i-1);

            if (point > floor && point <= ceil)
            {
                int loc = 0;
                if (isAway) loc = 1;
                scorerPlayers.get(loc).add(playerList.get(i));
                break;
            }
        }
    }

    private ArrayList<Integer> getRawMinutes(int score)
    {
        ArrayList<Integer> rawMinutes = new ArrayList<>();

        for (int i = 0; i < score; i++) rawMinutes.add(new Random().nextInt(90) + 1);

        return rawMinutes;
    }
    private ArrayList<Integer> getOrderedMinutes(ArrayList<Integer> raw)
    {
        ArrayList<Integer> ordered = new ArrayList<>();
        ArrayList<Integer> rawEdited = raw;

        while (rawEdited.size() > 0)
        {
            int min = getMin(raw);
            ordered.add(min);
            int id = getMinID(min, rawEdited);
            rawEdited.remove(id);
        }

        return ordered;
    }

    private int getMin(ArrayList<Integer> raw)
    {
        int min = Integer.MAX_VALUE;

        for (Integer r: raw) if (r < min) min = r;

        return min;
    }

    private Integer getMinID(int number, ArrayList<Integer> raw)
    {
        Integer id = null;

        for (int i = 0; i < raw.size(); i++)
        {
            if (number == raw.get(i))
            {
                id = i;
                break;
            }
        }

        return id;
    }
}
