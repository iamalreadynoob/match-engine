package action;

import general.TeamData;
import java.util.Random;

public class Scoring
{

    TeamData home, away;

    public Scoring(TeamData home, TeamData away)
    {
        this.home = home;
        this.away = away;
    }


    public int score(char team)
    {
        int diff = 1;

        if (team == 'h')
        {
            //home advantage
            diff++;

            if (home.getAvgAttack() > away.getAvgDefence()) diff += (int) (home.getAvgAttack() - away.getAvgDefence());
        }

        else if (team == 'a')
        {
            if (away.getAvgAttack() > home.getAvgDefence()) diff += (int) (away.getAvgAttack() - home.getAvgDefence());
        }

        return testChance(diff);
    }

    private int testChance(int chances)
    {
        int goals = 0;

        for (int i = 0; i < chances; i++)
        {
            int faith = new Random().nextInt(5);

            if (faith >= 3) goals++;
        }

        return goals;
    }

}
