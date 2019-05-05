/**
 * A Match object consists of two teams, a home team and an away team, two ints which represent the goals scored by each
 * team, and a boolean to store whether the game has been played or not
 * 
 * @author Joseph Hajjar
 *
 */

public class Match
{
	private Team hometeam;
	private Team awayteam;
	private int hometeamGoals;
	private int awayteamGoals;
	private boolean hasBeenPlayed;

	public Match(Team h, Team a)
	{
		setHometeam(h);
		setAwayteam(a);
		hasBeenPlayed = false;
	}

	public Team getHometeam()
	{
		return hometeam;
	}

	public void setHometeam(Team hometeam)
	{
		this.hometeam = hometeam;
		hometeam.setHome(true);
	}

	public Team getAwayteam()
	{
		return awayteam;
	}

	public void setAwayteam(Team awayteam)
	{
		this.awayteam = awayteam;
		awayteam.setHome(false);
	}

	public int getHometeamGoals()
	{
		return hometeamGoals;
	}

	public void setHometeamGoals(int goals)
	{
		this.hometeamGoals = goals;
	}

	public int getAwayteamGoals()
	{
		return awayteamGoals;
	}

	public void setAwayteamGoals(int goals)
	{
		this.awayteamGoals = goals;
	}

	private void setHasBeenPlayed(boolean b)
	{
		this.hasBeenPlayed = b;
	}

	public boolean equals(Match m)
	{
		return this.hometeam.equals(m.getHometeam()) && this.awayteam.equals(m.getAwayteam());
	}

	public void reverseTeams()
	{
		Team intermediate = this.hometeam;
		this.hometeam = this.awayteam;
		this.awayteam = intermediate;
	}
	
	/**
	 * To play a match, we set a probability for the number of goals each team will score based on their rank and
	 * their opponent's rank. The probabilities set for this project are arbitrary in that they are not backed up by any
	 * data, other than what seemed reasonable to me at the time.
	 * 
	 */
	
	public static void playMatchRandomly(Match m)
	{
		Team t1 = m.getHometeam();
		Team t2 = m.getAwayteam();
		int number = (int) (Math.random() * 100.);
		int t1goals, t2goals;

		// Determine t1's goals
		if (number <= 30)
			t1goals = 0;
		else if (number <= 55)
			t1goals = 1;
		else if (number <= 75)
			t1goals = 2;
		else if (number <= 85)
			t1goals = 3;
		else if (number <= 95)
			t1goals = 4;
		else
			t1goals = 5;

		// Determine t2's goals
		number = (int) (Math.random() * 100.);
		if (number <= 30)
			t2goals = 0;
		else if (number <= 55)
			t2goals = 1;
		else if (number <= 75)
			t2goals = 2;
		else if (number <= 85)
			t2goals = 3;
		else if (number <= 95)
			t2goals = 4;
		else
			t2goals = 5;

		// Update each team's stats
		t1.increaseGoalsConceded(t2goals);
		t1.increaseGoalsScored(t1goals);
		t2.increaseGoalsConceded(t1goals);
		t2.increaseGoalsScored(t2goals);
		if (t1goals > t2goals)
		{
			t1.increaseGamesWon();
			t2.increaseGamesLost();
		} else if (t1goals == t2goals)
		{
			t1.increaseGamesDrawn();
			t2.increaseGamesDrawn();
		} else
		{
			t1.increaseGamesLost();
			t2.increaseGamesWon();
		}
		
		// Set the variables of the Match instance based on what occurred in this match
		m.setHasBeenPlayed(true);
		m.setHometeamGoals(t1goals);
		m.setAwayteamGoals(t2goals);
	}

	public static void playMatchAccurately(Match m)
	{
		// Play the correct match based on the difference of the two teams rankings
		switch (Math.abs(m.getHometeam().getRanking() - m.getAwayteam().getRanking()))
		{
		case 0:
			playMatchEven(m);
			break;
		case 1:
			playMatchDiffOfOne(m);
			break;
		case 2:
			playMatchDiffOfTwo(m);
			break;
		case 3:
			playMatchDiffOfThree(m);
			break;
		}
	}

	private static void playMatchDiffOfThree(Match m)
	{
		Team t1 = m.getHometeam();
		Team t2 = m.getAwayteam();
		int number = (int) (Math.random() * 100);
		int t1goals, t2goals;
		int betterTeamGoals, worseTeamGoals;

		// Determine the better team's goals
		if (number <= 5)
			betterTeamGoals = 0;
		else if (number <= 15)
			betterTeamGoals = 1;
		else if (number <= 30)
			betterTeamGoals = 2;
		else if (number <= 55)
			betterTeamGoals = 3;
		else if (number <= 70)
			betterTeamGoals = 4;
		else if (number <= 85)
			betterTeamGoals = 5;
		else if (number <= 95)
			betterTeamGoals = 6;
		else
			betterTeamGoals = 7;

		// Determine the worse team's goals
		number = (int) (Math.random() * 100);
		if (number <= 45)
			worseTeamGoals = 0;
		else if (number <= 70)
			worseTeamGoals = 1;
		else if (number <= 85)
			worseTeamGoals = 2;
		else if (number <= 95)
			worseTeamGoals = 3;
		else if (number <= 98)
			worseTeamGoals = 4;
		else
			worseTeamGoals = 5;

		// Update each team's stats
		if(t1.getRanking() < t2.getRanking())
		{
			t1goals = betterTeamGoals;
			t2goals = worseTeamGoals;
		}
		else
		{
			t2goals = betterTeamGoals;
			t1goals = worseTeamGoals;
		}
		
		t1.increaseGoalsConceded(t2goals);
		t1.increaseGoalsScored(t1goals);
		t2.increaseGoalsConceded(t1goals);
		t2.increaseGoalsScored(t2goals);
		if (t1goals > t2goals)
		{
			t1.increaseGamesWon();
			t2.increaseGamesLost();
		} else if (t1goals == t2goals)
		{
			t1.increaseGamesDrawn();
			t2.increaseGamesDrawn();
		} else
		{
			t1.increaseGamesLost();
			t2.increaseGamesWon();
		}
		
		// Set the variables of the Match instance based on what occurred in this match
		m.setHasBeenPlayed(true);
		m.setHometeamGoals(t1goals);
		m.setAwayteamGoals(t2goals);
	}

