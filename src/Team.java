/**
 * A Team object consists of the name of the team, their home stadium, their games played, games won, games drawn, games
 * lost, goals scored, goals conceded, their points, their ranking, and a boolean to represent whether they are home
 * or away for a certain Match.
 * @author Joseph Hajjar
 *
 */

public class Team implements Comparable<Team>
{
	private String name;
	private String stadium;
	private int gamesPlayed;
	private int gamesWon;
	private int gamesDrawn;
	private int gamesLost;
	private int goalsScored;
	private int goalsConceded;
	private int points;
	private boolean home;
	private int ranking;
	
	public Team(String name, String stadium, int ranking)
	{
		this.name = name;
		this.stadium = stadium;
		gamesPlayed = 0;
		gamesWon = 0;
		gamesDrawn = 0;
		gamesLost = 0;
		goalsScored = 0;
		goalsConceded = 0;
		points = 0;
		this.ranking = ranking;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getStadium()
	{
		return stadium;
	}
	
	public void setStadium(String stadium)
	{
		this.stadium = stadium;
	}

	public boolean isHome()
	{
		return home;
	}

	public void setHome(boolean home)
	{
		this.home = home;
	}

	public int getGamesPlayed()
	{
		return gamesPlayed;
	}

	public int getGamesWon()
	{
		return gamesWon;
	}

	public void increaseGamesWon()
	{
		this.gamesWon++;
		this.gamesPlayed++;
		this.points+=3;
	}

	public int getGamesDrawn()
	{
		return gamesDrawn;
	}

	public void increaseGamesDrawn()
	{
		this.gamesDrawn++;
		this.gamesPlayed++;
		this.points++;
	}

	public int getGamesLost()
	{
		return gamesLost;
	}

	public void increaseGamesLost()
	{
		this.gamesLost++;
		this.gamesPlayed++;
	}

	public int getGoalsScored()
	{
		return goalsScored;
	}

	public void increaseGoalsScored(int goalsScored)
	{
		this.goalsScored += goalsScored;
	}

	public int getGoalsConceded()
	{
		return goalsConceded;
	}

	public void increaseGoalsConceded(int goalsConceded)
	{
		this.goalsConceded += goalsConceded;
	}

	public int getPoints()
	{
		return points;
	}

	public void increasePoints(int points)
	{
		this.points += points;
	}
	
	public int getRanking()
	{
		return ranking;
	}

	public void setRanking(int ranking)
	{
		this.ranking = ranking;
	}

	public int getGoalDifference()
	{
		return goalsScored - goalsConceded;
	}
	
	public boolean equalStanding(Team t)
	{
		return this.compareTo(t) == 0;
	}
	
	public boolean equals(Team t)
	{
		return this.name.equals(t.getName()) && this.stadium.equals(t.getStadium());
	}
	
	@Override
	public String toString()
	{
		return name;
	}

	@Override
	public int compareTo(Team t2)
	{
		if(this.points > t2.getPoints())
			return 1;
		else if(this.points == t2.getPoints())
		{
			if(this.getGoalDifference() > t2.getGoalDifference())
				return 1;
			else if(this.getGoalDifference() == t2.getGoalDifference())
			{
				if(this.goalsScored > t2.getGoalsScored())
					return 1;
				else if(this.goalsScored == t2.getGoalsScored())
				{
					return 0;
				}
			}
		}
		return -1;
	}
}
