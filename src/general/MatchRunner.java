package general;

import action.Scorers;
import action.Scoring;
import java.util.ArrayList;
public class MatchRunner
{
    private int[] score;
    private ArrayList<ArrayList<String>> scorers;
    private ArrayList<ArrayList<Integer>> minutes;

    private TeamData home, away;

    public MatchRunner(TeamData home, TeamData away)
    {
        score = new int[2];

        this.home = home;
        this.away = away;
    }
    public void setHome(TeamData home) {this.home = home;}
    public void setAway(TeamData away) {this.away = away;}

    public int[] getScore() {return score;}
    public ArrayList<String> getScorers(boolean isAway)
    {
        if (!isAway) return scorers.get(0);
        else return scorers.get(1);
    }

    public ArrayList<Integer> getMinutes(boolean isAway)
    {
        if (!isAway) return minutes.get(0);
        else return minutes.get(1);
    }

    public void run()
    {
        score[0] = new Scoring(home, away).score('h');
        score[1] = new Scoring(home, away).score('a');

        Scorers scorer = new Scorers(score, home, away);
        scorers = scorer.getScorers();
        minutes = scorer.setMinutes();
    }

}