	private static void playMatchDiffOfTwo(Match m)
	{
		Team t1 = m.getHometeam();
		Team t2 = m.getAwayteam();
		int number = (int) (Math.random() * 100);
		int t1goals, t2goals;
		int betterTeamGoals, worseTeamGoals;

		// Determine the better team's goals
		if (number <= 10)
			betterTeamGoals = 0;
		else if (number <= 25)
			betterTeamGoals = 1;
		else if (number <= 40)
			betterTeamGoals = 2;
		else if (number <= 65)
			betterTeamGoals = 3;
		else if (number <= 80)
			betterTeamGoals = 4;
		else if (number <= 90)
			betterTeamGoals = 5;
		else if (number <= 95)
			betterTeamGoals = 6;
		else
			betterTeamGoals = 7;

		// Determine the worse team's goals
		number = (int) (Math.random() * 100);
		if (number <= 30)
			worseTeamGoals = 0;
		else if (number <= 55)
			worseTeamGoals = 1;
		else if (number <= 70)
			worseTeamGoals = 2;
		else if (number <= 80)
			worseTeamGoals = 3;
		else if (number <= 90)
			worseTeamGoals = 4;
		else if (number <= 95)
			worseTeamGoals = 5;
		else
			worseTeamGoals = 6;

		// Update each team's stats
		if(t1.getRanking() < t2.getRanking())
		{
			t1goals = betterTeamGoals;
			t2goals = worseTeamGoals;
		}
		else
		{
			t2goals = betterTeamGoals;
			t1goals = worseTeamGoals;
		}
		
		t1.increaseGoalsConceded(t2goals);
		t1.increaseGoalsScored(t1goals);
		t2.increaseGoalsConceded(t1goals);
		t2.increaseGoalsScored(t2goals);
		if (t1goals > t2goals)
		{
			t1.increaseGamesWon();
			t2.increaseGamesLost();
		} else if (t1goals == t2goals)
		{
			t1.increaseGamesDrawn();
			t2.increaseGamesDrawn();
		} else
		{
			t1.increaseGamesLost();
			t2.increaseGamesWon();
		}

		// Set the variables of the Match instance based on what occurred in this match
		m.setHasBeenPlayed(true);
		m.setHometeamGoals(t1goals);
		m.setAwayteamGoals(t2goals);
	}

	private static void playMatchDiffOfOne(Match m)
	{
		Team t1 = m.getHometeam();
		Team t2 = m.getAwayteam();
		int number = (int) (Math.random() * 100);
		int t1goals, t2goals;
		int betterTeamGoals, worseTeamGoals;

		// Determine the better team's goals
		if (number <= 15)
			betterTeamGoals = 0;
		else if (number <= 30)
			betterTeamGoals = 1;
		else if (number <= 55)
			betterTeamGoals = 2;
		else if (number <= 70)
			betterTeamGoals = 3;
		else if (number <= 85)
			betterTeamGoals = 4;
		else if (number <= 95)
			betterTeamGoals = 5;
		else
			betterTeamGoals = 6;

		// Determine the worse team's goals
		number = (int) (Math.random() * 100);
		if (number <= 20)
			worseTeamGoals = 0;
		else if (number <= 50)
			worseTeamGoals = 1;
		else if (number <= 65)
			worseTeamGoals = 2;
		else if (number <= 80)
			worseTeamGoals = 3;
		else if (number <= 90)
			worseTeamGoals = 4;
		else
			worseTeamGoals = 5;

		// Update each team's stats
		if(t1.getRanking() < t2.getRanking())
		{
			t1goals = betterTeamGoals;
			t2goals = worseTeamGoals;
		}
		else
		{
			t2goals = betterTeamGoals;
			t1goals = worseTeamGoals;
		}
		
		t1.increaseGoalsConceded(t2goals);
		t1.increaseGoalsScored(t1goals);
		t2.increaseGoalsConceded(t1goals);
		t2.increaseGoalsScored(t2goals);
		if (t1goals > t2goals)
		{
			t1.increaseGamesWon();
			t2.increaseGamesLost();
		} else if (t1goals == t2goals)
		{
			t1.increaseGamesDrawn();
			t2.increaseGamesDrawn();
		} else
		{
			t1.increaseGamesLost();
			t2.increaseGamesWon();
		}
		
		// Set the variables of the Match instance based on what occurred in this match
		m.setHasBeenPlayed(true);
		m.setHometeamGoals(t1goals);
		m.setAwayteamGoals(t2goals);
	}

	private static void playMatchEven(Match m)
	{
		playMatchRandomly(m);
	}
	
	public void reset()
	{
		this.hometeamGoals = 0;
		this.awayteamGoals = 0;
		this.hasBeenPlayed = false;
	}

	public String toString()
	{
		return hometeam + " vs. " + awayteam;
	}

	public void print()
	{
		if (hasBeenPlayed)
			System.out.printf("%20s " + getHometeamGoals() + " - " + getAwayteamGoals() + " %-20s at %s\n",
					getHometeam(), getAwayteam(), getHometeam().getStadium());
		else
			System.out.print(this + " has not been played yet\n");
	}

}
